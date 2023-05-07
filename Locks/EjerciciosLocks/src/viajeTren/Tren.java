package viajeTren;
import java.util.concurrent.locks.*;

public class Tren {
	private Lock l = new ReentrantLock();
	private int N = 5;
	private int nvagon1 = 0;
	private int nvagon2 = 0;
	private Condition esperaVagon1 = l.newCondition();
	private Condition esperaVagon2 = l.newCondition();
	private Condition subirse = l.newCondition();
	private Condition viaje = l.newCondition();
	private boolean puedeBajar = false;
	private boolean seHaBajado1 = false;
	private boolean hayGente = false;
	private boolean enMarcha = false;
	
	
	public void viaje(int id) throws InterruptedException {
		l.lock();
		try{
			
			while(enMarcha || hayGente) subirse.await();
			if(nvagon1 < N){
				nvagon1++;
				System.out.println("Se ha subido el pasajero " + id + " al vagon1. Hay:" + (nvagon1+nvagon2));
				//completar
				puedeBajar = false;
				while(!puedeBajar) esperaVagon1.await();
				nvagon1--;
				System.out.println("Se ha bajado el pasajero " + id + " del vagon1. Hay:" + (nvagon1+nvagon2));
				if(nvagon1 == 0){
					seHaBajado1 = true;
					esperaVagon2.signalAll();
				}
				
			} else {
				nvagon2++;
				System.out.println("	Se ha subido el pasajero " + id + " al vagon2. Hay:" + (nvagon1+nvagon2));
				if(nvagon2 == N){
					enMarcha = true;
					hayGente = true;
					viaje.signal();
				}
				while(!seHaBajado1) esperaVagon2.await(); 
				
				nvagon2--;
				System.out.println("	Se ha bajado el pasajero " + id + " del vagon2. Hay:" + (nvagon1+nvagon2));
				if(nvagon2 == 0){
					hayGente = false;
					seHaBajado1 = false;
					subirse.signalAll();
				}
			}
			
		} finally {
			l.unlock();
		}
			
	}

	public void empiezaViaje() throws InterruptedException {
		l.lock();
		try {
			while(!enMarcha) viaje.await();
			System.out.println("        Maquinista:  empieza el viaje");
		} finally {
			l.unlock();
		}

	}
	public void finViaje() throws InterruptedException  {
		l.lock();
		try {
			System.out.println("        Maquinista:  fin del viaje");
			enMarcha = false;
			puedeBajar = true;
			esperaVagon1.signalAll();
		} finally {
			l.unlock();
		}
	}
}
