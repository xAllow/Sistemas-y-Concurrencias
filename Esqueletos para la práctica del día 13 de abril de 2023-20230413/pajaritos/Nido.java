package pajaritos;


public class Nido {
	
	public void come(int id) {
		//el bebe id coge un bichito del nido
	
		System.out.println("El bebé "+id+" ha comido un bichito. Quedan ");
		
	}
	
	public void nuevoBichito(int id)  {
		//el papa/mama id deja un nuevo bichito en el nido
		
		
		System.out.println("El papá "+id+" ha añadido un bichito. Hay ");
		
	}
}

//CS-Bebe-i: No puede comer del nido si está vacío
//CS-Papa/Mama: No puede poner un bichito en el nido si está lleno
