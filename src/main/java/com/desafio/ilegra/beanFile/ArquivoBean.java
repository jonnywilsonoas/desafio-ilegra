package com.desafio.ilegra.beanFile;

import java.util.List;

import lombok.Data;

@Data
public class ArquivoBean {

	private List<ClienteBean> clienteBean;
	
	private List<VendaBean> vendaBean;
	
	private List<VendedorBean> vendedorBean;
}
