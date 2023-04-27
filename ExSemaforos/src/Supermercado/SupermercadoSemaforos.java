package Supermercado;

import java.util.concurrent.Semaphore;

public class SupermercadoSemaforos implements Supermercado {
	private Cajero permanente;
	private int nClientes = 0;
	private int nCajeros = 0;
	private boolean hayClientes;
	private boolean estaCerrado = false;
	private Semaphore mutex = new Semaphore(1); //CS-clientes
	private Semaphore esperaPermanente = new Semaphore(0);
	private Semaphore cajOcasional = new Semaphore(0);

	
	public SupermercadoSemaforos() throws InterruptedException {
		permanente = new Cajero(this, true); //crea el primer cajero, el permanente
		permanente.start();		
		//TODO
	}

	@Override
	public void fin() throws InterruptedException {
		estaCerrado = true;
		
		System.out.println("Supermercado cerrado!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public void nuevoCliente(int id) throws InterruptedException {
		mutex.acquire();
		if(!estaCerrado) {
			nClientes++;
			System.out.println("            LLega cliente " + id + " Hay " + nClientes);
		}
		if(nClientes > 3*(nCajeros+1) && !estaCerrado) {
			Cajero ocasional = new Cajero(this, false);
			nCajeros++;
			System.out.println("Se crea un cajero nuevo "+nCajeros);
			ocasional.start();
			cajOcasional.release();
		} else {
			hayClientes = true;
			esperaPermanente.release();
		}
		
		mutex.release();
	}

	@Override
	public boolean permanenteAtiendeCliente( int id) throws InterruptedException {
		if (nClientes == 0) System.out.println("Cajero permanente espera");
		esperaPermanente.acquire();
		mutex.acquire();
		if(nClientes > 0 )
		nClientes--;
		hayClientes = true;
		System.out.println("  Cajero permanente atiende a un cliente. Quedan "+ nClientes);
		
		if(nClientes > 0) {
			esperaPermanente.release();
		}
		if(nClientes == 0 && estaCerrado) {
			System.out.println("Cajero permanente termina: "+nClientes);
			hayClientes = false;
			estaCerrado = true;
			esperaPermanente.acquire();
		}
		mutex.release();
		return hayClientes;
		
	}
		
	
	@Override
	public boolean ocasionalAtiendeCliente(int id) throws InterruptedException {
		System.out.println("El nuevo cajero "+ nCajeros + " comienza a servir a un cliente ");
		cajOcasional.acquire();
		mutex.acquire();
		if(nClientes > 0)
		nClientes--;
		System.out.println("El cajero "+nCajeros+ " atiende a un cliente: " +nClientes);
		if(nClientes == 0) {
			hayClientes = false;
			System.out.println("No hay clientes. Cajero "+nCajeros+" termina: "+nClientes);
			
		} 
		if(nClientes > 0) cajOcasional.release();
		mutex.release();
	
		return hayClientes;//borrar
	}

}
