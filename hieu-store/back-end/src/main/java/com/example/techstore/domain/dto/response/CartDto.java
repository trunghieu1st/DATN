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
public class CartDto extends UserDateAuditingDto {
    private String id;
    private ProductOptionDto productOptionDto;
    private Integer quantity;
}
