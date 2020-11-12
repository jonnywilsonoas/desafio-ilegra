package com.desafio.ilegra.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.desafio.ilegra.model.Arquivo;
import com.desafio.ilegra.util.ArquivoUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class RelatorioController {

	@Value("${home.path.out}")
	@Getter
	private String homePathOUT;

	public void montarRelatorio(List<Arquivo> arquivos) throws IOException{
		File diretorioOUT = new File(homePathOUT);
		ArquivoUtils.criarDiretorio(diretorioOUT);
		
		for(Arquivo arquivo : arquivos) {
			File outFile = new File(homePathOUT + File.separator + arquivo.getNomeArquivo());
			
			ArquivoUtils.criarArquivo(arquivo.toString(), outFile);
		}
		
	}
}
