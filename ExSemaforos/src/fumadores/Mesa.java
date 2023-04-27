package fumadores;

import java.util.concurrent.Semaphore;

public class Mesa {
	private Semaphore agente = new Semaphore(1); //CS-agente pone los ingredientes cada vez 
												//	que el fumador termina de fumar
	private Semaphore[] fumador = new Semaphore[3];
	
	public Mesa(){
		for(int i = 0; i < 3; i++) {
			fumador[i] = new Semaphore(0);
		}
	}
	
	public void qFumar(int id) throws InterruptedException {
		fumador[id].acquire();
		System.out.println("Fumador "+id+" coge los ingredientes");
		
	}
	public void finFumar(int id) {
		System.out.println("Fumador "+id+" ha terminado de fumar");
		agente.release();
		
	}
	public void nuevosIng(int ing) throws InterruptedException { // se pasa el ingrediente que no se pone
		agente.acquire();
		System.out.print("El agente ha puesto los ingredientes ");
		if (ing==0) System.out.println("1 y 2");
		else if (ing == 1) System.out.println("0 y 2");
		else System.out.println("0 y 1");
		fumador[ing].release();
		
	}

}

//CS-Fumador i: No puede fumar hasta que el fumador anterior no ha terminado
// de fumar y sus ingredientes están sobre la mesa
//CS-Agente: no puede poner nuevos ingredientes hasta que el fumador anterior 
//no ha terminado de fumar
