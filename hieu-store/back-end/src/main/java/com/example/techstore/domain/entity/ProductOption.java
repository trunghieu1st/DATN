package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_options")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductOption extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "custom-random-id")
    @GenericGenerator(name = "custom-random-id", strategy = "com.example.techstore.domain.entity.CustomRandomId")
    @Column(insertable = false, updatable = false, nullable = false, length = 7)
    private String id;
    @Column(nullable = false)
    private Integer ram;
    @Column(nullable = false)
    private Integer storageCapacity;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_OPTION_PRODUCT"))
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL)
    private List<Cart> carts;

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
