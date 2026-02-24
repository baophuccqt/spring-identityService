package org.baophuccqt.springidentityservice.DTO.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 3, max = 32, message = "username too short")
    String username;

    @Size(min = 8, max = 32, message = "password's length is not 8-32 character long")
    String password;
    String email;
    String firstName;
    String lastName;
    String dob;
}
