package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "custom-random-id")
    @GenericGenerator(name = "custom-random-id", strategy = "com.example.techstore.domain.entity.CustomRandomId")
    @Column(insertable = false, updatable = false, nullable = false, length = 7)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String avatar;
    // Screen
    @Column(nullable = false)
    private String screenTechnology;
    @Column(nullable = false)
    private String screenResolution;
    @Column(nullable = false)
    private String widescreen;
    @Column(nullable = false)
    private String scanFrequency;
    // Camera
    @Column(nullable = true)
    private String rearCamera;
    @Column(nullable = true)
    private String frontCamera;
    // CPU
    @Column(nullable = false)
    private String operationSystem;
    @Column(nullable = false)
    private String cpu;
    @Column(nullable = true)
    private Integer multiplier;
    @Column(nullable = true)
    private Integer numberStream;
    @Column(nullable = true)
    private String cpuSpeed;
    @Column(nullable = true)
    private String graphicChip;
    @Column(nullable = true)
    private String graphicCard;
    // Networks
    @Column(nullable = true)
    private String mobileNetwork;
    @Column(nullable = true)
    private String sim;
    @Column(nullable = false)
    private String wifi;
    @Column(nullable = true)
    private String gps;
    @Column(nullable = false)
    private String bluetooth;
    @Column(nullable = true)
    private String headphoneJack;
    @Column(nullable = true)
    private String chargingPort;
    @Column(nullable = true)
    private String connectionPort;
    @Column(nullable = true)
    private String webcam;
    // Battery
    @Column(nullable = false)
    private String batteryCapacity;
    @Column(nullable = true)
    private String batteryType;
    @Column(nullable = true)
    private String chargingSupport;
    // Other
    @Column(nullable = false)
    private String material;
    @Column(nullable = false)
    private String weight;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String launchDate;
    @Column(nullable = false)
    private String supplier;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<ProductOption> productOptions;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private List<Slide> slides;
}
