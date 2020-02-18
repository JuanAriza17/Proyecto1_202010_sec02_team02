package model.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.IListaEncadenada;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.ListaEncadenada;
import model.data_structures.Queue;
import model.data_structures.Stack;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo { 
	/**
	 * Atributos del modelo del mundo
	 */
	private IListaEncadenada<Comparendo> listaComparendos;
		
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		listaComparendos = new ListaEncadenada<Comparendo>();
	}

	public IListaEncadenada<Comparendo> darLista()
	{
		return listaComparendos;
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darLongitud()
	{
		return listaComparendos.darLongitud();
	}

	/**
	 * Agregar dato al inicio
	 * @param dato
	 */
	public void agregarInicio(Comparendo dato)
	{	
		listaComparendos.agregarInicio(dato);
	}
	
	/**
	 * Agregar dato al final
	 * @param dato
	 */
	public void agregarFinal(Comparendo dato)
	{
		listaComparendos.agregarFinal(dato);
	}
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(Comparendo dato)
	{
		listaComparendos.agregar(dato);
	}
	
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Comparendo buscar(Comparendo dato)
	{
		return listaComparendos.buscar(dato);
	}
	
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminar(Comparendo dato)
	{
		return listaComparendos.eliminar(dato);
	}
	
	/**
	 * Elimina último dato de la lista.
	 * @return dato Dato eliminado.
	 */
	public Comparendo eliminarFinal()
	{
		return listaComparendos.eliminarUltimo();
	}
	
	/**
	 * Eliminar primer elemento de la lista.
	 * @return Dato eliminado.
	 */
	public Comparendo eliminarInicio()
	{
		return listaComparendos.eliminarPrimero();
	}
	
	/**
	 * Da el primer comparendo de la lista
	 * @return primer dato
	 */
	public Comparendo darPrimerComparendo()
	{
		return listaComparendos.darPrimero().darElemento();
	}
	
	/**
	 * Da el comparendo con el mayor ID
	 * @return Comparendo con mayor ID
	 */
	public Comparendo darMayorId()
	{
		Comparendo comp = null;
		int id = 0;
		
		for(Comparendo c:listaComparendos)
		{			
			if(c.darId()>id)
			{
				comp = c;
				id = c.darId();
			}
		}
		
		return comp;
	}
	
	/**
	 * Da la menor longitud de todos los comparendos
	 * @return menor longitud
	 */
	public double darMenorLongitud()
	{
		double lon = darPrimerComparendo().darLongitud();
		
		for(Comparendo c:listaComparendos)
		{
			if(c.darLongitud()<lon)
			{
				lon = c.darLongitud();
			}
		}
		
		return lon;
	}
	
	/**
	 * Da la menor latitud de todos los comparendos
	 * @return menor latitud
	 */
	public double darMenorLatitud()
	{
		double lat = darPrimerComparendo().darLatitud();
		
		for(Comparendo c:listaComparendos)
		{
			if(c.darLatitud()<lat)
			{
				lat = c.darLatitud();
			}
		}
		
		return lat;
	}
	
	/**
	 * Da la mayor longitud de todos los comparendos
	 * @return mayor longitud
	 */
	public double darMayorLongitud()
	{
		double lon = darPrimerComparendo().darLongitud();
		
		for(Comparendo c:listaComparendos)
		{
			if(c.darLongitud()>lon)
			{
				lon = c.darLongitud();
			}
		}
		
		return lon;
	}
	
	/**
	 * Da la mayor latitud de todos los comparendos
	 * @return mayor latitud
	 */
	public double darMayorLatitud()
	{
		double lat = darPrimerComparendo().darLatitud();
		
		for(Comparendo c:listaComparendos)
		{
			if(c.darLatitud()>lat)
			{
				lat = c.darLatitud();
			}
		}
		
		return lat;
	}
	
	public String darZonaMiniMax()
	{
		return "Límite inferior: ["+darMenorLatitud()+","+darMenorLongitud()+"] \n"
				+ "Límite superior: ["+darMayorLatitud()+","+darMayorLongitud()+"]";
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 1A
	 * @param localidad, localidad del comparendo que se quiere buscar. 
	 * @return primer comparendo con la localidad dada.
	 */
	public Comparendo darComparendoLocalidad(String localidad)
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 2A
	 * @param fecha, fecha que se realizaron los comparendos
	 * @return todos los comparendos registrados dada una fecha. Resultados se presentan de manera ordenada por la infracción.
	 */
	public IListaEncadenada<Comparendo> darComparendosOrdenadosPorInfraccionEnFecha(String fecha)
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 3A
	 * @param fecha1 primera fecha que se quiere ver la cantidad de infracciones
	 * @param fecha2 segunda fecha que se quiere ver la cantidad de infracciones 
	 * @return Tabla donde se ve la cantidad de infracciones, y estas están ordenadas alfabeticamente.
	 */
	public String compararInfraccionPorFecha(String fechaIncial, String fechaFinal)
	{
		return null;
	}
	
	/**
	 * Da la cantidad de infracciones de un tipo en una fecha dada.
	 * @param inf infraccion que se quiere observar
	 * @param fecha fecha que se quiere contar la cantidad de infracciones
	 * @return cantidad de infracciones de un tipo en una fecha dada.
	 */
	public int contarInfraccionesPorFecha(String inf, String fecha)
	{
		return 0;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 1B
	 * @param inf, infraccion del comparendo que se quiere buscar
	 * @return primer comparendo con la infracción dada.
	 */
	public Comparendo darComparendoInfraccion(String inf)
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 2B
	 * @param inf infraccion de los comparendos
	 * @return Todos los comparendos con la infraccion dada, ordenados de menor a mayor según la fecha.
	 */
	public IListaEncadenada<Comparendo> darComparendosOrdenadosPorFechaConInfraccion(String inf)
	{
		return null;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 3B
	 * @return Tabla donde se ve la cantidad de infracciones, y estas están ordenadas alfabeticamente.
	 */
	public String compararInfraccionPorServicio()
	{
		return null;
	}
	
	/**
	 * Da la cantidad de infracciones de un tipo de servicio dado.
	 * @param inf infraccion que se quiere contar
	 * @param serv servicio de la infracción
	 * @return cantidad de infracciones de un tipo en una fecha dada.
	 */
	public int contarInfraccionesPorServicio(String serv, String inf )
	{
		return 0;
	}
	
	/**
	 * Método que se encarga de solucionar el requerimiento 1C
	 * @param localidad localidad de los comparendos
	 * @param fechaInicial fecha inicial intervalo tiempo
	 * @param fechaFinal fecha final intervalo tiempo
	 * @return tabal que dice la cantidad de infracciones dada una localidad y un intervalo de tiempo.
	 */
	public String darNumeroComparendosPorInfraccionEnLocalidadYFecha(String localidad, String fechaInicial, String fechaFinal)
	{
		return null;
	}
	
	/**
	 * Da la cantidad de infracciones de una localidad en un intervalo de tiempo dado. 
	 * @param inf infraccion que se quiere contar
	 * @param localidad localidad de la infraccion
	 * @param fechaInicial fecha inicial intervalo
	 * @param fechaFinal fecha final intervalo
	 * @return cantidad infracciones en la localidad en el intervalo de tiempo dado
	 */
	public int cantidadInfraccionesPorLocalidadEIntervaloTiempoDado(String inf, String localidad, String fechaInicial, String fechaFinal)
	{
		return 0;
	}
	
	/**
	 * Da una tabla con N infracciones donde se muestra la cantidad de comparendos que la tienen en un intervalo de tiempo. 
	 * @param n cantidad de infracciones
	 * @param fechaInicial fecha inicial intervalo
	 * @param fechaFinal fecha final intervalo.
	 * @return Tabla con N infracciones con más repeticiones en un intervalo de tiempo, ordenadas de mayor a menor según la cantidad de comparendos que tienen la infracción.
	 */
	public String darNCodigosInfraccionConMasInfraccionesEnFecha(int n, String fechaInicial, String fechaFinal)
	{
		return null;
	}
	
	/**
	 * Genera tabla ASCII con las especificaciones dadas.
	 * @return tabal ASCII que muestre el número total de comparendos por cada localidad
	 */
	public String generarASCII()
	{
		return null;
	}
	
	/**
	 * Da el ultimo comparendo de la lista
	 * @return ultimo dato
	 */
	public Comparendo darUltimoComparendo()
	{
		return listaComparendos.darUltimo().darElemento();
	}

	/**
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException
	{
		 File archivo = new File(ruta);
		 
		 
		 JsonReader lector = new JsonReader(new FileReader(archivo));
		 JsonObject obj = JsonParser.parseReader(lector).getAsJsonObject();
		 
		 JsonArray arregloComparendos = obj.get("features").getAsJsonArray();  
		 
		 listaComparendos = new ListaEncadenada<Comparendo>();
		 
		 for (JsonElement e: arregloComparendos) 	
		 {
			
			JsonObject propiedades = e.getAsJsonObject().get("properties").getAsJsonObject();
			
			int id = propiedades.get("OBJECTID").getAsInt();
			String fecha = propiedades.get("FECHA_HORA").getAsString();
			String vehiculo = propiedades.get("CLASE_VEHI").getAsString();
			String servicio = propiedades.get("TIPO_SERVI").getAsString();
			String infraccion = propiedades.get("INFRACCION").getAsString();
			String descripcion = propiedades.get("DES_INFRAC").getAsString();
			String localidad = propiedades.get("LOCALIDAD").getAsString();

			JsonObject geometria = e.getAsJsonObject().get("geometry").getAsJsonObject();
			JsonArray coords = geometria.get("coordinates").getAsJsonArray();
			double[] coordenadas = new double[2];
			
			for (int j = 0; j < coordenadas.length; j++) 
			{
				coordenadas[j]=coords.get(j).getAsDouble();
			}
						
			Comparendo comparendo = new Comparendo(id, fecha, vehiculo, servicio, infraccion, descripcion, localidad,coordenadas);
			
			agregarFinal(comparendo);
		 }
	}

}
