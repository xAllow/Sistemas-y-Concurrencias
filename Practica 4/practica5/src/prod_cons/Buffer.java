

public class Buffer { //recurso
	private int[] buffer;
	private int i = 0, j= 0;
	private int nDatos = 0;
	private volatile boolean fp = false, fc = false;
	private volatile int turno = 0;
	
	
	public Buffer(int s) {
		//TODO
		buffer = new int[s];
		
	}
	
	public void producir(int dato) {
		//TODO			
		while(nDatos == buffer.length){
			Thread.yield();
		}
		fp = true;
		turno = 1;
		while(fp && turno == 1){
			Thread.yield();
		}

		buffer[i] = dato;
		i = (i+1)%buffer.length;
		nDatos++;
		System.out.println("productor: " + dato + "nDatos: " + nDatos);
		fp = false;
	}
	
	public int consumir() {
		while(nDatos == 0) Thread.yield();
		fc = true;
		turno = 0;
		while(fc && turno == 0) Thread.yield();

		int local = buffer[j];
		j = (j+1)%buffer.length;
		nDatos--;
		fc = false;
		System.out.println("productor: " + local + "nDatos: " + nDatos);
		return local; 
	}
	

}
