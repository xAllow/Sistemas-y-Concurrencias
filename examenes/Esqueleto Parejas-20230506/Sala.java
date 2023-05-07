package parejas;

public class Sala {

	 
	  /**
	   * un hombre llega a la sala para formar una pareja
	   * si ya hay otra mujer en la sala o si aún no hay un hombre
	 * @throws InterruptedException 
	   */
	  public  void  llegaHombre(int id) throws InterruptedException {
		

		System.out.println("Llega hombre "+id);
		
		  
	  }
	   
	    
	  /**
	   * una mujer llega a la sala para formar una pareja
	   * debe esperar  si ya hay otra mujer en la sala o si aún no hay un hombre
	 * @throws InterruptedException 
	   */
	  public  void  llegaMujer(int id) throws InterruptedException {
		  
		  System.out.println("Llega hombre "+id);
	  }  
}
	

