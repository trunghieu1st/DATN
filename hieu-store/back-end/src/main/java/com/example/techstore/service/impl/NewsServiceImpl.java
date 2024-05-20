package com.example.techstore.service.impl;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.constant.MessageConstant;
import com.example.techstore.constant.SortByDataConstant;
import com.example.techstore.domain.dto.pagination.PaginationFullRequestDto;
import com.example.techstore.domain.dto.pagination.PaginationResponseDto;
import com.example.techstore.domain.dto.pagination.PagingMeta;
import com.example.techstore.domain.dto.request.NewsRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.NewsDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.News;
import com.example.techstore.domain.mapper.NewsMapper;
import com.example.techstore.exception.NotFoundException;
import com.example.techstore.repository.CategoryRepository;
import com.example.techstore.repository.NewsRepository;
import com.example.techstore.service.NewsService;
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
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UploadFileUtil uploadFileUtil;
    private final NewsMapper newsMapper;

    @Override
    public NewsDto getById(String id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{id}));
        return newsMapper.mapNewsToNewsDto(news);
    }

    @Override
    public PaginationResponseDto<NewsDto> getByStatus(PaginationFullRequestDto paginationFullRequestDto, Boolean status) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.NEWS);
        Page<News> newsPage = newsRepository.getByStatus(status, pageable);

        PagingMeta meta = PaginationUtil.buildPagingMeta(paginationFullRequestDto, SortByDataConstant.NEWS, newsPage);
        List<NewsDto> newsDtoList = newsMapper.mapNewsToNewsDto(newsPage.getContent());
        return new PaginationResponseDto<>(meta, newsDtoList);
    }

    @Override
    public PaginationResponseDto<NewsDto> getAll(PaginationFullRequestDto paginationFullRequestDto) {
        Pageable pageable = PaginationUtil.buildPageable(paginationFullRequestDto, SortByDataConstant.NEWS);
        Page<News> newsPage = newsRepository.getAll(pageable);
        PagingMeta meta = PaginationUtil
                .buildPagingMeta(paginationFullRequestDto, SortByDataConstant.NEWS, newsPage);

        List<NewsDto> newsDto = newsMapper.mapNewsToNewsDto(newsPage.getContent());
        return new PaginationResponseDto<>(meta, newsDto);
    }

    @Override
    public NewsDto create(NewsRequestDto createDto) {
        Category category = categoryRepository.findById(createDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID,
                        new String[]{createDto.getCategoryId()}));

        News news = newsMapper.mapNewsCreateDtoToNews(createDto);
        news.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        news.setCategory(category);
        return newsMapper.mapNewsToNewsDto(newsRepository.save(news));
    }

    @Override
    public NewsDto update(String id, NewsRequestDto updateDto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_ID, new String[]{id}));
        Category category = categoryRepository.findById(updateDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID,
                        new String[]{updateDto.getCategoryId()}));
        newsMapper.updateNews(news, updateDto);

        MultipartFile multipartFile = updateDto.getAvatar();
        if (multipartFile != null && !multipartFile.isEmpty()) {
            uploadFileUtil.destroyImageWithUrl(news.getAvatar());
            news.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
            news.setCategory(category);
        }
        return newsMapper.mapNewsToNewsDto(newsRepository.save(news));
    }

    @Override
    public CommonResponseDto deleteById(String id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_ID, new String[]{id}));
        newsRepository.delete(news);
        return new CommonResponseDto(true, MessageConstant.DELETE_NEWS_SUCCESSFULLY);
    }
}
