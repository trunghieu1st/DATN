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
public class ProductDto extends UserDateAuditingDto {
    private String id;
    private String name;
    private String avatar;

    private String screenTechnology;
    private String screenResolution;
    private String widescreen;
    private String scanFrequency;

    private String rearCamera;
    private String frontCamera;

    private String operationSystem;
    private String cpu;
    private Integer multiplier;
    private Integer numberStream;
    private String cpuSpeed;
    private String graphicChip;
    private String graphicCard;

    private String mobileNetwork;
    private String sim;
    private String wifi;
    private String gps;
    private String bluetooth;
    private String headphoneJack;
    private String chargingPort;
    private String connectionPort;
    private String webcam;

    private String batteryCapacity;
    private String batteryType;
    private String chargingSupport;

    private String material;
    private String weight;
    private String size;
    private String launchDate;
    private String supplier;

    private String categoryId;
    private String categoryName;

    private List<ProductOptionDto> productOptionDtos;
}
