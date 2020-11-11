package com.desafio.ilegra.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Arquivo {

	private List<Cliente> clientes;
	
	private List<Venda> vendas;
	
	private List<Vendedor> vendedores;
	
	public Arquivo() {
		this.clientes = new ArrayList<Cliente>();
		this.vendas = new ArrayList<Venda>();
		this.vendedores = new ArrayList<Vendedor>();
	}


}
