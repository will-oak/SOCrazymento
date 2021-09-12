package controller;

import java.util.concurrent.Semaphore;

public class ThreadCar extends Thread {

	private int idCarro;
	private static int posSaida;
	private Semaphore semaforo;
	private static int[] vetPosicoes = new int[4];
	private String sentido;
	
	public ThreadCar(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {

		try {
			semaforo.acquire();
			carroEstacionado();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		carroSaindo();	
	}


	private void carroEstacionado() {
		for (int i = 0 ; i < 4 ; i++) {
			if (vetPosicoes[i] == 0) {
				vetPosicoes[i] = idCarro;
				if (vetPosicoes[i] == 1) {
					sentido = "a esquerda";
					
					
				}
				if  (vetPosicoes[i] == 2) {
					sentido = "a direita";
					
				}
				 if  (vetPosicoes[i] == 3) {
					sentido = "para cima";
					
				}
				if  (vetPosicoes[i] == 4) {
					sentido = "para baixo";
					
				}
				
				break;
			}
		}
		int tempoParado = (int)((Math.random()* 0) + 1);
		try {
			sleep(tempoParado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void carroSaindo() {
		posSaida++;
		System.err.println("O Carro "+idCarro+" foi o "+
				posSaida+"º a atravessar no sentido "+sentido+" que estava livre");
	}

}

