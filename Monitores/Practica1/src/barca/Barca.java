package barca;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Barca extends Thread{
	private static final int C = 4;
	private int nAnd = 0;
	private int nIph = 0;
	
	private boolean puertaAnd = true; //CS-andr
	private boolean puertaIph = true; //CS-iph
	private boolean puertaSal = false; //CS-andr + iphone
	
	
	public Barca(){
		
	}
	
	
	/**
	 * Un estudiante con mÃ³vil android llama a este mÃ©todo 
	 * cuando quiere cruzar el rÃ­o. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * mÃ©todo
	 * @param id del estudiante android que llama al mÃ©todo
	 * @throws InterruptedException
	 */
	public synchronized void android(int id) throws InterruptedException{
		while(!puertaAnd) wait();
		nAnd++;
		System.out.println("Android " + id + " se sube a la barca");
		if(nAnd == 3 || (nIph == 2 && nAnd == 1)) puertaIph = false;
		
		if(nAnd == 2 && nIph == 1) puertaAnd = false;
		
		if(nAnd + nIph < 4) {
			while(!puertaSal) wait();
		} else {
			puertaSal = true;
			puertaAnd = false;
			notifyAll();
		}
		
	}
	
	/**
	 * Un estudiante con mÃ³vil android llama a este mÃ©todo 
	 * cuando quiere cruzar el rÃ­o. Debe esperarse a montarse en la
	 * barca de forma segura, y a llegar a la otra orilla del antes de salir del
	 * mÃ©todo
	 * @param id del estudiante android que llama al mÃ©todo
	 * @throws InterruptedException
	 */

	public synchronized void iphone(int id) throws InterruptedException{
		
		
	}
	
	
	
	

}