package babuinos;

import java.util.concurrent.Semaphore;

public class Cuerda {
	private static final int MAX_Babuinos = 3;
	private int nBabuinosNS = 0; 
	private int nBabuinosSN = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperaNS = new Semaphore(0);
	private Semaphore esperaSN = new Semaphore(0);


	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionNS(int id) throws InterruptedException{
		mutex.acquire();
		if(nBabuinosNS == 3 || nBabuinosSN > 0){
			mutex.release();
			esperaNS.acquire();
			mutex.acquire();
		}
		
		nBabuinosNS++;
		System.out.println("El babuino " + id + " se ha subido de NS, hay " + nBabuinosNS);
		if(nBabuinosNS < MAX_Babuinos) esperaNS.release();
		mutex.release();
	}
	/**
	 * Utilizado por un babuino cuando quiere cruzar el cañón  colgándose de la
	 * cuerda en dirección Norte-Sur
	 * Cuando el método termina, el babuino está en la cuerda y deben satisfacerse
	 * las dos condiciones de sincronización
	 * @param id del babuino que entra en la cuerda
	 * @throws InterruptedException
	 */
	public  void entraDireccionSN(int id) throws InterruptedException{
		mutex.acquire();
		if(nBabuinosSN >= 3 || nBabuinosNS > 0){
			mutex.release();
			esperaSN.acquire();
			mutex.acquire();
		}
		
		nBabuinosSN++;
		System.out.println("El babuino " + id + " se ha subido de SN, hay " + nBabuinosSN);
		if(nBabuinosSN < MAX_Babuinos ) esperaSN.release();
		mutex.release();
	}
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Norte-Sur
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionNS(int id) throws InterruptedException{
		mutex.acquire();
		nBabuinosNS--;
		System.out.println("El babuino " + id + " sale NS, hay " + nBabuinosNS);
		if(nBabuinosNS == 0 ) esperaSN.release();
		mutex.release();
	}
	
	/**
	 * Utilizado por un babuino que termina de cruzar por la cuerda en dirección Sur-Norte
	 * @param id del babuino que sale de la cuerda
	 * @throws InterruptedException
	 */
	public  void saleDireccionSN(int id) throws InterruptedException{
		mutex.acquire();
		nBabuinosSN--;
		System.out.println("El babuino " + id + " sale SN, hay " + nBabuinosSN);
		if(nBabuinosSN == 0 ) esperaNS.release();
		

		mutex.release();
	}	
		
}
