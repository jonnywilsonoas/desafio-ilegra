package com.desafio.ilegra.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Cliente {

	private String cnpj;
	
	private String name;
	
	private String businessArea;
}
