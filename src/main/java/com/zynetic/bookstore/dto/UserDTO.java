package com.zynetic.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    
    public UserDTO toUserDTO() {
        return UserDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}