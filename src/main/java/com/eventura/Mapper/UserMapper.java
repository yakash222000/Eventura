package com.eventura.Mapper;

import com.eventura.DTO.UserDTO;
import com.eventura.Model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
