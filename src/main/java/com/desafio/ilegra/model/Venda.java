package com.desafio.ilegra.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class Venda {

	private int id;
	
	private List<ItemVenda> sale;
	
	private String salesmanName;
	
	public BigDecimal getTotalVenda(){
		
		BigDecimal totalVenda = sale.stream()
                .map(x -> x.getPrecoTotal()) 
                .reduce(BigDecimal.ZERO, BigDecimal::add); 
		
		return totalVenda;

	}

}
