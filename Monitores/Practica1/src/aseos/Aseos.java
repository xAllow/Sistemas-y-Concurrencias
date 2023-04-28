package aseos;

import java.util.concurrent.Semaphore;

public class Aseos {
	
	private int numClientes = 0; //Cs-eq. limpieza
	private boolean equipo = false; //Cs - cliente
	/**
	 * Utilizado por el cliente id cuando quiere entrar en los aseos
	 * CS Version injusta: El cliente espera si el equipo de limpieza est� trabajando
	 * CS Version justa: El cliente espera si el equipo de limpieza est� trabajando o
	 * est� esperando para poder limpiar los aseos
	 * @throws InterruptedException 
	 * 
	 */
	public synchronized void entroAseo(int id) throws InterruptedException{
		while(equipo) {
			wait();
		}
		numClientes++;
		System.out.println("El cliente "+id+ " entra. Hay: "+numClientes);
		
	}

	/**
	 * Utilizado por el cliente id cuando sale de los aseos
	 * 
	 */
	public synchronized void salgoAseo(int id){
	
		numClientes--;
		System.out.println("El cliente "+id+ " sale. Hay: "+numClientes);
		if(numClientes == 0) {
			notify();
		}
	}
	
	/**
	 * Utilizado por el Equipo de Limpieza cuando quiere entrar en los aseos 
	 * CS: El equipo de trabajo est� solo en los aseos, es decir, espera hasta que no
	 * haya ning�n cliente.
	 * @throws InterruptedException 
	 * 
	 */
    public synchronized void entraEquipoLimpieza() throws InterruptedException{
		while(numClientes > 0) wait();
		System.out.println("Ha entradp el equipo de limpieza. Hay: "+numClientes+" clientes.");
		equipo = true;
	}
    
    /**
	 * Utilizado por el Equipo de Limpieza cuando  sale de los aseos 
	 * 
	 * 
	 */
    public synchronized void saleEquipoLimpieza(){
    	equipo = false;
    	System.out.println("Sale equipo de limpieza. Hay: "+numClientes);
    	notifyAll();
	}
}
