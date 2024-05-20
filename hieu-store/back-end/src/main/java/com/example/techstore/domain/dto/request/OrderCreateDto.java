package com.example.techstore.domain.dto.request;

import com.example.techstore.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCreateDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String addressId;
    @Valid
    @Size(min = 1, message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private List<OrderProductRequestDto> orderProductRequestDtos;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String note;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Long shippingFee;
    private String shippingDiscountCodeId;
    private String moneyDiscountCodeId;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Boolean paymentStatus;
}
