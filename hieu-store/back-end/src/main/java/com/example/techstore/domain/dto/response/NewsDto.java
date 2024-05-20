package com.example.techstore.domain.dto.response;

import com.example.techstore.domain.dto.common.UserDateAuditingDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsDto extends UserDateAuditingDto {
    private String id;
    private String title;
    private String avatar;
    private String summary;
    private String content;
    private Boolean status;
    private String categoryId;
    private String categoryName;
}
