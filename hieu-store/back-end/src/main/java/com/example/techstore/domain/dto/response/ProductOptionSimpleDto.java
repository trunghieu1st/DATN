package com.example.techstore.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOptionSimpleDto {
    private String id;
    private Integer ram;
    private Integer storageCapacity;
    private String color;
    private String image;
    private Long price;
    private Integer quantity;
    private Boolean status;

}
