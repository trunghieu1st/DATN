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
public class OrderDto extends UserDateAuditingDto {
    private String id;
    private String customerName;
    private String phone;
    private String address;
    private String note;
    private Long shippingFee;
    private Long originalPrice;
    private String shippingDiscountCodeId;
    private String moneyDiscountCodeId;
    private Long totalPrice;
    private Boolean paymentStatus;
    private Integer statusId;
    private String statusName;

}
