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

/*
package controller;

import java.util.concurrent.Semaphore;

public class ThreadCar extends Thread {

	private int idCarro;
	private static int posChegada=0;
	private static int posSaida;
	private Semaphore semaforo;
	private static int[] vetPosicoes = new int[4];
	private int vaga;
	private String sentido;
	
	public ThreadCar(int idCarro, Semaphore semaforo, int direcao ) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		carroChegando();		//Paralelizável
//		====Início da Seção Crítica====
		try {
			semaforo.acquire();
			carroParado();	
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release(); // no máximo 1
		}
//		=====Fim da Seção Crítica=====
		carroSaindo();		//Paralelizável
	}
	
	private void carroChegando() {
				while (posChegada < 4 || vaga <=4) {
				vaga += 1;
				
	}
	}

	private void carroParado() {
		
		
		if (vaga == 0) {
			sentido = "a esquerda";
			System.err.println("Carro #"+idCarro+" parou na posição do cruzamento "+ sentido);
			
		}
		if  (vaga == 1) {
			sentido = "a direita";
			System.err.println("Carro #"+idCarro+" parou na posição do cruzamento "+ sentido);
		}
		 if  (vaga == 2) {
			sentido = "em cima";
			System.err.println("Carro #"+idCarro+" parou na posição do cruzamento "+ sentido);
		}
		if (vaga == 3) {
			sentido = "em baixo";
			System.err.println("Carro #"+idCarro+" parou na posição do cruzamento "+ sentido);
		}
		 
		}
		


/*	private void carroEstacionado() {
		for (int i = 0 ; i < 4 ; i++) {
			if (vetPosicoes[i] == 0) {
				vetPosicoes[i] = idCarro;
				break;
			}
				System.err.println("Carro #"+idCarro+" estacionou no cruzamento " 
						+ sentido );
				vaga = i;
				
		}
		int tempoParado = (int)((Math.random()* 2001) + 1000);
		try {
			sleep(tempoParado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		vetPosicoes[vaga] = 0;
		
	
	}

	private void carroSaindo() {
		posSaida++;
		System.err.println("Carro #"+idCarro+" foi o "+
				posSaida+"o. a sair.");
		
	}

}
*/