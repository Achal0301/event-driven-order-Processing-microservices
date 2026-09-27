package com.achal.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="orders")
public class Order {
	 @Id
	    private String id;

	    private String productName;
	    private int quantity;
	    private double price;
	    private String status;

}
