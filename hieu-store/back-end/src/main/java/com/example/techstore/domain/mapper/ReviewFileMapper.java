package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.response.ReviewFileDto;
import com.example.techstore.domain.entity.ReviewFile;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewFileMapper {

    ReviewFileDto mapReviewFileToReviewFileDto(ReviewFile reviewFile);

    List<ReviewFileDto> mapReviewFilesToReviewFileDtos(List<ReviewFile> reviews);

}
