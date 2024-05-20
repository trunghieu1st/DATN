package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.DateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto extends DateAuditingDto {
    private String id;
    private String username;
    private String fullName;
    private String gender;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String avatar;
    private String roleName;

}

