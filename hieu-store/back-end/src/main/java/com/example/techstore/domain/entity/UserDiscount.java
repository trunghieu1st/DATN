package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_discounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDiscount extends UserDateAuditing {
    @Id
    @GeneratedValue(generator = "custom-random-id")
    @GenericGenerator(name = "custom-random-id", strategy = "com.example.techstore.domain.entity.CustomRandomId")
    @Column(insertable = false, updatable = false, nullable = false, length = 7)
    private String id;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountCode discountCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = true, name = "applied_date")
    private LocalDateTime appliedDate;

    @Column(nullable = true, name = "status")
    private Boolean status;
}
