package com.desafio.ilegra.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemVenda {

	private int id;
	
	private int quantity;
	
	private BigDecimal price;
	
	public BigDecimal getPrecoTotal() {
		return price.multiply(new BigDecimal(quantity));
	}
}
