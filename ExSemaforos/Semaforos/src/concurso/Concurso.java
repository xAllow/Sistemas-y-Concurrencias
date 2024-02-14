package concurso;
import java.util.concurrent.*;
public class Concurso {

	int[] numRondas = new int[2];
	int[] resultados = new int[2];
	int[] numTiradas = new int[2];
	int numJuegos= 0;
	boolean[] heAcabado = new boolean[2];
	Semaphore mutex = new Semaphore(1,true);
	Semaphore sigo = new Semaphore(0,true);
	public Concurso() {
		numRondas[0] = 0;
		numRondas[1] = 0;
		resultados[0] = 0;
		resultados[1] = 0;
		numTiradas[0] = 0;
		numTiradas[1] = 0;
		heAcabado[0] = false;
		heAcabado[1] = false;
	}

	public void tirarMoneda(int id,boolean cara) throws InterruptedException {
		mutex.acquire();
		System.out.println("[" + numTiradas[id] + "]El jugador " + id + " juega");
		resultados[id] += cara ? 1 : 0;
		numTiradas[id]++;
		heAcabado[id] = numTiradas[id] == 10;

		if(heAcabado[0] && heAcabado[1]){
			System.out.println("El jugador " + id + " cierra la ronda");
			numTiradas[0] = 0;
			numTiradas[1] = 0;
					if(resultados[0] > resultados[1]) numRondas[0]++;
			else 	if(resultados[1] > resultados[0]) numRondas[1]++;
			resultados[0] = 0;
			resultados[1] = 0;
			sigo.release();
		}
		mutex.release();
	}	
	
	public boolean concursoTerminado() {
		try {
			sigo.acquire();
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int ganador = 0;
		boolean resultado = false;
		if(numRondas[0] == 3){
			resultado = true;
		}else if(numRondas[1] == 3){
			resultado = true;
			ganador++;
		}
		
		if(heAcabado[0]){
			heAcabado[0] = false;
			sigo.release();
		}else{
			heAcabado[1] = false;
			if(resultado)		System.out.println("El jugador " + ganador + " ha ganado");
		}
		mutex.release();
		
		if(!resultado)				System.out.println("[" + numRondas[0] + "][" + numRondas[1] + "]Nadie ha ganado :(");
		return resultado; //borrar
	}
}