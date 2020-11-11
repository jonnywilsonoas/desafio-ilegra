package com.desafio.ilegra.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

/*
* Utilitário para controle de arquivos nas pastas
* 
*/
public abstract class ArquivoUtils {

	public static void salvarArquivo(File arquivoIN, File diretorioOUT) throws IOException { 
		FileUtils.copyFile(arquivoIN, diretorioOUT);
	}

	private static int contarLinhasArquivo(File arquivo) throws FileNotFoundException{
		FileReader arquivoFisico = new FileReader(arquivo);
		BufferedReader leitor = new BufferedReader(arquivoFisico);
		int linhas = 0;

		try {
			while(leitor.readLine() != null){
				linhas++;
			}
			leitor.close();
		} catch (IOException e) {

		}
		return linhas;
	}

	public static List<String> listarArquivosDeUmDiretorio(String path){
		List<String> caminhos = new ArrayList<>();
		File diretorio = new File(path);
		File[] arquivos = diretorio.listFiles();

		if(arquivos != null) {
			for(File arquivo: arquivos) {
				caminhos.add(arquivo.getAbsolutePath());
			}
		}

		return caminhos;
	}

}
