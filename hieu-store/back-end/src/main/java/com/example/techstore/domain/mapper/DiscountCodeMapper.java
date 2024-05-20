package com.example.techstore.domain.mapper;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.domain.dto.request.DiscountCodeRequestDto;
import com.example.techstore.domain.dto.response.DiscountCodeDto;
import com.example.techstore.domain.entity.DiscountCode;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiscountCodeMapper {
    @Mappings({
            @Mapping(target = "startDate", source = "startDate", dateFormat = CommonConstant.PATTERN_DATE_TIME),
            @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = CommonConstant.PATTERN_DATE_TIME)
    })
    DiscountCode mapDiscountCodeRequestDtoToDiscountCode(DiscountCodeRequestDto createDto);

    DiscountCodeDto mapDiscountCodeToDiscountCodeDto(DiscountCode discountCode);

    List<DiscountCodeDto> mapDiscountCodeToDiscountCodeDto(List<DiscountCode> discountCodes);

    @Mappings({
            @Mapping(target = "startDate", source = "startDate", dateFormat = CommonConstant.PATTERN_DATE_TIME),
            @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = CommonConstant.PATTERN_DATE_TIME)

    })
    void updateDiscountCode(@MappingTarget DiscountCode discountCode, DiscountCodeRequestDto updateDto);
}
