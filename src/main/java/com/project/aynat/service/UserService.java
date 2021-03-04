package com.project.aynat.service;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.domain.Role;
import com.project.aynat.dto.AccountDTO;
import com.project.aynat.dto.AgencyUserDTO;
import com.project.aynat.repository.UserRepository;
import com.project.aynat.util.DTOtoAgencyUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOtoAgencyUser dtOtoAgencyUserConverter;

    public List<AgencyUser> finAllByUserRole(Role role) {
        return userRepository.findAllByUserRole(role);
    }

    public boolean loadMoneyOnUserAccount(AccountDTO accountDTO) {
        try {
            userRepository.updateAccountBalance(accountDTO.getUserName(), accountDTO.getAccount());
        } catch (Exception e) {
            LOG.error("Problem occurred while adding money to client account", e);
            return false;
        }
        return true;
    }

    public List<AgencyUser> findAllMasters(Role role) {
        return userRepository.findAllByUserRole(role);
    }

    @Transactional
    public String addUserToDB(AgencyUserDTO userDTO) {
        AgencyUser user = dtOtoAgencyUserConverter.convert(userDTO);
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
