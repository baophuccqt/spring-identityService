package org.baophuccqt.springidentityservice.Service;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.baophuccqt.springidentityservice.DTO.Request.ApiResponse;
import org.baophuccqt.springidentityservice.DTO.Request.UserCreationRequest;
import org.baophuccqt.springidentityservice.DTO.Request.UserUpdateRequest;
import org.baophuccqt.springidentityservice.DTO.Response.UserResponse;
import org.baophuccqt.springidentityservice.Entity.User;
import org.baophuccqt.springidentityservice.Exception.AppException;
import org.baophuccqt.springidentityservice.Exception.ErrorCode;
import org.baophuccqt.springidentityservice.Mapper.UserMapper;
import org.baophuccqt.springidentityservice.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        boolean exists = userRepository.existsByUsername(request.getUsername());
        if (exists) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> userMapper.toUserResponse(user)).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        ));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(()  -> new AppException(ErrorCode.USER_NOT_EXIST));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        User user =  userRepository.findById(id).orElseThrow(()  -> new AppException(ErrorCode.USER_NOT_EXIST));
        userRepository.delete(user);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
