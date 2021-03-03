package com.project.aynat.util;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.dto.AgencyUserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgencyUserToDTO implements Converter<AgencyUser, AgencyUserDTO> {

    @Override
    public AgencyUserDTO convert(AgencyUser user) {
        AgencyUserDTO userDTO = new AgencyUserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserRole(user.getUserRole());
        userDTO.setPassword(user.getPassword());
        userDTO.setAccount(user.getAccount());
        userDTO.setActive(user.isActive());
        userDTO.setOrders(user.getOrders());
        return userDTO;
    }
}
