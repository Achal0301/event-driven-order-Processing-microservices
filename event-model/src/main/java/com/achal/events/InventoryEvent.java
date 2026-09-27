package com.achal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private String orderId;
    private String status;
    private double amount;// RESERVED / FAILED
}

