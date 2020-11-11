package com.desafio.ilegra.beanFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

public class VendedorBean {

	@Getter @Setter
	@NotBlank
	@Size(min=3, max=3)
	private String inicializador;
	
	@Getter @Setter
	private String cpf;
	
	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private String salario;
}
