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
public class ProductOptionDto extends UserDateAuditingDto {
    private String productId;
    private String productName;
    private String id;
    private Integer ram;
    private Integer storageCapacity;
    private String color;
    private String image;
    private Long price;
    private Integer quantity;
    private Boolean status;

}
