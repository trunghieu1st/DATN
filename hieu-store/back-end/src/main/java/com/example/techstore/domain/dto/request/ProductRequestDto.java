package com.example.techstore.domain.dto.request;

import com.example.techstore.constant.ErrorMessage;
import com.example.techstore.validator.annotation.ValidFileImage;
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
public class ProductRequestDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String name;
//    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    @ValidFileImage
    private MultipartFile avatar;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String screenTechnology;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String screenResolution;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String widescreen;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String scanFrequency;

    private String rearCamera;
    private String frontCamera;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String operationSystem;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String cpu;
    private Integer multiplier;
    private Integer numberStream;
    private String cpuSpeed;
    private String graphicChip;
    private String graphicCard;

    private String mobileNetwork;
    private String sim;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String wifi;
    private String gps;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String bluetooth;
    private String headphoneJack;
    private String chargingPort;
    private String connectionPort;
    private String webcam;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String batteryCapacity;
    private String batteryType;
    private String chargingSupport;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String material;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String weight;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String size;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String launchDate;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String supplier;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String categoryId;
}
