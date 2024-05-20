package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.UserDiscountCreateDto;
import com.example.techstore.domain.dto.response.UserDiscountDto;
import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.entity.UserDiscount;
import com.example.techstore.domain.entity.UserDiscount.UserDiscountBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserDiscountMapperImpl implements UserDiscountMapper {

    @Override
    public UserDiscount mapUserDiscountCreateDtoToUserDiscount(UserDiscountCreateDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        UserDiscountBuilder userDiscount = UserDiscount.builder();

        return userDiscount.build();
    }

    @Override
    public UserDiscountDto mapUserDiscountToUserDiscountDto(UserDiscount discountCode) {
        if ( discountCode == null ) {
            return null;
        }

        UserDiscountDto userDiscountDto = new UserDiscountDto();

        userDiscountDto.setUserId( discountCodeUserId( discountCode ) );
        userDiscountDto.setUserName( discountCodeUserFullName( discountCode ) );
        userDiscountDto.setDiscountCodeId( discountCodeDiscountCodeId( discountCode ) );
        userDiscountDto.setCode( discountCodeDiscountCodeCode( discountCode ) );
        userDiscountDto.setDiscountAmount( discountCodeDiscountCodeDiscountAmount( discountCode ) );
        userDiscountDto.setCreatedDate( discountCode.getCreatedDate() );
        userDiscountDto.setLastModifiedDate( discountCode.getLastModifiedDate() );
        userDiscountDto.setCreatedBy( discountCode.getCreatedBy() );
        userDiscountDto.setLastModifiedBy( discountCode.getLastModifiedBy() );
        userDiscountDto.setId( discountCode.getId() );
        userDiscountDto.setAppliedDate( discountCode.getAppliedDate() );
        if ( discountCode.getStatus() != null ) {
            userDiscountDto.setStatus( String.valueOf( discountCode.getStatus() ) );
        }

        return userDiscountDto;
    }

    @Override
    public List<UserDiscountDto> mapUserDiscountToUserDiscountDto(List<UserDiscount> userDiscounts) {
        if ( userDiscounts == null ) {
            return null;
        }

        List<UserDiscountDto> list = new ArrayList<UserDiscountDto>( userDiscounts.size() );
        for ( UserDiscount userDiscount : userDiscounts ) {
            list.add( mapUserDiscountToUserDiscountDto( userDiscount ) );
        }

        return list;
    }

    private String discountCodeUserId(UserDiscount userDiscount) {
        if ( userDiscount == null ) {
            return null;
        }
        User user = userDiscount.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String discountCodeUserFullName(UserDiscount userDiscount) {
        if ( userDiscount == null ) {
            return null;
        }
        User user = userDiscount.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String discountCodeDiscountCodeId(UserDiscount userDiscount) {
        if ( userDiscount == null ) {
            return null;
        }
        DiscountCode discountCode = userDiscount.getDiscountCode();
        if ( discountCode == null ) {
            return null;
        }
        String id = discountCode.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String discountCodeDiscountCodeCode(UserDiscount userDiscount) {
        if ( userDiscount == null ) {
            return null;
        }
        DiscountCode discountCode = userDiscount.getDiscountCode();
        if ( discountCode == null ) {
            return null;
        }
        String code = discountCode.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private Long discountCodeDiscountCodeDiscountAmount(UserDiscount userDiscount) {
        if ( userDiscount == null ) {
            return null;
        }
        DiscountCode discountCode = userDiscount.getDiscountCode();
        if ( discountCode == null ) {
            return null;
        }
        Long discountAmount = discountCode.getDiscountAmount();
        if ( discountAmount == null ) {
            return null;
        }
        return discountAmount;
    }
}
