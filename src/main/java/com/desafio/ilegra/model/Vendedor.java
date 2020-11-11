package com.desafio.ilegra.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	private double salary;

}
