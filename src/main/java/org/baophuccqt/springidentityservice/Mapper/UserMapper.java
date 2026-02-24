package org.baophuccqt.springidentityservice.Mapper;

import org.baophuccqt.springidentityservice.DTO.Request.UserCreationRequest;
import org.baophuccqt.springidentityservice.DTO.Request.UserUpdateRequest;
import org.baophuccqt.springidentityservice.DTO.Response.UserResponse;
import org.baophuccqt.springidentityservice.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    @Mapping(target = "password", ignore = true)
    UserResponse toUserResponse(User user);
}
