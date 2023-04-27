package barrera;
import java.util.concurrent.*;
public class Gestor {

    private int tam,num = 0;
   
    private Semaphore mutex = new Semaphore(1,true);
    private Semaphore espera = new Semaphore(0,true);
    private Semaphore sigIter = new Semaphore(1);
   
    public Gestor(int tam){
        this.tam = tam;
    }
	
    public void llego(int id,int iter) throws InterruptedException{
    	sigIter.acquire();
    	mutex.acquire();   
    	num++;
    	System.out.println("Iter: "+iter+" Ha llegado trabajador "+id+ " hay "+num);
    	if (num < tam) {
    		mutex.release();
    		sigIter.release();
    		espera.acquire(); // no se satisface la condiciÃƒÂ³n de sincronizaciÃƒÂ³n
    		mutex.acquire();
    	}
    	num--;
    	if (num > 0) espera.release();
    	else {
    		sigIter.release();
    	}
    	System.out.println("Trabajador "+id+ " sale. Hay "+num);
    	mutex.release();
    }
}
