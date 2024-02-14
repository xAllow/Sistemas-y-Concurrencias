package pajaritos;

import java.util.concurrent.Semaphore;

public class Nido {
	private Semaphore papa = new Semaphore(1); //Pone bichos mientras haya hueco
	private Semaphore puedeComer = new Semaphore(0); //Inicialmente no hay bichos
	private int cont = 0;
	private int B = 4; //Un maximo numero de bichos en el plato
	private Semaphore mutex = new Semaphore(1);
	
	public void come(int id) throws InterruptedException {
		//el bebe id coge un bichito del nido
		puedeComer.acquire();
		mutex.acquire();
		cont--;
		System.out.println("El bebé "+id+" ha comido un bichito. Quedan "+cont);
		if(cont == B - 1) {
			mutex.release();
			papa.release();
			mutex.acquire();
		} 
		if (cont > 0) {
			puedeComer.release();
		}
		mutex.release();
		
	}
	
	public void nuevoBichito(int id) throws InterruptedException  {
		//el papa/mama id deja un nuevo bichito en el nido
		papa.acquire();
		mutex.acquire();
		cont++;
		System.out.println("El papá "+id+" ha añadido un bichito. Hay "+cont);
		if (cont < B) {
			papa.release();
		}
		if(cont == 1) puedeComer.release();
		mutex.release();
	}
}

//CS-Bebe-i: No puede comer del nido si está vacío
//CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
