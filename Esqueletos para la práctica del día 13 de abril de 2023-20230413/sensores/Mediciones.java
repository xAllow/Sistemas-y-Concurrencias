package sensores;


public class Mediciones {
	
	
	
	public void nuevaMedicion(int id) {
		//el sensor id deja una nueva medición
		
		System.out.println("Sensor "+id+" deja sus mediciones");
		
	}
	
	public void recogerMediciones()  {
		//el worker coge las tres mediciones
		
		System.out.println("Worker ha recogido las mediciones");
	}
	
	public void finTrabajo() {
		//lo llama el worker cuando ha procesado las mediciones
		System.out.println("Worker ha procesado las mediciones");
		
	}
	
	//CS-Worker: No puede recoger las mediciones hasta que no están las tres
	//CS-Sensor-i: No puede medir hasta que el worker no ha procesado
	//las medidas de la interación anterior

}
