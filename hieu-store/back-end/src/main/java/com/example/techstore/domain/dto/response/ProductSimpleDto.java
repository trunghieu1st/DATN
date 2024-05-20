package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSimpleDto extends UserDateAuditingDto {
    private String id;
    private String name;
    private String avatar;

    private String screenTechnology;
    private String screenResolution;
    private String widescreen;
    private String categoryId;
    private List<ProductOptionSimpleDto> productOptionSimpleDtos;
}
