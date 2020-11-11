package com.desafio.ilegra.model;

import java.util.List;

import lombok.Data;

@Data
public class Venda {

	private int id;
	
	private List<ItemVenda> sale;
	
	private String salesmanName;

}
