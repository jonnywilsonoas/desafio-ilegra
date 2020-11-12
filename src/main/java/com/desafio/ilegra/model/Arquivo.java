package com.desafio.ilegra.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Arquivo {
	
	private Long id;

	private String nomeArquivo;
	
	private List<Cliente> clientes;
	
	private List<Venda> vendas;
	
	private List<Vendedor> vendedores;
	
	public Arquivo() {
		this.clientes = new ArrayList<Cliente>();
		this.vendas = new ArrayList<Venda>();
		this.vendedores = new ArrayList<Vendedor>();
	}

	@Override
	public String toString() {
		return "Nesse arquivo contém:\n"
				+ "Quantidade de clientes: " + this.clientes.size() + "\n"
				+ "Quantidade de vendedores:" + this.vendedores.size() + "\n"
				+ "ID da venda mais cara:" + this.vendaMaisCara().getId() + "\n"
				+ "Pior Vendedor: " + this.piorVendedor().getName();
	}
	
	
	public Venda vendaMaisCara() {
		Venda maisCara = this.vendas.stream()
			      .max(Comparator.comparing(Venda::getTotalVenda))
			      .orElseThrow(NoSuchElementException::new);
		return maisCara;
	}
	
	public Vendedor piorVendedor() {
		Vendedor piorVendedor = null;
		BigDecimal totalVendasAUX = BigDecimal.ZERO;
		
		for(Vendedor vendedor : this.vendedores) {
			List<Venda> vendasVendedor = vendas.stream()
							.filter((x)-> x.getSalesmanName().contains(vendedor.getName()))
							.collect(Collectors.toList());
			
			BigDecimal totalVendas = BigDecimal.ZERO;
			for(Venda venda : vendasVendedor) {
				BigDecimal valorTotalVenda = venda.getTotalVenda();
				totalVendas = totalVendas.add(valorTotalVenda);
			}
			
			if(piorVendedor == null && totalVendasAUX.compareTo(BigDecimal.ZERO) == 0) {
				piorVendedor = vendedor;
				totalVendasAUX = totalVendas;
			} else {
				if(totalVendas.compareTo(totalVendasAUX) < 0 ) {
					piorVendedor = vendedor;
					totalVendasAUX = totalVendas;
				}
			}
			
		}
		return piorVendedor;
	}

}
