package parejas;

import java.util.concurrent.locks.*;

public class Sala {
	  
	private Lock l = new ReentrantLock();

	private boolean hayHombre = false;
	private boolean hayMujer = false;
	private boolean hayCita = false;

	private Condition cEsperaSala = l.newCondition();
	private Condition cEsperaHombre = l.newCondition();
	private Condition cEsperaMujer = l.newCondition();
	 /**
	 * un hombre llega a la sala para formar una pareja
	 * si ya hay otra mujer en la sala o si aún no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public void llegaHombre(int id) throws InterruptedException {
		l.lock();
		try{
			while(hayHombre || hayCita){
				cEsperaHombre.await();
			}
			hayHombre = true;
			System.out.println("Llega hombre " + id);
			cEsperaSala.signal();;
			if (hayMujer) {
				hayCita = true;
				System.out.println("	Hay cita");
			} else {
				while (!hayCita) {
					cEsperaSala.await();
				}
				hayCita = false;
				cEsperaHombre.signal();
				cEsperaMujer.signal();
			}
			hayHombre = false;
			System.out.println("		Sale hombre " + id);
			} finally {
				l.unlock();
			}
	}

	/**
	 * una mujer llega a la sala para formar una pareja
	 * debe esperar si ya hay otra mujer en la sala o si aún no hay un hombre
	 * 
	 * @throws InterruptedException
	 */
	public void llegaMujer(int id) throws InterruptedException {
		l.lock();
		try {
			while(hayMujer || hayCita){
				cEsperaMujer.await();
			}
			hayMujer = true;
			System.out.println("Llega mujer " + id);
			cEsperaSala.signal();
			if (hayHombre) {
				hayCita = true;
				System.out.println("	Hay cita");
			} else {
				while (!hayCita) {
					cEsperaSala.await();
				}
				hayCita = false;
				cEsperaHombre.signal();
				cEsperaMujer.signal();
			}
			hayMujer = false;
			System.out.println("		Sale mujer " + id);
		
		} finally {
			l.unlock();
		}
		
	}
}
	

