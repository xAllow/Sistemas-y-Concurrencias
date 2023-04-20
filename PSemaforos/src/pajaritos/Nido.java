package pajaritos;

import java.util.concurrent.Semaphore;

public class Nido {
	private int num = 0;
	private int B = 4;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore noVacio = new Semaphore(0); //inicialmente vacia
	private Semaphore noLleno = new Semaphore(1);

	
	public void come(int id) throws InterruptedException {
		//el bebe id coge un bichito del nido
		noVacio.acquire(); //tiene que esperar a que el nido no este vacio
		//Aqui no vacio vale 0
		mutex.acquire();
		num--;
		if(num > 0) noVacio.release(); //si siguen quedando bichos se abre el semaforo
		if(num == B-1 ) noLleno.release();
		System.out.println("El bebé "+id+" ha comido un bichito. Quedan ");
		mutex.release();
	}
	
	public void nuevoBichito(int id)  {
		//el papa/mama id deja un nuevo bichito en el nido
		
		
		System.out.println("El papá "+id+" ha añadido un bichito. Hay ");
		
	}
}

//noVacio -> CS-Bebe-i: No puede comer del nido si está vacío
//noLLeno -> CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
