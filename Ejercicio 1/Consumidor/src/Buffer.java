
import java.util.concurrent.*;
public class Buffer { //Recurso
	
	private int[] buffer;
	private int p = 0;//indice de los productores
	private int c = 0;//indice del consumidor
	private Semaphore mutex = new Semaphore(1);
	private Semaphore turnoProd = new Semaphore(1);
	private Semaphore turnoCons = new Semaphore(0);
	public Buffer(int tam){
		buffer = new int[tam];
	}
	

	
	public void almacenar(int dato,int id) throws InterruptedException{
		//utilizado por el productor id
		turnoProd.acquire();
		mutex.acquire();
		buffer[p] = dato;
		
		p = (p+1)%buffer.length;
		System.out.println("El productor "+ id+ " deja "+dato);
		if (p == 0) turnoCons.release();
		mutex.release();
		
	}
	
	public int extraer() throws InterruptedException{
		//utilizado el consumidor 
		turnoCons.acquire();
		mutex.acquire();
		int dato = 0;
		dato = buffer[c];
		c = (c + 1)%buffer.length;
		System.out.println("               El consumidor extrae "+dato);
		if (c == 0) turnoProd.release();
		else turnoCons.release();
		mutex.release();
		return dato;
	}

}

//CS-Prod: espero hasta que el buffer no está lleno
//CS-Cons: espero hasta que el buffer no esté vacío


