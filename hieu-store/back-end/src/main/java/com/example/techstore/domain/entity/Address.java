package com.example.techstore.domain.entity;

import com.example.techstore.domain.entity.common.UserDateAuditing;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "addresses")
public class Address extends UserDateAuditing {
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

    @Nationalized
    @Column(nullable = false)
    private Boolean type;

    @Column(nullable = false)
    private Boolean addressDefault;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_ADDRESS_USER"))
    private User user;
}
