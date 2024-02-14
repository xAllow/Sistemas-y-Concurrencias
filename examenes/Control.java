package recursos_2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Control {
	private int NUM;//numero total de recursos
	private int recursosDisponibles;
	private Lock l;
	private Condition cEspera;
	private List<Integer> orden;
	
	public Control(int num){
		this.NUM = num;
		recursosDisponibles = NUM;
		orden = new LinkedList<Integer>();
		l = new ReentrantLock();
		cEspera = l.newCondition();
	}
	public void qRecursos(int id,int num) throws InterruptedException{
		l.lock();
		try {
			orden.add(id);
			System.out.println("P"+id+" en posición "+orden.size());
			while(orden.get(0)!= id || recursosDisponibles < num)
				cEspera.await();
			recursosDisponibles-=num;
			orden.remove(0);
			System.out.println("P"+id+" obtiene recursos "+num);
			if(recursosDisponibles > 0)
				cEspera.signalAll(); //por si el nuevo primero puede usar los recursos
		}finally {
			l.unlock();
		}
	}

	public void libRecursos(int id,int num){
		l.lock();
		try {
			recursosDisponibles+=num;
			System.out.println("P"+id+" libera recursos "+num+". Total "+recursosDisponibles);
			cEspera.signalAll();
			
		}finally {
			l.unlock();
		}
	}
}
