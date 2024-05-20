package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.RoleConstant;
import com.example.techstore.domain.dto.request.AddressRequestDto;
import com.example.techstore.domain.dto.response.AddressDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.entity.Address;
import com.example.techstore.domain.entity.User;
import com.example.techstore.domain.mapper.AddressMapper;
import com.example.techstore.exception.ForbiddenException;
import com.example.techstore.exception.InternalServerException;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.AddressRepository;
import com.example.techstore.service.AddressService;
import com.example.techstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserService userService;

    @Override
    public AddressDto getById(String id, String userId) {
        User user = userService.getById(userId);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!address.getCreatedBy().equals(userId) && !user.getRole().getName().equals(RoleConstant.ADMIN)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public List<AddressDto> getAllByCurrentUserId(String userId) {
        List<Address> addresses = addressRepository.getAllByUserId(userId);
        return addressMapper.mapAddressesToAddressDtos(addresses);
    }

    @Override
    public List<AddressDto> getAllByUserId(String userId) {
        userService.getById(userId);
        List<Address> addresses = addressRepository.getAllByUserId(userId);
        return addressMapper.mapAddressesToAddressDtos(addresses);
    }

    @Override
    public AddressDto getDefaultByCurrentUser(String userId) {
        Address address = addressRepository.getDefaultByUserId(userId);
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto create(AddressRequestDto addressRequestDto, String userId) {
        User user = userService.getById(userId);
        Address address = addressMapper.mapAddressRequestDtoToAddress(addressRequestDto);
        address.setUser(user);
        addressRepository.save(address);
        if (address.getAddressDefault()) {
            addressRepository.changeDefault(address.getId(), userId);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto updateById(String id, AddressRequestDto addressRequestDto, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        if (address.getAddressDefault() && !addressRequestDto.getAddressDefault()) {
            throw new InternalServerException(ErrorMessage.Address.ERR_CANCEL_DEFAULT);
        }
        addressMapper.updateAddress(address, addressRequestDto);
        addressRepository.save(address);
        if (address.getAddressDefault()) {
            addressRepository.changeDefault(address.getId(), userId);
        }
        return addressMapper.mapAddressToAddressDto(address);
    }

    @Override
    public AddressDto changeDefaultById(String id, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        addressRepository.changeDefault(address.getId(), userId);
        address.setAddressDefault(true);
        return addressMapper.mapAddressToAddressDto(addressRepository.save(address));
    }

    @Override
    public CommonResponseDto deleteById(String id, String userId) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Address.ERR_NOT_FOUND_ID, new String[]{id}));
        if (!address.getCreatedBy().equals(userId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_UPDATE_DELETE);
        }
        addressRepository.delete(address);
        return new CommonResponseDto(true, MessageConstant.DELETE_ADDRESS_SUCCESSFULLY);
    }
}
