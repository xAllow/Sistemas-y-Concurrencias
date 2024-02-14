package impresoras;

import java.util.concurrent.Semaphore;
import java.util.*;
public class SalaImpresorasSem implements SalaImpresoras {
	private int N;
	private List<Integer> impresoras = new LinkedList<Integer>();
	private Semaphore mutex = new Semaphore(1);
	private Semaphore impresoraLibre = new Semaphore(1);
	
	public SalaImpresorasSem(int N) {
		this.N = N;
		for(int j = 0; j < N; j++) {
			impresoras.add(j);
		}
	}
    public int quieroImpresora(int id) throws InterruptedException{
    	System.out.println("Cliente "+ id +" quiere impresora");
    	impresoraLibre.acquire();
    	mutex.acquire();
    	N--;
    	int nimpr = impresoras.get(0);
    	System.out.println("  Cliente "+ id +" coge impresora "+nimpr+" quedan libres "+ N);
    	impresoras.remove(0);
    	if(N > 0) {
    		impresoraLibre.release();
    	}
    	mutex.release();
    	return nimpr;
    }

    public void devuelvoImpresora(int id, int n) throws InterruptedException{
    	mutex.acquire();
    	N++;
    	System.out.println("    Cliente "+id+" devuelve la impresora "+ n +" quedan libres "+N);
    	impresoras.add(n);
    	if(N == 1) {
    		impresoraLibre.release();
    	}
    	mutex.release();
    }
}
