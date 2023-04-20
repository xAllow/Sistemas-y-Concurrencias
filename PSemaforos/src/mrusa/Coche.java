package mrusa;

import java.util.concurrent.*;
public class Coche extends Thread{
	private int C;
	private int numPasajeros = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore viaje = new Semaphore(0); //CS-coche
	private Semaphore espera = new Semaphore(0);
	private Semaphore subir = new Semaphore(1);



	public Coche(int cap){
		C = cap;
	}
	
	public void nuevoViaje(int id) throws InterruptedException{
		subir.acquire();
		mutex.acquire();
		numPasajeros++;
		System.out.println("Pasajero "+id+ "se sube a la mrusa. Hay " + numPasajeros);
		if(numPasajeros <C ){
			subir.release();
		} else {
			viaje.release();
		}
		mutex.release();
		espera.acquire();

		mutex.acquire();
		numPasajeros--;
		if(numPasajeros > 0){
			espera.release();
		} else {
			subir.release();
		}

		mutex.release();
	}
	
	public void esperaLleno() throws InterruptedException{
		viaje.acquire();
		System.out.println("Empieza el viaje de la mrusa");

	}
	
	public void finViaje(){
		System.out.println("Fin del viaje!!!");

	}

	public void run(){
		while (true){
			
			try {
				this.esperaLleno();
				Thread.sleep(200);
				this.finViaje();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
