package aseos;
import java.util.concurrent.Semaphore;
public class Aseos {
	
	private int numClientes = 0;
	private Semaphore mutex = new Semaphore(1);//para numClientes
	private Semaphore limpiando = new Semaphore(0);
	private Semaphore puedoEntrar = new Semaphore(1);
	private boolean entrar = true;
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza estï¿½ trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza estï¿½ trabajando o
	 * estï¿½ esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		
		mutex.acquire();
		
		while(!entrar) {
			
			limpiando.acquire();
			
		}
		numClientes++;
		System.out.println("Cliente "+id+" entra en el aseo. Hay "+numClientes);
		
		mutex.release();
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public void salgoAseo(int id) throws InterruptedException{
		mutex.acquire();
		numClientes--;
		System.out.println("Cliente "+id+" sale del aseo. Hay "+numClientes);
		if (numClientes==0) {
			limpiando.release();
		}
		mutex.release();
		
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo estï¿½ solo en los aseos, es decir, espera hasta que no
	 * haya ningï¿½n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		limpiando.acquire();
		entrar = false;
		System.out.println("El equipo de limpieza entra en el aseo");
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza(){
    	System.out.println("El equipo de limpieza sale del aseo");
    	entrar = true;
    	
    	limpiando.release();
	}
}
