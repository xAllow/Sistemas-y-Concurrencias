package agua;


import java.util.concurrent.*;
	
public class GestorAgua {
	private int nO = 0;
	private int nH = 0;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore mol = new Semaphore(0);
	private Semaphore entraOx = new Semaphore(1);
	private Semaphore entraH = new Semaphore(1);
	
	
	public void hListo(int id) throws InterruptedException{ 
		entraH.acquire();
		mutex.acquire();
		nH++;
		System.out.println("Entra el H " + id);
		if(nH == 1) {
			entraH.release();
		}
		if (nH + nO < 3) {
			mutex.release();
			mol.acquire();
			mutex.acquire();
		} else {
			System.out.println("Estan los 3 atomos");
		}
		nH--;
		
		if (nH + nO > 0) {
			mol.release();
		} else {
			entraH.release();
			entraOx.release();
		}
		
		
		mutex.release();
	}
	
	public void oListo(int id) throws InterruptedException{ 
		entraOx.acquire();
		mutex.acquire();
		nO++;
		System.out.println("Entra el O: "+ id);
		if (nH + nO < 3) {
			mutex.release();
			mol.acquire();
			mutex.acquire();
		} else {
			System.out.println("Estan los 3 atomos");
		}
		nO--;
		
		if (nH + nO > 0) {
			mol.release();
		} else {
			entraH.release();
			entraOx.release();
		}
		
		mutex.release();
		
		
	}
}