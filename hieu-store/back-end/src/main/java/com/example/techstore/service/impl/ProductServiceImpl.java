package com.example.techstore.service.impl;

import com.example.techstore.constant.CommonConstant;
import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.ProductRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.ProductDto;
import com.example.techstore.domain.dto.response.ProductSimpleDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.mapper.ProductMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.CategoryRepository;
import com.example.techstore.repository.ProductRepository;
import com.example.techstore.service.ProductService;
import com.example.techstore.util.PaginationUtil;
import com.example.techstore.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final UploadFileUtil uploadFileUtil;

    @Override
    public ProductDto getById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id}));
        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public PaginationResponseDto<ProductSimpleDto> getAll(String categoryId, PaginationFullRequestDto paginationFullRequestDto) {
        if (!categoryId.isEmpty()) {
            categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{categoryId}));
        }
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_PRODUCT_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);
        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.PRODUCT);
        Page<Product> productPage;
        if (categoryId.isEmpty()) {
            productPage = productRepository.getAll(pageable);
        } else {
            productPage = productRepository.getAll(categoryId, pageable);
        }

        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.PRODUCT, productPage);

        List<ProductSimpleDto> productSimpleDtos = productMapper.mapProductsToProductSimpleDtos(productPage.getContent());

        return new PaginationResponseDto<>(meta, productSimpleDtos);
    }

    @Override
    public PaginationResponseDto<ProductSimpleDto> search(PaginationFullRequestDto paginationFullRequestDto) {
        int pageSize = paginationFullRequestDto.getPageSize() != CommonConstant.PAGE_SIZE_DEFAULT
                ? paginationFullRequestDto.getPageSize() : CommonConstant.NUM_OF_PRODUCT_PER_PAGE;
        paginationFullRequestDto.setPageSize(pageSize);

        Pageable pageable = PaginationUtil
                .buildPageable(paginationFullRequestDto, SortByDataConstant.PRODUCT);
        Page<Product> productPage = productRepository.search(paginationFullRequestDto.getKeyword(), pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.PRODUCT, productPage);

        List<ProductSimpleDto> productSimpleDtos = productMapper.mapProductsToProductSimpleDtos(productPage.getContent());

        return new PaginationResponseDto<>(meta, productSimpleDtos);
    }

    @Override
    public ProductDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId()}));

        Product product = productMapper.mapProductRequestDtoToProduct(productRequestDto);
        product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        product.setCategory(category);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateById(String id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id}));
        Category category = categoryRepository.findById(productRequestDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{productRequestDto.getCategoryId()}));
        productMapper.update(product, productRequestDto);
        uploadFileUtil.destroyImageWithUrl(product.getAvatar());
           MultipartFile multipartFile = productRequestDto.getAvatar();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            uploadFileUtil.destroyImageWithUrl(product.getAvatar());
            product.setAvatar(uploadFileUtil.uploadImage(productRequestDto.getAvatar()));
        }
        product.setCategory(category);
        return productMapper.mapProductToProductDto(productRepository.save(product));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{id}));
        productRepository.delete(product);
        return new CommonResponseDto(true, MessageConstant.DELETE_PRODUCT_SUCCESSFULLY);
    }
}
