package mrusa;

import java.util.concurrent.*;
public class Coche extends Thread{
	private int C;
	private int num = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore estaLleno = new Semaphore(0);
	private Semaphore hayEspacio = new Semaphore(1);
	private Semaphore viaje = new Semaphore(0);
	public Coche(int cap){
		C = cap;
	}
	
	public void nuevoViaje(int id) throws InterruptedException{
		hayEspacio.acquire();
		mutex.acquire();
		num++;
		System.out.println("Se sube: "+id+" Hay: "+num);
		if(num < C) {
			hayEspacio.release();
		} else {
			estaLleno.release();
		}
		mutex.release();
	}
	
	public void esperaLleno() throws InterruptedException{
		estaLleno.acquire();
		System.out.println("Nos vamos de viaje!!!");
		viaje.release();
	}
	
	public void finViaje() throws InterruptedException{
		viaje.acquire();
		System.out.println("Ha terminado el viaje");
		num = 0;
		hayEspacio.release();
		
		
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
