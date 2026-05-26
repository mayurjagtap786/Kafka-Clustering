package com.techexamples.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class Order {

    private int productId;
    private int quantity;
    private Double amount;

}