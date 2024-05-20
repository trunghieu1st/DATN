package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.DiscountCodeRequestDto;
import com.example.techstore.domain.dto.response.DiscountCodeDto;
import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.entity.DiscountCode.DiscountCodeBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class DiscountCodeMapperImpl implements DiscountCodeMapper {

    @Override
    public DiscountCode mapDiscountCodeRequestDtoToDiscountCode(DiscountCodeRequestDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        DiscountCodeBuilder discountCode = DiscountCode.builder();

        if ( createDto.getStartDate() != null ) {
            discountCode.startDate( LocalDateTime.parse( createDto.getStartDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) ) );
        }
        if ( createDto.getExpirationDate() != null ) {
            discountCode.expirationDate( LocalDateTime.parse( createDto.getExpirationDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) ) );
        }
        discountCode.code( createDto.getCode() );
        discountCode.discountAmount( createDto.getDiscountAmount() );
        discountCode.quantity( createDto.getQuantity() );
        discountCode.type( createDto.getType() );

        return discountCode.build();
    }

    @Override
    public DiscountCodeDto mapDiscountCodeToDiscountCodeDto(DiscountCode discountCode) {
        if ( discountCode == null ) {
            return null;
        }

        DiscountCodeDto discountCodeDto = new DiscountCodeDto();

        discountCodeDto.setCreatedDate( discountCode.getCreatedDate() );
        discountCodeDto.setLastModifiedDate( discountCode.getLastModifiedDate() );
        discountCodeDto.setCreatedBy( discountCode.getCreatedBy() );
        discountCodeDto.setLastModifiedBy( discountCode.getLastModifiedBy() );
        discountCodeDto.setId( discountCode.getId() );
        discountCodeDto.setCode( discountCode.getCode() );
        discountCodeDto.setDiscountAmount( discountCode.getDiscountAmount() );
        discountCodeDto.setStartDate( discountCode.getStartDate() );
        discountCodeDto.setExpirationDate( discountCode.getExpirationDate() );
        discountCodeDto.setQuantity( discountCode.getQuantity() );
        discountCodeDto.setType( discountCode.getType() );

        return discountCodeDto;
    }

    @Override
    public List<DiscountCodeDto> mapDiscountCodeToDiscountCodeDto(List<DiscountCode> discountCodes) {
        if ( discountCodes == null ) {
            return null;
        }

        List<DiscountCodeDto> list = new ArrayList<DiscountCodeDto>( discountCodes.size() );
        for ( DiscountCode discountCode : discountCodes ) {
            list.add( mapDiscountCodeToDiscountCodeDto( discountCode ) );
        }

        return list;
    }

    @Override
    public void updateDiscountCode(DiscountCode discountCode, DiscountCodeRequestDto updateDto) {
        if ( updateDto == null ) {
            return;
        }

        if ( updateDto.getStartDate() != null ) {
            discountCode.setStartDate( LocalDateTime.parse( updateDto.getStartDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) ) );
        }
        if ( updateDto.getExpirationDate() != null ) {
            discountCode.setExpirationDate( LocalDateTime.parse( updateDto.getExpirationDate(), DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) ) );
        }
        if ( updateDto.getCode() != null ) {
            discountCode.setCode( updateDto.getCode() );
        }
        if ( updateDto.getDiscountAmount() != null ) {
            discountCode.setDiscountAmount( updateDto.getDiscountAmount() );
        }
        if ( updateDto.getQuantity() != null ) {
            discountCode.setQuantity( updateDto.getQuantity() );
        }
        if ( updateDto.getType() != null ) {
            discountCode.setType( updateDto.getType() );
        }
    }
}
