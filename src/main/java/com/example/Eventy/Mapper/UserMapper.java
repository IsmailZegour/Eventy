package com.example.Eventy.Mapper;

import com.example.Eventy.Dto.User.UserDTO;
import com.example.Eventy.Model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
