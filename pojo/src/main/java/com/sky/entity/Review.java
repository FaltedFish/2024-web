package com.sky.entity;

import lombok.Data;

@Data
public class Review {
    private Long orderId;
    private Boolean rating;
    private String reviewText;
}
