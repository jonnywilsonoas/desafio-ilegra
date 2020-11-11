package com.desafio.ilegra.beanFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VendaBean {

	@NotBlank
	@Size(min=3, max=3)
	private String inicializador;
	
	private String idVenda;
	
	private String venda;
	
	private String nomeVendedor;
}
