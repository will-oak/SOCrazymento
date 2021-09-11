package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCar;

public class Estacionamento {

	public static void main(String[] args) {
		
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for (int idCarro = 1 ; idCarro <= 4 ; idCarro++) {
			Thread tCarro = new ThreadCar(idCarro, semaforo);
			tCarro.start();
		}
		
	}

}
