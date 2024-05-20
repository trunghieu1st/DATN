package com.example.techstore.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusConstant {
    PENDING(1, "Pending"),
    WAITING(2, "Waiting"),
    DELIVERING(3, "Delivering"),
    DELIVERED(4, "Delivered"),
    CANCELLED(5, "Cancelled");

    private final Integer id;
    private final String name;


}
