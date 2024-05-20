package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "custom-random-id")
    @GenericGenerator(name = "custom-random-id", strategy = "com.example.techstore.domain.entity.CustomRandomId")
    @Column(insertable = false, updatable = false, nullable = false, length = 7)
    private String id;
    @Nationalized
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String phone;
    @Nationalized
    @Column(nullable = false)
    private String address;
    @Column(nullable = true)
    private String note;
    @Column(nullable = false)
    private Long shippingFee;
    @Column(nullable = false)
    private Long originalPrice;
    @Column(nullable = false)
    private Long totalPrice;
    @Column(nullable = false)
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "shipping_discount_code_id", foreignKey = @ForeignKey(name = "FK_ORDER_SHIPPING_CODE"))
    private DiscountCode shippingDiscountCode;

    @ManyToOne
    @JoinColumn(name = "money_discount_code_id", foreignKey = @ForeignKey(name = "FK_ORDER_MONEY_CODE"))
    private DiscountCode moneyDiscountCode;

    @ManyToOne
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(name = "FK_ORDER_STATUS"))
    private Status status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
