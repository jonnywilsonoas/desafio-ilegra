package com.desafio.ilegra.listener;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desafio.ilegra.controller.HomePathController;
import com.desafio.ilegra.controller.RelatorioController;
import com.desafio.ilegra.model.Arquivo;

/*
* Componente responsavel por iniciar a task que obsevará a pasta "/in"
* 
*/

@Component @EnableScheduling 
public class Observer {
	
	// scheduled do springo boot aceita apenas constants
	private final long INTERVALO = 5000; // 5 segundos
	
	@Autowired
	public HomePathController homePathController;
	
	@Autowired
	private RelatorioController RelatorioController; 
	
	public Observer(HomePathController homePathController) {
		this.homePathController = homePathController;
	}
	
	@Scheduled(fixedDelay = INTERVALO) 
	public void start(){
		Logger logger = LogManager.getLogger(getClass());

		logger.info("=== Lendo pasta IN ===");
		List<Arquivo> arquivos = this.homePathController.verificarPastaIN();
		
		try {
			logger.info("=== Criando relatórios ===");
			this.RelatorioController.montarRelatorio(arquivos);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
