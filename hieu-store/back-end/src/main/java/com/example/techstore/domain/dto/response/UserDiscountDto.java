package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDiscountDto extends UserDateAuditingDto {
    private String id;
    private String userId;
    private String userName;
    private String discountCodeId;
    private String code;
    private Long discountAmount;
    private LocalDateTime appliedDate;
    private String status;
}
