package com.achal.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReleaseEvent {
    private String orderId;
}
