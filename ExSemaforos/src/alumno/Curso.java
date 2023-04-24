package alumno;

import java.util.concurrent.Semaphore;

public class Curso {

	//Numero maximo de alumnos cursando simultaneamente la parte de iniciacion
	private final int MAX_ALUMNOS_INI = 10;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore esperaIniciacion = new Semaphore(0);
	private Semaphore esperaPersonasAvanzado = new Semaphore(0);
	private Semaphore esperaGrupoAvanzado = new Semaphore(1);
	private Semaphore finProyecto = new Semaphore(0);
	private Semaphore empiezaProyecto = new Semaphore(0);
	private int contDesp = 0;
	

	private int numIniciacion = 0;
	private int numPersonasAvanzado = 0;
	//Numero de alumnos por grupo en la parte avanzada
	private final int ALUMNOS_AV = 3;
	
	
	//El alumno tendra que esperar si ya hay 10 alumnos cursando la parte de iniciacion
	public void esperaPlazaIniciacion(int id) throws InterruptedException{
		mutex.acquire();
		numIniciacion++;
		//Espera si ya hay 10 alumnos cursando esta parte
		if(numIniciacion == MAX_ALUMNOS_INI){
			mutex.release();
			esperaIniciacion.acquire();
			mutex.acquire();
		}
		//Mensaje a mostrar cuando el alumno pueda conectarse y cursar la parte de iniciacion
		System.out.println("PARTE INICIACION: Alumno " + id + " cursa parte iniciacion");
		mutex.release();
	}

	//El alumno informa que ya ha terminado de cursar la parte de iniciacion
	public void finIniciacion(int id) throws InterruptedException{
		mutex.acquire();
		numIniciacion--;
		//Mensaje a mostrar para indicar que el alumno ha terminado la parte de principiantes
		System.out.println("PARTE INICIACION: Alumno " + id + " termina parte iniciacion");
		if(esperaIniciacion.availablePermits() == 0) esperaIniciacion.release();
		mutex.release();

		//Libera la conexion para que otro alumno pueda usarla
	}
	
	/* El alumno tendra que esperar:
	 *   - si ya hay un grupo realizando la parte avanzada
	 *   - si todavia no estan los tres miembros del grupo conectados
	 */
	public void esperaPlazaAvanzado(int id) throws InterruptedException{
		//Espera a que no haya otro grupo realizando esta parte
		esperaGrupoAvanzado.acquire();
		mutex.acquire();
		//Espera a que haya tres alumnos conectados
		numPersonasAvanzado++;
		//Mensaje a mostrar si el alumno tiene que esperar al resto de miembros en el grupo
		if(numPersonasAvanzado < 3){
			esperaGrupoAvanzado.release();
			mutex.release();
			System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
			esperaPersonasAvanzado.acquire();
			mutex.acquire();
		}else{
			System.out.println("PARTE AVANZADA: Alumno " + id + " espera a que haya " + ALUMNOS_AV + " alumnos");
		}
		contDesp++;
		if(contDesp < 3) esperaPersonasAvanzado.release();
		//Mensaje a mostrar cuando el alumno pueda empezar a cursar la parte avanzada
		System.out.println("PARTE AVANZADA: Hay " + ALUMNOS_AV + " alumnos. Alumno " + id + " empieza el proyecto");
		empiezaProyecto.release();
		mutex.release();
	}
	
	/* El alumno:
	 *   - informa que ya ha terminado de cursar la parte avanzada 
	 *   - espera hasta que los tres miembros del grupo hayan terminado su parte 
	 */ 
	public void finAvanzado(int id) throws InterruptedException{
		//Espera a que los 3 alumnos terminen su parte avanzada
		empiezaProyecto.acquire();
		mutex.acquire();
		//Mensaje a mostrar si el alumno tiene que esperar a que los otros miembros del grupo terminen
		numPersonasAvanzado--;
		if(numPersonasAvanzado > 0){
			mutex.release();
			System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
			finProyecto.acquire();
			mutex.acquire();
		}else{
			System.out.println("PARTE AVANZADA: Alumno " +  id + " termina su parte del proyecto. Espera al resto");
			System.out.println("PARTE AVANZADA: LOS " + ALUMNOS_AV + " ALUMNOS HAN TERMINADO EL CURSO");
			contDesp = 0;
			esperaGrupoAvanzado.release();
		}
		if(finProyecto.availablePermits() == 0) finProyecto.release();
		//Mensaje a mostrar cuando los tres alumnos del grupo han terminado su parte
		
		mutex.release();
	}
}