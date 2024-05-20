package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.NewsRequestDto;
import com.example.techstore.domain.dto.response.NewsDto;
import com.example.techstore.domain.entity.News;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {
    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    News mapNewsCreateDtoToNews(NewsRequestDto createDto);

    @Mappings({
            @Mapping(target = "avatar", ignore = true)
    })
    void updateNews(@MappingTarget News news, NewsRequestDto updateDto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "avatar", source = "avatar"),
            @Mapping(target = "summary", source = "summary"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "categoryName", source = "category.name")
    })
    NewsDto mapNewsToNewsDto(News news);

    List<NewsDto> mapNewsToNewsDto(List<News> news);

}
