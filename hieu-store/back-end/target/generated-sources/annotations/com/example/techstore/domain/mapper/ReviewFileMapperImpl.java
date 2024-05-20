package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.ReviewFileDto;
import com.example.techstore.domain.entity.ReviewFile;
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
public class ReviewFileMapperImpl implements ReviewFileMapper {

    @Override
    public ReviewFileDto mapReviewFileToReviewFileDto(ReviewFile reviewFile) {
        if ( reviewFile == null ) {
            return null;
        }

        ReviewFileDto reviewFileDto = new ReviewFileDto();

        reviewFileDto.setCreatedDate( reviewFile.getCreatedDate() );
        reviewFileDto.setLastModifiedDate( reviewFile.getLastModifiedDate() );
        reviewFileDto.setCreatedBy( reviewFile.getCreatedBy() );
        reviewFileDto.setLastModifiedBy( reviewFile.getLastModifiedBy() );
        reviewFileDto.setId( reviewFile.getId() );
        reviewFileDto.setPath( reviewFile.getPath() );
        reviewFileDto.setName( reviewFile.getName() );
        reviewFileDto.setSize( reviewFile.getSize() );

        return reviewFileDto;
    }

    @Override
    public List<ReviewFileDto> mapReviewFilesToReviewFileDtos(List<ReviewFile> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewFileDto> list = new ArrayList<ReviewFileDto>( reviews.size() );
        for ( ReviewFile reviewFile : reviews ) {
            list.add( mapReviewFileToReviewFileDto( reviewFile ) );
        }

        return list;
    }
}
