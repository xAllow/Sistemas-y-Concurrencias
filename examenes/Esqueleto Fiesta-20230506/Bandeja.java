package fiesta;
import java.util.concurrent.Semaphore;

public class Bandeja {
	
	
	private int R = 8; // numero de raciones por pastel
	

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
		
	}
	
	/**
	 * El pastelero llama a este método para poner una nueva tarta en
	 * la bandeja. Tiene que esperar a que la bandeja esté vacía para ponerla.
	 * @throws InterruptedException
	 */
	
	public void tarta() throws InterruptedException{
		
	
	}
	
}
