package com.desafio.ilegra.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/*
* Utilitário para controle de arquivos nas pastas
* 
*/
public abstract class ArquivoUtils {

	public static void criarDiretorio(File diretorio) {
		if(!diretorio.exists()) {
			diretorio.mkdirs();
		}
	}
	
	public static void salvarArquivo(File arquivoIN, File diretorioOUT) throws IOException { 
		FileUtils.copyFile(arquivoIN, diretorioOUT);
	}
	
	public static void removerArquivo(File arquivo) throws IOException {
    	arquivo.delete();
	}
	
	public static void criarArquivo(String src, File dst) throws IOException {
		Files.write( Paths.get(dst.getAbsolutePath()), src.getBytes());
    }

}
