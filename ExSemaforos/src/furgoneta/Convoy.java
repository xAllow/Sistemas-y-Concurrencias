package furgoneta;
import java.util.concurrent.*;
public class Convoy {
	private int tam;
	private int convoy = 0;
	private int lider;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore inicioViaje = new Semaphore(0);
	private Semaphore finViaje = new Semaphore(0);
	private Semaphore espera = new Semaphore(0);
	public Convoy(int tam) {
		this.tam = tam;
	}
	
	/**
	 * Las furgonetas se unen al convoy
	 * La primera es la lider, el resto son seguidoras 
	 * @throws InterruptedException 
	 **/
	public int unir(int id) throws InterruptedException{
		//TODO: Poner los mensajes donde corresponda
		mutex.acquire();
		convoy++;
		if(convoy == 1) {
			System.out.println("** Furgoneta " +id + " lidera del convoy **");
			lider = id;
		} else {
			System.out.println("Furgoneta "+id+" seguidora");
		}
		if(convoy == tam) inicioViaje.release();
		mutex.release();
		return lider;
	}

	/**
	 * La furgoneta lider espera a que todas las furgonetas se unan al convoy 
	 * Cuando esto ocurre calcula la ruta y se pone en marcha
	 * @throws InterruptedException 
	 * */
	public void calcularRuta(int id) throws InterruptedException{
		inicioViaje.acquire();
		System.out.println("** Furgoneta "+id+" lider:  ruta calculada, nos ponemos en marcha **");
	}
	
	
	/** 
	 * La furgoneta lider avisa al las furgonetas seguidoras que han llegado al destino y deben abandonar el convoy
	 * La furgoneta lider espera a que todas las furgonetas abandonen el convoy
	 * @throws InterruptedException 
	 **/
	public void destino(int id) throws InterruptedException{
		System.out.println("** Furgoneta "+id+" lider: hemos llegado al destino **");
		finViaje.release();
		espera.acquire();
		System.out.println("** Furgoneta "+id+" lider: abandona el convoy **");
	}

	/**
	 * Las furgonetas seguidoras hasta que la lider avisa de que han llegado al destino
	 * y abandonan el convoy
	 * @throws InterruptedException 
	 **/
	public void seguirLider(int id) throws InterruptedException{
		finViaje.acquire();
		mutex.acquire();
		convoy--;
		System.out.println("Furgoneta "+id+" abandona el convoy");
		if(convoy > 1) finViaje.release();
		if(convoy == 1) espera.release();
		mutex.release();
	}

	
	
	/**
	* Programa principal. No modificar
	**/
	public static void main(String[] args) {
		final int NUM_FURGO = 10;
		Convoy c = new Convoy(NUM_FURGO);
		Furgoneta flota[ ]= new Furgoneta[NUM_FURGO];
		
		for(int i = 0; i < NUM_FURGO; i++)
			flota[i] = new Furgoneta(i,c);
		
		for(int i = 0; i < NUM_FURGO; i++)
			flota[i].start();
	}

}
