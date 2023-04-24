package barca;
import java.util.concurrent.*;
public class Barca {

	private int orilla = 1;
	private int nPas = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore subirBarco0 = new Semaphore(0);
	private Semaphore subirBarco1 = new Semaphore(1);
	private Semaphore bajarBarco = new Semaphore(0);
	private Semaphore estaLleno = new Semaphore(0);
	
	/*
	 * El Pasajero id quiere darse una vuelta en la barca desde la orilla pos
	 */
	public void subir(int id, int pos) throws InterruptedException {
		if(pos == 0) {
			subirBarco0.acquire();
			mutex.acquire();
			nPas++;
			System.out.println("Viajero " + id + " se sube al barco en la orilla " + pos);
			if (nPas < 3) {
				subirBarco0.release();
			} else {
				estaLleno.release();
			}
			mutex.release();
			
		} else {
			subirBarco1.acquire();
			mutex.acquire();
			nPas++;
			System.out.println("Viajero " + id + " se sube al barco en la orilla " + pos);
			if (nPas < 3) {
				subirBarco1.release();
			} else {
				estaLleno.release();
			}
			mutex.release();
		}
		
	}
	
	/*
	 * Cuando el viaje ha terminado, el Pasajero que esta en la barca se baja
	 */
	public int bajar(int id) throws InterruptedException {
		bajarBarco.acquire();
		
		nPas--;
		System.out.println("Viajero " + id + " se baja del barco");
		if(nPas == 0) {
			if(orilla == 0) {
				subirBarco0.release();
			}else {
				subirBarco1.release();
			}
			System.out.println("Barca vacia pueden subir nuevos pasajeros");
		} else {
			bajarBarco.release();
		}
		
		return orilla;
	}
	
	/*
	 * El Capitan espera hasta que se suben 3 pasajeros para comenzar el viaje
	 */
	public void esperoSuban() throws InterruptedException {
		estaLleno.acquire();
		System.out.println("Empieza el viaje!!!");
	}
	
	/*
	 * El Capitan indica a los pasajeros que el viaje ha terminado y tienen que bajarse
	 */
	public void finViaje() throws InterruptedException {
		
		orilla = (orilla +1)%2;	
		System.out.println("El viaje ha terminado!!!");
		bajarBarco.release();
	}

}
