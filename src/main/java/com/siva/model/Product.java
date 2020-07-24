package com.siva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private Integer prodId;
	private String prodName;
	private Double prodCost;
	private Double prodDiscount;
	private Double prodGst;
	

}
