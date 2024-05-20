package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.AddressRequestDto;
import com.example.techstore.domain.dto.response.AddressDto;
import com.example.techstore.domain.entity.Address;
import com.example.techstore.domain.entity.Address.AddressBuilder;
import com.example.techstore.domain.entity.User;
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
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto mapAddressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setUserId( addressUserId( address ) );
        addressDto.setCreatedDate( address.getCreatedDate() );
        addressDto.setLastModifiedDate( address.getLastModifiedDate() );
        addressDto.setCreatedBy( address.getCreatedBy() );
        addressDto.setLastModifiedBy( address.getLastModifiedBy() );
        addressDto.setId( address.getId() );
        addressDto.setCustomerName( address.getCustomerName() );
        addressDto.setPhone( address.getPhone() );
        addressDto.setAddress( address.getAddress() );
        addressDto.setType( address.getType() );
        addressDto.setAddressDefault( address.getAddressDefault() );

        return addressDto;
    }

    @Override
    public List<AddressDto> mapAddressesToAddressDtos(List<Address> addresses) {
        if ( addresses == null ) {
            return null;
        }

        List<AddressDto> list = new ArrayList<AddressDto>( addresses.size() );
        for ( Address address : addresses ) {
            list.add( mapAddressToAddressDto( address ) );
        }

        return list;
    }

    @Override
    public Address mapAddressRequestDtoToAddress(AddressRequestDto addressRequestDto) {
        if ( addressRequestDto == null ) {
            return null;
        }

        AddressBuilder address = Address.builder();

        address.customerName( addressRequestDto.getCustomerName() );
        address.phone( addressRequestDto.getPhone() );
        address.address( addressRequestDto.getAddress() );
        address.type( addressRequestDto.getType() );
        address.addressDefault( addressRequestDto.getAddressDefault() );

        return address.build();
    }

    @Override
    public void updateAddress(Address address, AddressRequestDto addressRequestDto) {
        if ( addressRequestDto == null ) {
            return;
        }

        if ( addressRequestDto.getCustomerName() != null ) {
            address.setCustomerName( addressRequestDto.getCustomerName() );
        }
        if ( addressRequestDto.getPhone() != null ) {
            address.setPhone( addressRequestDto.getPhone() );
        }
        if ( addressRequestDto.getAddress() != null ) {
            address.setAddress( addressRequestDto.getAddress() );
        }
        if ( addressRequestDto.getType() != null ) {
            address.setType( addressRequestDto.getType() );
        }
        if ( addressRequestDto.getAddressDefault() != null ) {
            address.setAddressDefault( addressRequestDto.getAddressDefault() );
        }
    }

    private String addressUserId(Address address) {
        if ( address == null ) {
            return null;
        }
        User user = address.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
