package com.desafio.ilegra.util;

/*
* Utilitário para conversão de dados
* 
*/

public class ConversorUtils {

	/*
	 * public static long convertStringToMilliseconds(int segundos){ long segundo =
	 * segundos * 1000; long frenqueciaMilissegundo = segundo; return
	 * frenqueciaMilissegundo; }
	 */
	
	public static String getValorAsString(String linha, int inicioIndex, int finalIndex){
		return linha.substring(inicioIndex - 1, finalIndex);
	}
}
