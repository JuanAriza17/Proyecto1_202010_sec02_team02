package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	public final static String RUTA = "./data/comparendos_dei_2018.geojson";

	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Comparendo c = null;
		Comparable[] arreglo = null;
		SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

		while( !fin ){
			view.printMenu();
			try
			{
				int option = Integer.parseInt(lector.next());				
				switch(option){
					case 0:
						view.printMessage("--------- \nCargando lista de comparendos en la pila y en la cola ");
					    try
					    {
					    	modelo.cargarComparendos(RUTA);
					    	view.printMessage("Comparendos cargados a la lista");
						    view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
					    	view.printMessage("Comparendo con mayor ID: \n"+modelo.darMayorId().toString()+"\n");
					    	view.printMessage("Zona Minimax de los Comparendos:\n"+modelo.darZonaMiniMax()+"\n");
					    }
					    catch(FileNotFoundException e)
					    {
					    	view.printMessage("No se pudo crear la lista porque no existe el archivo de comparendos");
						    view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
					    }
					    catch(ParseException e)
					    {
					    	view.printMessage("Ocurrió un error cargando los comparendos");
						    view.printMessage("\n---------\n" + "Numero actual de comparendos en la lista " + modelo.darLongitud()+"\n");	
						    
					    }
					    
					    break;

					case 1:
						view.printMessage("--------- \n ");
						view.printMessage("Ingrese la localidad");
						String localidad = lector.next();
						c = modelo.darComparendoLocalidad(localidad);
						
						if(c==null)
						{
							view.printMessage("No se encontró el comparendo con la infracción dada.\n");
						}
						else
						{
							view.printMessage(c.toString()+"\n");						
						}	
						
						break;
					
					case 2:
						view.printMessage("--------- \n ");
						
						view.printMessage("Ingrese una fecha de la forma yyyy/MM/dd");
						String f = lector.next();
						try
						{
							Date fecha = parser.parse(f);
							arreglo = modelo.darComparendosOrdenadosPorInfraccionEnFecha(fecha);
							
							if(arreglo==null||arreglo.length==0)
							{
								view.printMessage("No se encontraron comparendos en esa fecha");
							}
							else
							{
								for(Comparable com: arreglo)
								{
									view.printMessage(com.toString());
								}
							}
						}
						catch(ParseException e)
						{
							view.printMessage("No ingresó la fecha en el formato correcto");
						}
						
						
						view.printMessage("Aún no se ha implementado el requerimiento");						
						break;
						
					case 3:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementado el requerimiento");						
						break;
						
					case 4:
						view.printMessage("--------- \n ");
						
						view.printMessage("Ingrese una infracción determinada:");	
						Scanner lector4=new Scanner(System.in);
						String infraccion = lector4.next();		
						try
						{
							Comparendo buscado=modelo.darComparendoInfraccion(infraccion);
							view.printMessage(buscado.toString()+"\n");
						}
						catch (Exception e)
						{
							view.printMessage(e.getMessage());
						}
						break;
					
					case 5:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementado el requerimiento");						
						break;
						
					case 6:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementado el requerimiento");						
						break;
					
					case 7:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementda el requerimiento");						
						break;
					
					case 8:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementado el requerimiento");						
						break;
					
					case 9:
						view.printMessage("--------- \n ");
						
						view.printMessage("Aún no se ha implementda el requerimiento");						
						break;
						
					case 10: 
						view.printMessage("--------- \n Hasta pronto !! \n---------"); 
						lector.close();
						fin = true;
						break;

					default: 
						view.printMessage("--------- \n Opcion Invalida !! \n---------");
						break;
				}
			}
			catch(NumberFormatException e)
			{
				view.printMessage("Por favor ingrese un número");
			}
			
		}
	}	
}
