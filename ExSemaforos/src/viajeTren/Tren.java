package viajeTren;
import java.util.concurrent.*;


public class Tren {
	private int N = 5;
	private int vagon1 = 0;
	private int vagon2 = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore lleno = new Semaphore(0);
	private Semaphore subir = new Semaphore(0);
	private Semaphore espera1 = new Semaphore(0);
	private Semaphore espera2 = new Semaphore(0);
	
	public void viaje(int id) throws InterruptedException {
		mutex.acquire();
		if(vagon2 < N) subir.release(); //Hasta que no suba el ultimo pasajero del vagon2, no paran de subir
		mutex.release();
		
		subir.acquire();
		mutex.acquire();
		if(vagon1 < N) {
			vagon1++;
			System.out.println("Pasajero "+ id + " ha subido al vagon 1 "+vagon1);
			//Ahora se quedan esperando hasta que se despierten cuando termine el viaje
			mutex.release();
			espera1.acquire();
			mutex.acquire();
			vagon1--;
			System.out.println("Pasajero "+ id + " ha bajado del vagon 1 "+vagon1);
			
			
		} else {
			vagon2++;
			System.out.println("Pasajero "+ id + " ha subido al vagon 2 "+ vagon2);
			if(vagon2 == N) lleno.release();
			mutex.release();
			espera2.acquire();
			mutex.acquire();
			
			vagon2--;
			System.out.println("Pasajero "+ id + " ha bajado del vagon 2 "+vagon2);
		}
		if(vagon1 > 0) espera1.release();
		if(vagon1 == 0 && vagon2 > 0) espera2.release();
		
		if(vagon2 == 0) {
			System.out.println("*******************************************");
			subir.release();
		}
		mutex.release();
	}

	public void empiezaViaje() throws InterruptedException {
		lleno.acquire();
		System.out.println("        Maquinista:  empieza el viaje");
		
	}
	public void finViaje() throws InterruptedException  {
		System.out.println("        Maquinista:  fin del viaje");
		espera1.release();
	}
}
