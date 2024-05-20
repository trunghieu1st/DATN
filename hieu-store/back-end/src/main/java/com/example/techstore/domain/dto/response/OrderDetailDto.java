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
public class OrderDetailDto extends UserDateAuditingDto {
    private String id;
    private String orderId;
    private ProductOptionDto productOptionDto;
    private Integer quantity;
    private Long price;

}
