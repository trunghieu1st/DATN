package com.example.techstore.service.impl;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.ProductOptionCreateDto;
import com.example.techstore.domain.dto.request.ProductOptionUpdateDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.ProductOptionDto;
import com.example.techstore.domain.dto.response.ProductSimpleDto;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.ProductOption;
import com.example.techstore.domain.mapper.ProductOptionMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.ProductOptionRepository;
import com.example.techstore.repository.ProductRepository;
import com.example.techstore.service.ProductOptionService;
import com.example.techstore.util.PaginationUtil;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final ProductOptionMapper productOptionMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ProductOptionDto getById(String id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id}));
        return productOptionMapper.mapProductOptionToProductOptionDto(productOption);
    }

    @Override
    public List<ProductOptionDto> getAllByProductId(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{productId}));

        List<ProductOption> productOptions = productOptionRepository.getAllByProductId(productId);
        return productOptionMapper.mapProductOptionsToProductOptionDtos(productOptions);
    }
    @Override
    public List<ProductOptionDto> getAll() {
        List<ProductOption> productOptions = productOptionRepository.getAll();
        return productOptionMapper.mapProductOptionsToProductOptionDtos(productOptions);
    }

    @Override
    public ProductOptionDto create(ProductOptionCreateDto productOptionCreateDto) {
        Product product = productRepository.findById(productOptionCreateDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{productOptionCreateDto.getProductId()}));
        ProductOption productOption = productOptionMapper.mapProductOptionCreateDtoToProductOption(productOptionCreateDto);
        productOption.setImage(uploadFileUtil.uploadImage(productOptionCreateDto.getImage()));
        productOption.setProduct(product);
        return productOptionMapper.mapProductOptionToProductOptionDto(productOptionRepository.save(productOption));
    }

    @Override
    public ProductOptionDto updateById(String id, ProductOptionUpdateDto productOptionUpdateDto) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id}));
        productOptionMapper.update(productOption, productOptionUpdateDto);
        if (productOptionUpdateDto.getImage() != null) {
            uploadFileUtil.destroyImageWithUrl(productOption.getImage());
            productOption.setImage(uploadFileUtil.uploadImage(productOptionUpdateDto.getImage()));
        }
        return productOptionMapper.mapProductOptionToProductOptionDto(productOptionRepository.save(productOption));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.ProductOption.ERR_NOT_FOUND_ID, new String[]{id}));
        productOptionRepository.delete(productOption);
        return new CommonResponseDto(true, MessageConstant.DELETE_PRODUCT_OPTION_SUCCESSFULLY);
    }

}
