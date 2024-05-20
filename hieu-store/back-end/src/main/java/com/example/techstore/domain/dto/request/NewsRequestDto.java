package com.example.techstore.domain.dto.request;

import com.example.techstore.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsRequestDto {

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String title;

    private MultipartFile avatar;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String summary;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String content;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private Boolean status;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String categoryId;
}
