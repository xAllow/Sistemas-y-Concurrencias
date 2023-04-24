package guarderia;

import java.util.concurrent.Semaphore;

public class Guarderia {
	private int nBebes = 0;
	private int nAdulto = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore entraBebe = new Semaphore(0);
	private Semaphore saleAdulto = new Semaphore(0);
	
	/*
	private Semaphore entraAdulto = new Semaphore(1);
	private Semaphore entraBebe = new Semaphore(0);
	private Semaphore saleAdulto = new Semaphore(0);
	private Semaphore saleBebe = new Semaphore(0);
	*/
	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuando entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		entraBebe.acquire();
		mutex.acquire();
		nBebes++;
		if(nBebes <= 3 * nAdulto) {
			System.out.println("Entra el bebe "+id+". Hay: " + nBebes + " bebes.");
			entraBebe.release();
		} else {
			nBebes--;
		}
		mutex.release();
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutex.acquire();
		if(nBebes > 0) {
			nBebes--;
			System.out.println("Sale el bebe " + id + ". Hay: " + nBebes + " bebes.");
		}
		if (nBebes < 3 * nAdulto) {
            saleAdulto.release();  // Libera el semáforo para permitir la salida de un adulto.
        }
		mutex.release();
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutex.acquire();
		nAdulto++;
		System.out.println("Entra el adulto " + id + ". Hay: " + nAdulto + " adultos");
		if (nBebes <= 3 * nAdulto) {
            entraBebe.release();
        }
		mutex.release();
		
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		saleAdulto.acquire();
		mutex.acquire();
		int adultoFuera = nAdulto - 1;
		if (nBebes <= 3*adultoFuera) {
			nAdulto--;
			System.out.println("Sale el adulto " + id + ". Hay: "+nAdulto + " adultos");
		} else {
			mutex.release();
			entraBebe.acquire();
			mutex.acquire();
		}
		mutex.release();
		
	}

}
