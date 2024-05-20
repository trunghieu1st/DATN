package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.DiscountCodeRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.DiscountCodeDto;
import com.example.techstore.domain.entity.DiscountCode;
import com.example.techstore.domain.mapper.DiscountCodeMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.DiscountCodeRepository;
import com.example.techstore.service.DiscountCodeService;
import com.example.techstore.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountCodeServiceImpl implements DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository;
    private final DiscountCodeMapper discountCodeMapper;

    @Override
    public DiscountCodeDto getById(String id) {
        DiscountCode discountCode = discountCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{id}));
        return discountCodeMapper.mapDiscountCodeToDiscountCodeDto(discountCode);
    }

    @Override
    public PaginationResponseDto<DiscountCodeDto> getAll(Boolean type, PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.DISCOUNT_CODE);
        Page<DiscountCode> discountCodePage = discountCodeRepository.getAll(paginationFullRequestDto.getKeyword(), type, pageable);

        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.DISCOUNT_CODE, discountCodePage);

        List<DiscountCodeDto> discountCodeDtoList = discountCodeMapper.mapDiscountCodeToDiscountCodeDto(discountCodePage.getContent());
        return new PaginationResponseDto<>(meta, discountCodeDtoList);
    }

    @Override
    public DiscountCodeDto create(DiscountCodeRequestDto createDto) {
        DiscountCode discountCode = discountCodeMapper.mapDiscountCodeRequestDtoToDiscountCode(createDto);
        return discountCodeMapper.mapDiscountCodeToDiscountCodeDto(discountCodeRepository.save(discountCode));
    }

    @Override
    public DiscountCodeDto update(String id, DiscountCodeRequestDto updateDto) {
        DiscountCode discountCode = discountCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{id}));
        discountCodeMapper.updateDiscountCode(discountCode, updateDto);
        return discountCodeMapper.mapDiscountCodeToDiscountCodeDto(discountCodeRepository.save(discountCode));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        DiscountCode discountCode = discountCodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.DiscountCode.ERR_NOT_FOUND_ID, new String[]{id}));
        discountCodeRepository.delete(discountCode);
        return new CommonResponseDto(true, MessageConstant.DELETE_DISCOUNT_CODE_SUCCESSFULLY);
    }
}
