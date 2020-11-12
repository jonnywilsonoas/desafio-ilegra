package com.desafio.ilegra.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Vendedor {

	@Getter @Setter
	private String cpf;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private BigDecimal salary;

}
