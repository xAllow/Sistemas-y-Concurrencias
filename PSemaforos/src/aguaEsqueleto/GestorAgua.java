package aguaEsqueleto;
import java.util.concurrent.*;

public class GestorAgua {
	private int nH = 0;
	private int nO = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperaMol = new Semaphore(0);
	private Semaphore sigHid = new Semaphore(1);
	private Semaphore sigOx = new Semaphore(1);


	public void hListo(int id) throws InterruptedException{ 
		sigHid.acquire();
		mutex.acquire();
		nH++;
		System.out.println("Llega Hidrogeno "+id);
		if (nH == 1){
			sigHid.release();
		}
		if(nH+ nO < 3){
			mutex.release();
			esperaMol.acquire();
			mutex.acquire();
		}else {
			System.out.println("Ya estamos todos");
		}
		nH--;
		if (nH + nO > 0){
			esperaMol.release();
		}
		else {
			sigHid.release();
			sigOx.release();
		}
		mutex.release();
		
	}
	
	public void oListo(int id) throws InterruptedException{ 
		sigOx.acquire();
		mutex.acquire();
		nO++;
		System.out.println("Llega Oxigeno "+id);
		if(nH+ nO < 3){
			mutex.release();
			esperaMol.acquire();
			mutex.acquire();
		}else {
			System.out.println("Ya estamos todos");
		}
		nO--;
		if (nH + nO > 0){
			esperaMol.release();
		}
		else {
			sigHid.release();
			sigOx.release();
		}
		mutex.release();
		
	}
}
