package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.SlideRequestDto;
import com.example.techstore.domain.dto.response.SlideDto;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.Slide;
import com.example.techstore.domain.entity.Slide.SlideBuilder;
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
public class SlideMapperImpl implements SlideMapper {

    @Override
    public Slide mapSlideRequestDtoToSlide(SlideRequestDto createDto) {
        if ( createDto == null ) {
            return null;
        }

        SlideBuilder slide = Slide.builder();

        slide.position( createDto.getPosition() );
        slide.description( createDto.getDescription() );
        slide.status( createDto.getStatus() );

        return slide.build();
    }

    @Override
    public void updateSlide(Slide slide, SlideRequestDto updateDto) {
        if ( updateDto == null ) {
            return;
        }

        if ( updateDto.getPosition() != null ) {
            slide.setPosition( updateDto.getPosition() );
        }
        if ( updateDto.getDescription() != null ) {
            slide.setDescription( updateDto.getDescription() );
        }
        if ( updateDto.getStatus() != null ) {
            slide.setStatus( updateDto.getStatus() );
        }
    }

    @Override
    public SlideDto mapSlideToSlideDto(Slide slide) {
        if ( slide == null ) {
            return null;
        }

        SlideDto slideDto = new SlideDto();

        slideDto.setProductId( slideProductId( slide ) );
        slideDto.setProductName( slideProductName( slide ) );
        slideDto.setCreatedDate( slide.getCreatedDate() );
        slideDto.setLastModifiedDate( slide.getLastModifiedDate() );
        slideDto.setCreatedBy( slide.getCreatedBy() );
        slideDto.setLastModifiedBy( slide.getLastModifiedBy() );
        slideDto.setId( slide.getId() );
        slideDto.setAvatar( slide.getAvatar() );
        slideDto.setPosition( slide.getPosition() );
        slideDto.setDescription( slide.getDescription() );
        slideDto.setStatus( slide.getStatus() );

        return slideDto;
    }

    @Override
    public List<SlideDto> mapSlideToSlideDto(List<Slide> slides) {
        if ( slides == null ) {
            return null;
        }

        List<SlideDto> list = new ArrayList<SlideDto>( slides.size() );
        for ( Slide slide : slides ) {
            list.add( mapSlideToSlideDto( slide ) );
        }

        return list;
    }

    private String slideProductId(Slide slide) {
        if ( slide == null ) {
            return null;
        }
        Product product = slide.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String slideProductName(Slide slide) {
        if ( slide == null ) {
            return null;
        }
        Product product = slide.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
