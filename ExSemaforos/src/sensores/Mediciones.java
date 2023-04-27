package sensores;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class Mediciones {
	private Semaphore[] sensores = new Semaphore[3];
	private Semaphore worker = new Semaphore(0);
	private int nMed = 0;
	private Semaphore mutex = new Semaphore(1);
	
	public Mediciones() {
		for (int i = 0; i < sensores.length; i++) {
			sensores[i] = new Semaphore(0);
		}
	}
	
	public void nuevaMedicion(int id) throws InterruptedException {
		//el sensor id deja una nueva medición
		mutex.acquire();
		nMed++;
		System.out.println("Sensor "+id+" deja sus mediciones");
		if(nMed == 3) {
			worker.release();
		} 
		
		mutex.release();
		sensores[id].acquire();
	}
	
	public void recogerMediciones() throws InterruptedException  {
		//el worker coge las tres mediciones
		worker.acquire();
		mutex.acquire();
		System.out.println("Worker ha recogido las mediciones");
		nMed = 0;
		mutex.release();
	}
	
	public void finTrabajo() throws InterruptedException {
		//lo llama el worker cuando ha procesado las mediciones
		System.out.println("Worker ha procesado las mediciones");
		for (int i = 0; i < sensores.length; i++) {
			sensores[i].release();
		}
		
	}
	
	//CS-Worker: No puede recoger las mediciones hasta que no están las tres
	//CS-Sensor-i: No puede medir hasta que el worker no ha procesado
	//las medidas de la interación anterior

}
