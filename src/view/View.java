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
			System.out.println("0. Cargar Comparendos en la Lista.");
			System.out.println("1. Dar primer comparendo que concuerde con la localidad dada.");
			System.out.println("2. Dar todos comparendos que fueron emitidos a la fecha y hora dada .");
			System.out.println("3. Dar una tabla de infracciones ordenadas alfabéticamente, según dos fechas dadas.");
			System.out.println("4. Dar primer comparendo que concuerde con la infracción dada.");
			System.out.println("5. Dar todos los comparendos con la infracción dada.");
			System.out.println("6. Dar una tabla de infracciones ordenadas alfabéticamente, según dos tipos de servicio dados (Particular, Público).");
			System.out.println("7. Dar el número de comparendos por cada código de infracción en una localidad dada, para un periodo de tiempo dado.");
			System.out.println("8. Dado un N, dar los N códigos de infracción con más infracciones ordenados de mayor a menor en un periodo de tiempo dado.");
			System.out.println("9. Generar un gráfica ASCII que muestre un total de comparendos por cada localidad representado por un String de caracteres.");
            System.out.println("10. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
}
