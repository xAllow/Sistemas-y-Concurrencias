package aseos;

import java.util.concurrent.*;

public class Aseos {

	private int numClientes = 0;
	private Semaphore mutex = new Semaphore(1); //para numClientes
	private Semaphore limpiando = new Semaphore(1);

	
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException
	 * 
	 */
	public void entroAseo(int id) throws InterruptedException{
		mutex.acquire();
		numClientes++;
		if (numClientes == 1){
			limpiando.acquire();
		}

		System.out.println("Cliente "+ id + "entra en el aseo, hay " + numClientes);
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
		if(numClientes == 0){
			limpiando.release();
		}
		System.out.println("Cliente "+ id + "sale del aseo, hay " + numClientes);
		mutex.release();
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException
	 * 
	 */
    public void entraEquipoLimpieza() throws InterruptedException{
		limpiando.acquire();
		System.out.println("El equipo de limpieza entra en el aseo");
		
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public void saleEquipoLimpieza(){
    	System.out.println("El equipo de limpieza entra en el aseo");
		limpiando.release();
	}

	//CS-cliente pueden usar el aseo con otros clientes
	//CS-equipo usa el aseo en exclusion mutua 
}
