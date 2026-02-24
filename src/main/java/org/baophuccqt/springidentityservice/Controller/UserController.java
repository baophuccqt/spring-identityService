package org.baophuccqt.springidentityservice.Controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.baophuccqt.springidentityservice.DTO.Request.ApiResponse;
import org.baophuccqt.springidentityservice.DTO.Request.UserCreationRequest;
import org.baophuccqt.springidentityservice.DTO.Request.UserUpdateRequest;
import org.baophuccqt.springidentityservice.DTO.Response.UserResponse;
import org.baophuccqt.springidentityservice.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        response.setResult(userService.createUser(request));

        return response;
    }

    @GetMapping
    public ApiResponse< List<UserResponse> > getAllUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();

        response.setResult(userService.getAllUsers());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        response.setResult(userService.getUser(id));
        return response;
    }


    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        response.setResult(userService.updateUser(id, request));
        return response;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @DeleteMapping
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "All users deleted successfully";
    }
}
