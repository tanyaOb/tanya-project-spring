package com.project.aynat.controller;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    //TOID
    @PostMapping("/registration")
    public String addUser(AgencyUser user, Map<String, Object> model) {
        String message = userService.addUserToDB(user);
        model.put("message", message);
        if (message.equals("User already exists!")) {
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping(value = "/", produces = "text/plain;charset=UTF-8")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String role = authentication.getAuthorities().toString();

        if (role.contains("MANAGER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/manager/manageorders"));
        } else if (role.contains("CLIENT")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/client/myorder"));
        } else if (role.contains("MASTER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/master/checkorders"));
        }
    }
}