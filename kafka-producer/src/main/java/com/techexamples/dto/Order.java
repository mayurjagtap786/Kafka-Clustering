package com.techexamples.dto;

import lombok.Data;

@Data
public class Order {

    private String orderId;
    private int productId;
    private int quantity;
    private Double amount;

}