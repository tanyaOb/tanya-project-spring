package com.project.aynat.service;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Role;
import com.project.aynat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public AgencyUser getId(String username) {
        AgencyUser agencyUser = userRepository.findByUserName(username);
        return agencyUser;
    }

    public List<AgencyUser> finAllByUserRole(Role role) {
        List<AgencyUser> clients = userRepository.findAllByUserRole(role);
        return clients;
    }

    @Transactional
    public boolean loadMoneyOnUserAccount(String clientName, int ammount) {
        try {
            AgencyUser client = userRepository.findByUserName(clientName);
            if (client.getAccount() == null) {
                client.setAccount(ammount);
            } else {
                client.setAccount(client.getAccount() + ammount);
            }
            userRepository.save(client);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<AgencyUser> findAllMasters(Role role) {
        List<AgencyUser> users = userRepository.findAllByUserRole(role);
        return users;
    }

    @Transactional
    public String addUserToDB(AgencyUser user) {
        AgencyUser userFromDb = userRepository.findByUserName(user.getUserName());

        if (userFromDb != null) {
            return "User already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return "User is saved";
    }
}
