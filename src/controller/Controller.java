package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.data_structures.IListaEncadenada;
import model.data_structures.NodoLista;
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
		lector.useDelimiter("\n");
		boolean cargado = false;
		boolean fin = false;
		Comparendo c = null;
		Comparable[] arreglo = null;
		SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

		while( !fin ){
			view.printMenu();
			try
			{
				int option = Integer.parseInt(lector.nextLine());				
				switch(option){
				case 0:
					view.printMessage("--------- \nCargando lista de comparendos en la lista ");
					try
					{
						modelo.cargarComparendos(RUTA);
						cargado = true;
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
					if(cargado)
					{
						view.printMessage("Ingrese la localidad:");
						
						String localidad = lector.nextLine();
												
						c = modelo.darComparendoLocalidad(localidad);

						if(c==null)
						{
							view.printMessage("No se encontró el comparendo con la infracción dada.\n");
						}
						else
						{
							view.printMessage(c.toString()+"\n");						
						}	
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}

					break;

				case 2:
					view.printMessage("--------- \n ");

					if(cargado)
					{
						view.printMessage("Ingrese una fecha de la forma yyyy/MM/dd");
						String f = lector.nextLine();
						try
						{
							Date fecha = parser.parse(f);
							arreglo = modelo.darComparendosOrdenadosPorInfraccionEnFecha(fecha);

							if(arreglo==null||arreglo.length==0)
							{
								view.printMessage("No se encontraron comparendos en esa fecha.\n");
							}
							else
							{
								int i = 0;
								for(Comparable com: arreglo)
								{
									view.printMessage(com.toString());
									i++;
								}

								view.printMessage("Se encontraron "+i+" comparendos");
							}
						}
						catch(ParseException e)
						{
							view.printMessage("No ingresó la fecha en el formato correcto.\n");
						}
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}


					break;

				case 3:
					view.printMessage("--------- \n ");

					if(cargado)
					{
						view.printMessage("Ingrese la primera fecha de la forma yyyy/MM/dd");
						String f1 = lector.nextLine();

						view.printMessage("Ingrese la segunda fecha de la forma yyyy/MM/dd");
						String f2 = lector.nextLine();

						try
						{
							Date fecha1 = parser.parse(f1);
							Date fecha2 = parser.parse(f2);

							String mensaje = modelo.compararInfraccionPorFecha(fecha1, fecha2);

							view.printMessage(mensaje);

						}
						catch(ParseException e)
						{
							view.printMessage("No ingresó la fecha en el formato correcto");
						}
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}


					break;

				case 4:
					view.printMessage("--------- \n ");

					view.printMessage("Ingrese una infracción determinada:");	
					String infraccion4 = lector.nextLine();		
					try
					{
						Comparendo buscado=modelo.darComparendoInfraccion(infraccion4);
						view.printMessage(buscado.toString()+"\n");
					}
					catch (Exception e)
					{
						view.printMessage(e.getMessage());
					}
					break;

				case 5:
					view.printMessage("--------- \n ");
					
					view.printMessage("Ingrese una infracción determinada:");	
					String infraccion5 = lector.nextLine();
					String impresion="";
					int contador=0;
					try
					{
						Comparable[] lista=modelo.darComparendosOrdenadosPorFechaConInfraccion(infraccion5);
						for(int i=0; i<lista.length;++i)
						{
							Comparendo actual=(Comparendo) lista[i];
							impresion+=actual.toString()+"\n";
							++contador;
						}
						view.printMessage(impresion);
						view.printMessage("\nEl número total de comparendos en la consulta es de: "+ contador);
						
					}
					catch (Exception e)
					{
						view.printMessage(e.getMessage());
					}
					break;

				case 6:
					view.printMessage("--------- \n ");

					try
					{
						view.printMessage(modelo.compararInfraccionPorServicio());
					}
					catch (Exception e)
					{
						view.printMessage(e.getMessage());
					}	
					break;

				case 7:
					view.printMessage("--------- \n ");

					if(cargado)
					{
						view.printMessage("Ingrese la localidad que le interesa:");
						String localidad = lector.nextLine();
						
						view.printMessage("Debe ingresar las fechas en orden cronológico\n");
						view.printMessage("Ingrese la primera fecha de la forma yyyy/MM/dd:");
						String f1 = lector.nextLine();

						view.printMessage("Ingrese la segunda fecha de la forma yyyy/MM/dd:");
						String f2 = lector.nextLine();

						try
						{
							Date fechaInicial = parser.parse(f1);
							Date fechaFinal = parser.parse(f2);
							
							if(fechaInicial.compareTo(fechaFinal)>0)
							{
								view.printMessage("Las fechas se ingresaron en el orden incorrecto");
							}
							else
							{
								String mensaje = modelo.darNumeroComparendosPorInfraccionEnLocalidadYFecha(localidad, fechaInicial, fechaFinal);

								view.printMessage(mensaje);
							}
						}
						catch(ParseException e)
						{
							view.printMessage("No ingresó la fecha en el formato correcto");
						}
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}

					break;

				case 8:
					view.printMessage("--------- \n ");

					if(cargado)
					{
						view.printMessage("Ingrese la cantidad de infracciones que le interesa:");
						int n = Integer.parseInt(lector.nextLine());
						
						view.printMessage("Debe ingresar las fechas en orden cronológico\n");
						view.printMessage("Ingrese la primera fecha de la forma yyyy/MM/dd:");
						String f1 = lector.nextLine();

						view.printMessage("Ingrese la segunda fecha de la forma yyyy/MM/dd:");
						String f2 = lector.nextLine();

						try
						{
							Date fechaInicial = parser.parse(f1);
							Date fechaFinal = parser.parse(f2);
							
							if(fechaInicial.compareTo(fechaFinal)>0)
							{
								view.printMessage("Las fechas se ingresaron en el orden incorrecto");
							}
							else
							{
								String mensaje = modelo.darNCodigosInfraccionConMasInfraccionesEnFecha(n, fechaInicial, fechaFinal);

								view.printMessage(mensaje);
							}
						}
						catch(ParseException e)
						{
							view.printMessage("No ingresó la fecha en el formato correcto");
						}
						catch(NumberFormatException e)
						{
							view.printMessage("Ingrese una cantidad válida de infracciones");
						}
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}

					break;

				case 9:
					view.printMessage("--------- \n ");

					if(cargado)
					{
						String mensaje = modelo.generarASCII();
						view.printMessage(mensaje);
					}
					else
					{
						view.printMessage("Por favor inialice la lista.\n");
					}

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
				view.printMessage("Por favor ingrese un número.\n");
			}

		}
	}	
}
