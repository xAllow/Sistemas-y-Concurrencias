package tiovivo;

import java.util.concurrent.Semaphore;



public class Tiovivo {
	private int tam;
	
	private int nPas = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore puedoBajar = new Semaphore(0);//cuando termina el viaje se bajan
	private Semaphore estaLleno = new Semaphore(0); //cuando esta lleno se inicia el viaje
	private Semaphore subir = new Semaphore(1);//inicialmente se suben los 5 pasajeros
	
	
	public Tiovivo(int tam) {
		this.tam = tam;
	}
	
	public void subir(int id) throws InterruptedException {	
		subir.acquire();
		mutex.acquire();
		nPas++;
		System.out.println("El pasajero "+id+" ha subido. Hay "+nPas+" pasajeros.");
		if(nPas < tam) {
			subir.release();
		} else {
			estaLleno.release();
		}
		mutex.release();
	}
	
	public void bajar(int id) throws InterruptedException {
		puedoBajar.acquire();
		mutex.acquire();
		nPas--;
		System.out.println("                                            El pasajero "+id+" ha bajado. Hay "+nPas+" pasajeros.");
		if(nPas == 0) {
			subir.release();
		} else {
			puedoBajar.release();
		}
		mutex.release();
	}
	
	public void esperaLleno () throws InterruptedException {
		estaLleno.acquire();
		System.out.println("Nos vamos de viaje!!!");
		
	}
	
	public void finViaje () {
			
			System.out.println("Ha terminado el viaje!!!");
			puedoBajar.release();
	}
}
