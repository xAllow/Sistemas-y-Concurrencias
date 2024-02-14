package fiesta;

import java.util.concurrent.locks.*;

public class Bandeja {
	
	
	private int R = 8; // numero de raciones por pastel
	private int racionesDisponibles = 0;
	private Lock l = new ReentrantLock();
	private boolean tartaLista = false;
	private boolean pasteleroAvisado = false;

	private Condition cesperaNiño = l.newCondition();
	private Condition cesperaPastelero = l.newCondition();
	private Condition cPastelero = l.newCondition();



	/**
	 * Un niño que quiere una ración de tarta llama a este método para
	 * cogerla de la bandeja. 
	 * El niño debe esperar si  la bandeja está vacía a que el pastelero
	 * ponga una nueva tarta  
	 * @param id del niño que pide la ración
	 * @throws InterruptedException
	 */
	//System.out.println("Ni�o " + id + " coge racion. Quedan: " + R);
	public void quieroRacion(int id) throws InterruptedException{
		l.lock();
		try{
			while(pasteleroAvisado){ //Mientra que el pastelero esta haciendo la tarta esperan
				cesperaNiño.await();
			}
			if(racionesDisponibles == 0){
				pasteleroAvisado = true;
				System.out.println("Avisa al pastelero " + id);
				cPastelero.signal();
				while(!tartaLista){
					cesperaPastelero.await(); //Espera a que el pastelero haga la tarta
				}
				tartaLista = false; // al crear la tarta se pone en true, hay que volver a ponerlo en false
				pasteleroAvisado = false;
				cesperaNiño.signal();
			}
			racionesDisponibles--;
			System.out.println("Niño " + id + " coge racion. Quedan: " + racionesDisponibles);
		} finally{
			l.unlock();
		}
	}
	
	/**
	 * El pastelero llama a este método para poner una nueva tarta en
	 * la bandeja. Tiene que esperar a que la bandeja esté vacía para ponerla.
	 * @throws InterruptedException
	 */
	
	public void tarta() throws InterruptedException{
		l.lock();
		try{
			while(racionesDisponibles > 0) cPastelero.await();
			if(pasteleroAvisado){ //Si avisan al pastelero, hace la tarta
				tartaLista = true;
			}
			racionesDisponibles = R;
			System.out.println("Pastelero pone una tarta, raciones:" + racionesDisponibles);
			cesperaPastelero.signal();
		} finally{
			l.unlock();
		}
	
	}
	
}
