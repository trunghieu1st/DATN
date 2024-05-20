package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.ReviewCreateDto;
import com.example.techstore.domain.dto.request.ReviewUpdateDto;
import com.example.techstore.domain.dto.response.ReviewDto;
import com.example.techstore.domain.dto.response.ReviewFileDto;
import com.example.techstore.domain.entity.OrderDetail;
import com.example.techstore.domain.entity.Review;
import com.example.techstore.domain.entity.Review.ReviewBuilder;
import com.example.techstore.domain.entity.ReviewFile;
import com.example.techstore.domain.entity.User;
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
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public Review mapReviewCreateDtoToReview(ReviewCreateDto reviewCreateDto) {
        if ( reviewCreateDto == null ) {
            return null;
        }

        ReviewBuilder review = Review.builder();

        review.star( reviewCreateDto.getStar() );
        review.description( reviewCreateDto.getDescription() );

        return review.build();
    }

    @Override
    public ReviewDto mapReviewToReviewDto(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setUserId( reviewUserId( review ) );
        reviewDto.setUsername( reviewUserUsername( review ) );
        reviewDto.setOrderDetailId( reviewOrderDetailId( review ) );
        reviewDto.setFileDtos( reviewFileListToReviewFileDtoList( review.getFiles() ) );
        reviewDto.setCreatedDate( review.getCreatedDate() );
        reviewDto.setLastModifiedDate( review.getLastModifiedDate() );
        reviewDto.setCreatedBy( review.getCreatedBy() );
        reviewDto.setLastModifiedBy( review.getLastModifiedBy() );
        reviewDto.setId( review.getId() );
        reviewDto.setStar( review.getStar() );
        reviewDto.setDescription( review.getDescription() );

        return reviewDto;
    }

    @Override
    public List<ReviewDto> mapReviewsToReviewDtos(List<Review> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDto> list = new ArrayList<ReviewDto>( reviews.size() );
        for ( Review review : reviews ) {
            list.add( mapReviewToReviewDto( review ) );
        }

        return list;
    }

    @Override
    public void update(Review review, ReviewUpdateDto reviewUpdateDto) {
        if ( reviewUpdateDto == null ) {
            return;
        }

        if ( reviewUpdateDto.getStar() != null ) {
            review.setStar( reviewUpdateDto.getStar() );
        }
        if ( reviewUpdateDto.getDescription() != null ) {
            review.setDescription( reviewUpdateDto.getDescription() );
        }
    }

    private String reviewUserId(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        String id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String reviewUserUsername(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String reviewOrderDetailId(Review review) {
        if ( review == null ) {
            return null;
        }
        OrderDetail orderDetail = review.getOrderDetail();
        if ( orderDetail == null ) {
            return null;
        }
        String id = orderDetail.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected ReviewFileDto reviewFileToReviewFileDto(ReviewFile reviewFile) {
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

    protected List<ReviewFileDto> reviewFileListToReviewFileDtoList(List<ReviewFile> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewFileDto> list1 = new ArrayList<ReviewFileDto>( list.size() );
        for ( ReviewFile reviewFile : list ) {
            list1.add( reviewFileToReviewFileDto( reviewFile ) );
        }

        return list1;
    }
}
