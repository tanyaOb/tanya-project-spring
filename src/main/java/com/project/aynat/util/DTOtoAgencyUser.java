package com.project.aynat.util;

import com.project.aynat.domain.AgencyUser;
import com.project.aynat.dto.AgencyUserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DTOtoAgencyUser implements Converter<AgencyUserDTO, AgencyUser> {

    @Override
    public AgencyUser convert(AgencyUserDTO userDTO) {
        AgencyUser user = new AgencyUser();
        user.setUserName(userDTO.getUserName());
        user.setUserRole(userDTO.getUserRole());
        user.setPassword(userDTO.getPassword());
        user.setAccount(userDTO.getAccount());
        user.setActive(userDTO.isActive());
        user.setOrders(userDTO.getOrders());
        return user;
    }
}
