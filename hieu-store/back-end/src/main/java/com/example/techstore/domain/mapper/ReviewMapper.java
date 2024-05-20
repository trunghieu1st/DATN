package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.ReviewCreateDto;
import com.example.techstore.domain.dto.request.ReviewUpdateDto;
import com.example.techstore.domain.dto.response.ReviewDto;
import com.example.techstore.domain.entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {
    @Mappings({
            @Mapping(target = "files", ignore = true),
    })
    Review mapReviewCreateDtoToReview(ReviewCreateDto reviewCreateDto);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "orderDetailId", source = "orderDetail.id"),
            @Mapping(target = "fileDtos", source = "files"),
    })
    ReviewDto mapReviewToReviewDto(Review review);

    List<ReviewDto> mapReviewsToReviewDtos(List<Review> reviews);

    @Mappings({
            @Mapping(target = "star", source = "star"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "files", ignore = true),
    })
    void update(@MappingTarget Review review, ReviewUpdateDto reviewUpdateDto);

}
