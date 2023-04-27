package unisex;

import java.util.concurrent.Semaphore;

public class Aseo {
	private int nHombre = 0;
	private int nMujeres = 0;
	private Semaphore mutex = new Semaphore(1);	
	private Semaphore esperaHombre = new Semaphore(0);
	private Semaphore esperaMujer = new Semaphore(0);


	
	/**
	 * El hombre id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay alguna mujer en ese
	 * momento en el aseo
	 */
	public void llegaHombre(int id) throws InterruptedException{
		mutex.acquire();
		while(nMujeres > 0){
			mutex.release();
			esperaHombre.acquire();
			mutex.acquire();
		}
		nHombre++;
		if(esperaHombre.availablePermits() == 0) esperaHombre.release();
		System.out.println("El hombre " + id + " entra en el aseo, hay " + nHombre);
		mutex.release();

	}
	/**
	 * La mujer id quiere entrar en el aseo. 
	 * Espera si no es posible, es decir, si hay algun hombre en ese
	 * momento en el aseo
	 */
	public void llegaMujer(int id) throws InterruptedException{
		mutex.acquire();
		while(nHombre > 0){
			mutex.release();
			esperaMujer.acquire();
			mutex.acquire();
		}
		nMujeres++;
		if(esperaMujer.availablePermits() == 0) esperaMujer.release();
		System.out.println("La mujer " + id + " entra en el aseo, hay " + nMujeres);
		mutex.release();
	}
	/**
	 * El hombre id, que estaba en el aseo, sale
	 */
	public void saleHombre(int id)throws InterruptedException{
		mutex.acquire();
		nHombre--;
		System.out.println("Sale el hombre " + id + " hay " + nHombre);
		if(nHombre == 0 && esperaMujer.availablePermits() == 0) esperaMujer.release();
		mutex.release();
	}
	
	public void saleMujer(int id)throws InterruptedException{
		mutex.acquire();
		nMujeres--;
		System.out.println("Sale la mujer " + id + " hay " + nMujeres);
		if(nMujeres == 0 && esperaHombre.availablePermits() == 0) esperaHombre.release();
		mutex.release();
	}
}
