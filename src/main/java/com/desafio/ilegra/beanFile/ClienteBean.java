package com.desafio.ilegra.beanFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteBean {

	@NotBlank
	@Size(min=3, max=3)
	private String inicializador;
	
	private String cnpj;
	
	private String nome;
	
	private String areaNegocio;
}
