package com.desafio.ilegra.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.desafio.ilegra.controller.HomePathController;

/*
* Componente responsavel por iniciar a task que obsevará a pasta "/in"
* 
*/

@Component @EnableScheduling 
public class Observer {
	
	private final long INTERVALO = 5000; // 5 segundos
	
	@Autowired
	public HomePathController homePathController;
	
	public Observer(HomePathController homePathController) {
		this.homePathController = homePathController;
	}
	
	@Scheduled(fixedDelay = INTERVALO) 
	public void start(){
		this.homePathController.verificarPastaIN();
	}
}
