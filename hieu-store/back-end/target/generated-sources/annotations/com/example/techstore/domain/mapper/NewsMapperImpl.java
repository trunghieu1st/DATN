package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.NewsRequestDto;
import com.example.techstore.domain.dto.response.NewsDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.News;
import com.example.techstore.domain.entity.News.NewsBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:30+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public News mapNewsCreateDtoToNews(NewsRequestDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        NewsBuilder news = News.builder();

        news.title( createDto.getTitle() );
        news.summary( createDto.getSummary() );
        news.content( createDto.getContent() );
        news.status( createDto.getStatus() );

        return news.build();
    }

    @Override
    public void updateNews(News news, NewsRequestDto updateDto) {
        if ( updateDto == null ) {
            return;
        }

        if ( updateDto.getTitle() != null ) {
            news.setTitle( updateDto.getTitle() );
        }
        if ( updateDto.getSummary() != null ) {
            news.setSummary( updateDto.getSummary() );
        }
        if ( updateDto.getContent() != null ) {
            news.setContent( updateDto.getContent() );
        }
        if ( updateDto.getStatus() != null ) {
            news.setStatus( updateDto.getStatus() );
        }
    }

    @Override
    public NewsDto mapNewsToNewsDto(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDto newsDto = new NewsDto();

        newsDto.setId( news.getId() );
        newsDto.setTitle( news.getTitle() );
        newsDto.setAvatar( news.getAvatar() );
        newsDto.setSummary( news.getSummary() );
        newsDto.setContent( news.getContent() );
        newsDto.setStatus( news.getStatus() );
        newsDto.setCategoryId( newsCategoryId( news ) );
        newsDto.setCategoryName( newsCategoryName( news ) );
        newsDto.setCreatedDate( news.getCreatedDate() );
        newsDto.setLastModifiedDate( news.getLastModifiedDate() );
        newsDto.setCreatedBy( news.getCreatedBy() );
        newsDto.setLastModifiedBy( news.getLastModifiedBy() );

        return newsDto;
    }

    @Override
    public List<NewsDto> mapNewsToNewsDto(List<News> news) {
        if ( news == null ) {
            return null;
        }

        List<NewsDto> list = new ArrayList<NewsDto>( news.size() );
        for ( News news1 : news ) {
            list.add( mapNewsToNewsDto( news1 ) );
        }

        return list;
    }

    private String newsCategoryId(News news) {
        if ( news == null ) {
            return null;
        }
        Category category = news.getCategory();
        if ( category == null ) {
            return null;
        }
        String id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String newsCategoryName(News news) {
        if ( news == null ) {
            return null;
        }
        Category category = news.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
