package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.UserDiscountCreateDto;
import com.example.techstore.domain.dto.response.UserDiscountDto;
import com.example.techstore.domain.entity.UserDiscount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserDiscountMapper {

    UserDiscount mapUserDiscountCreateDtoToUserDiscount(UserDiscountCreateDto createDto);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "userName", source = "user.fullName"),
            @Mapping(target = "discountCodeId", source = "discountCode.id"),
            @Mapping(target = "code", source = "discountCode.code"),
            @Mapping(target = "discountAmount", source = "discountCode.discountAmount")
    })
    UserDiscountDto mapUserDiscountToUserDiscountDto(UserDiscount discountCode);

    List<UserDiscountDto> mapUserDiscountToUserDiscountDto(List<UserDiscount> userDiscounts);

}
