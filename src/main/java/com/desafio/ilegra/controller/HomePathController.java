package com.desafio.ilegra.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.desafio.ilegra.model.Arquivo;
import com.desafio.ilegra.util.ArquivoUtils;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class HomePathController {
	
	@Value("${home.path.in}")
	private String homePathIN; 
	
	@Value("${home.path.process}")
	private String homePathProcess;
	
	@Autowired
	private ArquivoINController arquivoController;
	
	@Autowired
	public HomePathController(ArquivoINController arquivoController) {
		this.arquivoController = arquivoController;
	}
	
	public List<Arquivo> verificarPastaIN() {
		File diretorioVerificar = new File(homePathIN);
		try {
			return lerPastaIN(diretorioVerificar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * ler pasta IN em busca de arquivo e caso encontre processa-os transformando em
	 * Arquivo para o relatório
	 */
	
	private List<Arquivo> lerPastaIN(File diretorioIN) throws IOException{
		ArquivoUtils.criarDiretorio(diretorioIN);
		
		File arquivoINList[] = diretorioIN.listFiles(); 
		
		File diretorioINProcess = new File(homePathProcess);
		ArquivoUtils.criarDiretorio(diretorioINProcess);
		
		
		List<Arquivo> arquivos = new ArrayList<Arquivo>(); 
		
		for (File fileIN : arquivoINList) { 
			
			Arquivo arquivo = this.arquivoController.leituraArquivo(fileIN);
			
			ArquivoUtils.salvarArquivo(fileIN, new File(diretorioINProcess.getPath() + File.separator + fileIN.getName()));
			
			ArquivoUtils.removerArquivo(fileIN);
			
			arquivos.add(arquivo);
    		
		}
		System.gc();
		return arquivos;
	}
}
