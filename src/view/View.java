package view;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("0. (Requerimiento 0) Cargar Comparendos en la Lista.");
			System.out.println("1. (Requerimiento 1A) Dar primer comparendo que concuerde con la localidad dada.");
			System.out.println("2. (Requerimiento 2A) Dar todos comparendos que fueron emitidos a la fecha y hora dada .");
			System.out.println("3. (Requerimiento 3A) Dar una tabla de infracciones ordenadas alfab�ticamente, seg�n dos fechas dadas.");
			System.out.println("4. (Requerimiento 1B) Dar primer comparendo que concuerde con la infracci�n dada.");
			System.out.println("5. (Requerimiento 2B) Dar todos los comparendos con la infracci�n dada.");
			System.out.println("6. (Requerimiento 3B) Dar una tabla de infracciones ordenadas alfab�ticamente, seg�n dos tipos de servicio dados (Particular, P�blico).");
			System.out.println("7. (Requerimiento 1C) Dar el n�mero de comparendos por cada c�digo de infracci�n en una localidad dada, para un periodo de tiempo dado.");
			System.out.println("8. (Requerimiento 2C) Dado un N, dar los N c�digos de infracci�n con m�s infracciones ordenados de mayor a menor en un periodo de tiempo dado.");
			System.out.println("9. (Requerimiento 3C) Generar un gr�fica ASCII que muestre un total de comparendos por cada localidad representado por un String de caracteres.");
            System.out.println("10. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
}
