package com.codevent.demoSpringSecurity.dto;

import com.codevent.demoSpringSecurity.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
