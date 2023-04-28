package agua;


public class GestorAgua {

	private boolean puertaH = true;
	private boolean puertaO = true;
	private boolean salida = false;
	private int numH = 0;
	private int numO = 0;
	
	public synchronized void hListo(int id) throws InterruptedException{ 
		while(!puertaH) wait();
		numH++;
		System.out.println("Entra el hidrogeno "+id+" Hid: "+numH+" Ox: "+numO);
		if(numH == 2) puertaH = false;
		if(numH + numO < 3) {
			while(!salida) wait();
		} else {
			salida = true;
			System.out.println("Ya estamos todos!!!!");
			notifyAll();
		}
		
		numH--;
		System.out.println("Sale el hidrogeno "+id+" Hid: "+numH+" Ox: "+numO);
		if(numH + numO == 0) {
			puertaH = true;
			puertaO = true;
			salida = false;
			System.out.println("********************************************");
			notifyAll();
		}
	}
	
	public synchronized void oListo(int id) throws InterruptedException{ 
		while(!puertaO) wait();
		numO++;
		System.out.println("Entra el oxigeno "+id+" Hid: "+numH+" Ox: "+numO);
		puertaO = false;
		if(numH + numO < 3) {
			while(!salida) wait();
		} else {
			salida = true;
			System.out.println("Ya estamos todos!!!!");
			notifyAll();
		}
		
		numO--;
		System.out.println("Sale el oxigeno "+id+" Hid: "+numH+" Ox: "+numO);
		if(numH + numO == 0) {
			puertaH = true;
			puertaO = true;
			salida = false;
			System.out.println("********************************************");
			notifyAll();
		}
	}
}
