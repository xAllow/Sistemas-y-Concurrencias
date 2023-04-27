package guarderia2;

import java.util.concurrent.Semaphore;

public class Guarderia {
	private int nBebes = 0;
	private int nAdulto = 0;
	private Semaphore mutex1 = new Semaphore(1);
	private Semaphore entraBB = new Semaphore(0);
	private Semaphore saleAD = new Semaphore(0);
	

	/**
	 * Un bebe que quiere entrar en la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro entrar, es decir, hasta que 
	 * cuando entre haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void entraBebe(int id) throws InterruptedException{
		mutex1.acquire();
		if ((nBebes+1) <= 3*nAdulto ) {
			entraBB.release();
		}
		mutex1.release();
		entraBB.acquire();
		mutex1.acquire();
		nBebes++;
		System.out.println("Entra el bebe "+id+". Hay: " + nBebes + " bebes.");
		
		mutex1.release();
	}
	/**
	 * Un bebe que quiere irse de la guarderia llama a este metodo * 
	 */
	public void saleBebe(int id) throws InterruptedException{
		mutex1.acquire();
		nBebes--;
		System.out.println("Sale el bebe " + id + ". Hay: " + nBebes + " bebes.");
		mutex1.release();
		
	}
	/**
	 * Un adulto que quiere entrar en la guarderia llama a este metodo * 
	 */
	public void entraAdulto(int id) throws InterruptedException{
		mutex1.acquire();
		nAdulto++;
		System.out.println("Entra el adulto " + id + ". Hay: " + nAdulto + " adultos");
		mutex1.release();
		
	}
	
	/**
	 * Un adulto que quiere irse  de la guarderia llama a este metodo.
	 * Debe esperar hasta que sea seguro salir, es decir, hasta que
	 * cuando se vaya haya, al menos, 1 adulto por cada 3 bebes
	 * 
	 */
	public void saleAdulto(int id) throws InterruptedException{
		if((nBebes)<=(3*(nAdulto-1))) {
			saleAD.release();
		}
		saleAD.acquire();
		mutex1.acquire();
		nAdulto--;
		System.out.println("Sale el adulto " + id + ". Hay: "+nAdulto + " adultos");
		mutex1.release();
		
	}

}
