package lecesc;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class GestorBD { //Recurso
	//solucion injusta para los escritores
	
	private Lock l = new ReentrantLock(true);
	private boolean escribiendo = false;
	private int nLectores = 0;
	private int nEscritores = 0;
	private Condition cLectores = l.newCondition();
	private Condition cEscritores = l.newCondition();
	
	public  void entraLector(int id) throws InterruptedException {
		l.lock();
		try {
			while(escribiendo || nEscritores > 0) {
				cLectores.await();
			}
			nLectores++;
			System.out.println("Entra lector "+ id + " nLectores: "+ nLectores);
		} finally {
			l.unlock();
		}
		
	}
	
	public  void saleLector(int id) throws InterruptedException {
		//usado por el lector id para salir de la BD
		l.lock();
		try {
			nLectores--;
			if(nLectores == 0) cEscritores.signal();
			System.out.println("Sale lector "+ id + " nLectores: " +nLectores);
		} finally {
			l.unlock();
		}		
	
	}

	public  void entraEscritor(int id) throws InterruptedException {
		l.lock();
		try {
			nEscritores++;
			while(nLectores>0 || escribiendo) {
				cEscritores.await();
			}
			System.out.println("Entra escritor "+ id + " nLectores: " +nEscritores);
			escribiendo = true;
		} finally {
			l.unlock();
		}		
	}
	
	public  void saleEscritor(int id) {
		l.lock();
		try {
			nEscritores--;
			System.out.println("Sale escritor "+ id + " nLectores: " + nEscritores);
			escribiendo = false;
			if(nEscritores > 0) cEscritores.signal();
			else cLectores.signalAll(); 
		} finally {
			l.unlock();
		}		
	}
}

//CS-E: exclusión mutua para el uso de la BD
//CS-L: puede haber cualquier número de lectores en la BD