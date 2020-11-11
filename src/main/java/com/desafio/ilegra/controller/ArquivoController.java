package com.desafio.ilegra.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.desafio.ilegra.model.Arquivo;
import com.desafio.ilegra.model.Cliente;
import com.desafio.ilegra.model.ItemVenda;
import com.desafio.ilegra.model.Venda;
import com.desafio.ilegra.model.Vendedor;
import com.desafio.ilegra.util.ConversorUtils;


@Controller
public class ArquivoController {
	
	@Autowired
	public ArquivoController() {
		
	}
	
	/*
	 * método que processar o arquivo encontrando no direitório IN
	 */
	@SuppressWarnings("resource")
	public Arquivo leituraArquivo(File arquivoIN) throws IOException{
		FileReader arquivoReaderIN = new FileReader(arquivoIN);
		BufferedReader leitor = new BufferedReader(arquivoReaderIN);
		String linha = "";
		StringBuilder sb = null;
	    
		Arquivo arquivo = new Arquivo();
		while((linha = leitor.readLine()) != null){
			Cliente cliente = null;
			Vendedor vendedor = null;
			Venda venda = null;
			
			linha = new String(linha.getBytes());
			switch(this.getTipoRegistro(linha)){

				// Vendedor
				case 1:
					sb = new StringBuilder(linha.trim());
					if(linhaToObject(sb.toString()) instanceof Vendedor) {
						vendedor = (Vendedor) linhaToObject(sb.toString());
						arquivo.getVendedores().add(vendedor);
					}
					System.out.println(sb.toString());
					break;
				
				// Cliente
				case 2:
					sb = new StringBuilder(linha.trim());
					if(linhaToObject(sb.toString()) instanceof Cliente) {
						cliente = (Cliente) linhaToObject(sb.toString());
						arquivo.getClientes().add(cliente);
					}
					System.out.println(sb.toString());
					break;
			    
				// Venda
				case 3:
					sb = new StringBuilder(linha.trim());
					if(linhaToObject(sb.toString()) instanceof Venda) {
						venda = (Venda) linhaToObject(sb.toString());
						arquivo.getVendas().add(venda);
					}
					
					System.out.println(sb.toString());
					break;
					
				case 0: 
					sb.append(" " + linha.trim());
					
					Object linhaConvertida = linhaToObject(sb.toString());
					
					if(linhaConvertida instanceof Vendedor) {
						arquivo.getVendedores().remove(vendedor);
						arquivo.getVendedores().add((Vendedor) linhaConvertida);
					} else if (linhaConvertida instanceof Cliente) {
						arquivo.getClientes().remove(cliente);
						arquivo.getClientes().add((Cliente) linhaConvertida);
					} else if (linhaConvertida instanceof Venda) {
						arquivo.getVendas().remove(venda);
						arquivo.getVendas().add((Venda) linhaConvertida);
					}
					
					System.out.println(sb.toString());
					break;
				
				default:
					break;
			}
			System.gc();
		}
		return arquivo;
	}
	
	private Object linhaToObject(String linha){
		String linhaSpt[] = linha.trim().split(Pattern.quote("ç"));
		if(linhaSpt.length == 4) {
			int indicador = getTipoRegistro(linhaSpt[0]);
			
			switch (indicador) {
			    
			    // Vendedor
				case 1:
					Vendedor vendedor = new Vendedor();
					vendedor.setCpf(linhaSpt[1]);
					vendedor.setName(linhaSpt[2]);
					vendedor.setSalary(Double.parseDouble(linhaSpt[3]));
					return vendedor;

				// Cliente	
				case 2:
					Cliente cliente = new Cliente();
					cliente.setCnpj(linhaSpt[1]);
					cliente.setName(linhaSpt[2]);
					cliente.setBusinessArea(linhaSpt[3]);
					return cliente;
					
				// Venda
				case 3:
					Venda venda = new Venda();
					venda.setId(Integer.parseInt(linhaSpt[1]));
					venda.setSale(converterItemVenda(linhaSpt[2].substring(1, linhaSpt[2].length() - 1)));
					venda.setSalesmanName(linhaSpt[3]);
					return venda;

				default:
					break;
			}
		}
		return linha;
	}

	/*
	 * método que converte uma string separada por virgulas(,) e traços(-) em itens da venda
	 */
	private List<ItemVenda> converterItemVenda(String string) {
		String[] itensVendaArray = string.split(Pattern.quote(","));
		List<ItemVenda> itensVendaList = new ArrayList<ItemVenda>();
		for(int i = 0; i < itensVendaArray.length; i++) {
			String[] itensVendaUnidade = string.split(Pattern.quote("-"));
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setId(Integer.parseInt(itensVendaUnidade[0]));
			itemVenda.setQuantity(Integer.parseInt(itensVendaUnidade[1]));
			itemVenda.setPrice(Double.parseDouble(itensVendaUnidade[2]));
			
			itensVendaList.add(itemVenda);
			
		}
		return itensVendaList;
	}

	/*
	 * método que controla o tipo de linha que será processada
	 */
	public int getTipoRegistro(String linha){
		int indicador = 0;
		try {
			indicador = Integer.parseInt(ConversorUtils.getValorAsString(linha, 1, 3));
		} catch (NumberFormatException e) {
			System.out.println("linha complemento da anterior");
		}
		return indicador;
	}
}
