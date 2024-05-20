package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto extends UserDateAuditingDto {
    private String id;
    private Integer star;
    private String description;
    private String userId;
    private String username;
    private String orderDetailId;
    private List<ReviewFileDto> fileDtos;

}
