package com.desafio.ilegra.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.desafio.ilegra.util.ArquivoUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class HomePathController {
	
//	@Value("${home.path.in}")
	@Getter
	private String homePathIN = "C://homepath//home//in";

//	@Value("${home.path.out}")
	@Getter
	private String homePathOUT = "C://homepath//home//out";
	
	@Autowired
	ArquivoController arquivoController;
	
	@Autowired
	public HomePathController(ArquivoController arquivoController) {
		this.arquivoController = arquivoController;
	}
	
	public boolean verificarPastaIN() {
		File diretorioVerificar = new File(homePathIN);
		try {
			return moverArquivosDaPastaIN(diretorioVerificar);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean moverArquivosDaPastaIN(File diretorioIN) throws IOException{
		File arquivoINList[] = diretorioIN.listFiles(); 
		File diretorioOUT = new File(homePathOUT);
		boolean retorno = false;
		for (File fileIN : arquivoINList) { 
			
			this.arquivoController.leituraArquivo(fileIN);
			
			FileInputStream fisDestinoFinal;
			ArquivoUtils.salvarArquivo(fileIN, new File(diretorioOUT.getPath() + File.separator + fileIN.getName()));
    		fisDestinoFinal = new FileInputStream(new File(diretorioOUT.getPath() + File.separator + fileIN.getName()));
        	fisDestinoFinal.close();
        	retorno = true;
        	fileIN.delete();
		}
		System.gc();
		return retorno;
	}
}
