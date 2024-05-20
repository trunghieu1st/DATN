package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto extends UserDateAuditingDto {
    private String id;
    private String customerName;
    private String phone;
    private String address;
    private Boolean type;
    private Boolean addressDefault;
    private String userId;
}
