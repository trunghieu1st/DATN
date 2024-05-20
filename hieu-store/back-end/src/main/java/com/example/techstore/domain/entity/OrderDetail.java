package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetail extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "custom-random-id")
    @GenericGenerator(name = "custom-random-id", strategy = "com.example.techstore.domain.entity.CustomRandomId")
    @Column(insertable = false, updatable = false, nullable = false, length = 7)
    private String id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_DETAIL_ORDER"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_option_id", foreignKey = @ForeignKey(name = "FK_DETAIL_OPTION"))
    private ProductOption productOption;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderDetail")
    private Review review;

}
