package recursos;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

import javax.swing.CellEditor;

public class Control {
	private int NUM;//numero total de recursos
	private Lock l = new ReentrantLock();
	private int recursosDisponibles;
	private Condition cEspera = l.newCondition();
	private List<Integer> orden = new LinkedList<Integer>();

	public Control(int num){
		this.NUM = num;
		recursosDisponibles = NUM;
	}
	public void qRecursos(int id,int num) throws InterruptedException{
		l.lock();
		try{
			orden.add(id);
			System.out.println("P"+id+" en posición "+orden.size());
			while(orden.get(0)!= id || recursosDisponibles < num)
				cEspera.await();
			recursosDisponibles-=num;
			orden.remove(0);
			System.out.println("P"+id+" obtiene recursos "+num);
			if(recursosDisponibles > 0)
				cEspera.signalAll(); //por si el nuevo primero puede usar los recursos

		} finally{
			l.unlock();
		}
	}

	public void libRecursos(int id,int num){
		l.lock();
		try{
			recursosDisponibles+=num;
			System.out.println("P"+id+" libera recursos "+num+". Total "+recursosDisponibles);
			cEspera.signalAll();
		} finally{
			l.unlock();
		}
	}
}
