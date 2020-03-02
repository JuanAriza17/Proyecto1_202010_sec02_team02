package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.IListaEncadenada;
import model.data_structures.ListaEncadenada;
import model.data_structures.NodoLista;


/**
 * Definicion del modelo del mundo
 *@author Juan Andrés Ariza Gacharná 201911442
 *@author Sergio Julian Zona Moreno 201914936
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
		Comparendo c = null;

		NodoLista<Comparendo> actual = listaComparendos.darPrimero();

		while(actual!=null&&!actual.darElemento().darLocalidad().equalsIgnoreCase(localidad))
		{
			actual = actual.darSiguiente();
		}

		c = actual!=null?actual.darElemento():null;

		return c;
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 2A
	 * @param fecha, fecha que se realizaron los comparendos
	 * @return todos los comparendos registrados dada una fecha. Resultados se presentan de manera ordenada por la infracción.
	 */
	public Comparable[] darComparendosOrdenadosPorInfraccionEnFecha(Date fecha)
	{
		IListaEncadenada<Comparendo> lista = new ListaEncadenada<Comparendo>();
		Comparendo.ComparadorXInfraccionDescendente compXInfraccion = new Comparendo.ComparadorXInfraccionDescendente();

		NodoLista<Comparendo> actual = listaComparendos.darPrimero();

		while(actual!=null)
		{
			if(actual.darElemento().darFecha().compareTo(fecha)==0)
			{
				lista.agregarFinal(actual.darElemento());
			}
			actual = actual.darSiguiente();
		}

		Comparable[] arreglo = lista.darArreglo();

		Ordenamientos.mergeSort(arreglo, compXInfraccion);

		return arreglo;
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 3A
	 * @param fecha1 primera fecha que se quiere ver la cantidad de infracciones
	 * @param fecha2 segunda fecha que se quiere ver la cantidad de infracciones 
	 * @return Tabla donde se ve la cantidad de infracciones, y estas están ordenadas alfabeticamente.
	 */
	public String compararInfraccionPorFecha(Date fecha1, Date fecha2)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String mensaje = "Comparación de comparendos por Infracción en dos fechas\nInfracción | "+sdf.format(fecha1)+" | "+sdf.format(fecha2)+"\n";
		

		Comparable[] com = listaComparendos.darArreglo();
		Comparendo.ComparadorXInfraccionAscendente compXInfraccion = new Comparendo.ComparadorXInfraccionAscendente();
		Ordenamientos.mergeSort(com, compXInfraccion);

		Comparendo cp = (Comparendo) com[0];
		String infraccion = cp.darInfraccion();
		int contador1 = 0;
		int contador2 = 0;

		for (int i = 0; i < com.length; i++)
		{
			cp = (Comparendo) com[i];

			if(cp.darInfraccion().compareTo(infraccion)==0)
			{
				if(cp.darFecha().compareTo(fecha1)==0)
				{
					contador1++;
				}
				if(cp.darFecha().compareTo(fecha2)==0)
				{
					contador2++;
				}
			}
			else
			{
				if(contador1!=0||contador2!=0)
				{
					String espacios1="";
					for(int j=0;j<11-infraccion.length(); ++j)
					{
						espacios1+=" ";
					}
					String espacios2="";
					for(int j=0;j<11-Integer.toString(contador1).length(); ++j)
					{
						espacios2+=" ";
					}
					
					mensaje+=infraccion+espacios1+"| "+contador1+espacios2+"| "+contador2 +"\n";

				}	
				
				infraccion = cp.darInfraccion();
				contador1=cp.darFecha().compareTo(fecha1)==0?1:0;
				contador2=cp.darFecha().compareTo(fecha2)==0?1:0;

			}
		}

		if(contador1!=0||contador2!=0)
		{
			String espacios1="";
			for(int j=0;j<11-infraccion.length(); ++j)
			{
				espacios1+=" ";
			}
			String espacios2="";
			for(int j=0;j<11-Integer.toString(contador1).length(); ++j)
			{
				espacios2+=" ";
			}
			
			mensaje+=infraccion+espacios1+"| "+contador1+espacios2+"| "+contador2 +"\n";

		}

		return mensaje;
	}


	/**
	 * Método que se encarga de solucionar el requerimiento 1B
	 * @param inf, infraccion del comparendo que se quiere buscar
	 * @return primer comparendo con la infracción dada.
	 */
	public Comparendo darComparendoInfraccion(String pInfraccion) throws Exception
	{
		//CASO 1: La lista no se encuentra inicializada.
		if(listaComparendos.darPrimero()==null)
		{
			throw new Exception("Por favor inialice la lista.\n");
		}
		//CASO 2: Lista inicializada y se busca el comparendo por infracción.
		else
		{
			NodoLista<Comparendo> actual=listaComparendos.darPrimero();
			while(actual!=null)
			{
				if(actual.darElemento().darInfraccion().equals(pInfraccion))
				{
					return actual.darElemento();
				}
				actual=actual.darSiguiente();
			}
		}
		//CASO 3: No se encuentra la infracción dentro de la lista.
		throw new Exception("No se encontró un comparendo con la infracción dada.\n");
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 2B
	 * @param inf infraccion de los comparendos
	 * @return Todos los comparendos con la infraccion dada, ordenados de menor a mayor según la fecha.
	 */
	public Comparable[] darComparendosOrdenadosPorFechaConInfraccion(String pInfraccion) throws Exception
	{
		//CASO 1: La lista no se encuentra inicializada.
		IListaEncadenada<Comparendo> lista = new ListaEncadenada<Comparendo>();
		Comparendo.ComparadorXFecha compXFecha = new Comparendo.ComparadorXFecha();
		boolean existe=false;
		NodoLista<Comparendo> actual=listaComparendos.darPrimero();
		if(actual==null)
		{
			throw new Exception("Por favor inicialice la lista.\n");
		}
		//Se recorre la lista en busca de comparendos con la infracción.
		while(actual!=null)
		{
			if(actual.darElemento().darInfraccion().equals(pInfraccion))
			{
				lista.agregarFinal(actual.darElemento());
				existe=true;
			}
			actual=actual.darSiguiente();
		}
		//CASO 2: No se encuentra ninguna infracción con ese código dentro de la lista.
		if(existe==false)
		{
			throw new Exception("No se encontró ningún comparendo por la infracción buscada.\n");
		}

		//Ordena un arreglo con los datos de los comparendos por el comparador de fecha.
		Comparable[] arreglo=lista.darArreglo();
		Ordenamientos.mergeSort(arreglo, compXFecha);

		return arreglo;
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 3B
	 * @return Tabla donde se ve la cantidad de infracciones, y estas están ordenadas alfabeticamente.
	 */
	public String compararInfraccionPorServicio() throws Exception
	{
		String impresion="";
		Comparable[] arreglo;
		try
		{
			NodoLista<Comparendo>actual=listaComparendos.darPrimero();
			Comparendo.ComparadorXInfraccionAscendente compXInfraccion = new Comparendo.ComparadorXInfraccionAscendente();
			arreglo=listaComparendos.darArreglo();
			Ordenamientos.mergeSort(arreglo, compXInfraccion);
			//CASO 1: La lista no se encuentra inicializada.
			if(actual==null)
			{
				throw new Exception("Por favor inicialice la lista.\n");
			}
		}
		catch (Exception e)
		{
			throw new Exception("Por favor inicialice la lista.\n");
		}
		//Contadores que guardan información.
		String infraccion="";
		int contadorParticular=0;
		int contadorPublico=0;
		int contadorTotalInfracciones=0;
		int contadorTotalInfraccionesCumplen=0;
		int contadorTotalCodigoInfracciones=0;
		impresion+="Comparación de comparendos por Infracción en servicio Particular y servicio Público\nInfracción | Particular | Público \n";
		for(int i=0; i<arreglo.length; ++i)
		{
			++contadorTotalInfracciones;
			Comparendo actual=(Comparendo) arreglo[i];
			//Cambio en el código de infracción, se anexa al String de impresión la información adquirida del anterior código de infracción.
			if(!(actual.darInfraccion().equals(infraccion)))
			{
				//Ciclos para dejar espacios y cumplir con el formato establecido, no generan mucha complejidad, por lo que el algoritmo se mantiene con la misma.
				String espacios1="";
				for(int j=0;j<10-infraccion.length(); ++j)
				{
					espacios1+=" ";
				}
				String espacios2="";
				for(int j=0;j<10-Integer.toString(contadorParticular).length(); ++j)
				{
					espacios2+=" ";
				}
				
				//CASO 2: Existe el comparendo, pero no tiene ningún tipo Particular o Público, por ende no se anexa el String de impresión.
				if(contadorParticular!=0 || contadorPublico!=0)
				{
					impresion+=infraccion+espacios1+" | "+contadorParticular+espacios2+" | "+contadorPublico+"\n";
				}
				contadorTotalInfraccionesCumplen+=contadorParticular+contadorPublico;
				//Reinicio del contador por el cambio de código del comparendo.
				infraccion=actual.darInfraccion();
				contadorParticular=actual.darServicio().equals("Particular")?1:0;
				contadorPublico=actual.darServicio().equals("Particular")?0:1;
				++contadorTotalCodigoInfracciones;
			}
			
			//Se mantiene el código del comparendo, aumentan los contadores según el tipo.
			else if(actual.darInfraccion().equals(infraccion))
			{
				contadorParticular+=actual.darServicio().equalsIgnoreCase("Particular")?1:0;
				contadorPublico+=actual.darServicio().equalsIgnoreCase("Particular")?0:1;
			}

		}
		
		//Ciclos para dejar espacios y cumplir con el formato establecido, no generan mucha complejidad, por lo que el algoritmo se mantiene con la misma.
		String espacios1="";
		for(int j=0;j<10-infraccion.length(); ++j)
		{
			espacios1+=" ";
		}
		String espacios2="";
		for(int j=0;j<10-Integer.toString(contadorParticular).length(); ++j)
		{
			espacios2+=" ";
		}
		
		//CASO 2: Existe el comparendo, pero no tiene ningún tipo Particular o Público, por ende no se anexa el String de impresión.
		if(contadorParticular!=0 || contadorPublico!=0)
		{
			impresion+=infraccion+espacios1+" | "+contadorParticular+espacios2+" | "+contadorPublico+"\n";
		}
		int noCumplen=contadorTotalInfracciones-contadorTotalInfraccionesCumplen;
		impresion+="\nEl número total de infracciones fue de: "+contadorTotalInfracciones;
		impresion+="\nEl número total de infracciones registradas en la tabla fue de: "+contadorTotalInfraccionesCumplen;
		impresion+="\nEl número total de infracciones que NO cumplían con el formato requerido por diseño fue de:"+noCumplen;
		impresion+="\nEl número total de códigos de infracciones registrados fue de: "+contadorTotalCodigoInfracciones+"\n";
		return impresion;
	}

	/**
	 * Método que se encarga de solucionar el requerimiento 1C
	 * @param localidad localidad de los comparendos
	 * @param fechaInicial fecha inicial intervalo tiempo
	 * @param fechaFinal fecha final intervalo tiempo
	 * @return tabal que dice la cantidad de infracciones dada una localidad y un intervalo de tiempo.
	 */
	public String darNumeroComparendosPorInfraccionEnLocalidadYFecha(String localidad, Date fechaInicial, Date fechaFinal)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		String mensaje = "Comparación de comparendos en "+localidad+" del "+sdf.format(fechaInicial)+" al "+sdf.format(fechaFinal)+
				         "\nInfracción | #Comparendos\n";	
		

		Comparable[] com = listaComparendos.darArreglo();
		Comparendo.ComparadorXInfraccionAscendente compXInfraccion = new Comparendo.ComparadorXInfraccionAscendente();
		Ordenamientos.mergeSort(com, compXInfraccion);
		
		Comparendo cp = (Comparendo) com[0];
		String infraccion = cp.darInfraccion();
		int contador = 0;

		for (int i = 0; i < com.length; i++)
		{
			cp = (Comparendo) com[i];

			if(cp.darLocalidad().equalsIgnoreCase(localidad)&&cp.darFecha().after(fechaInicial)&&cp.darFecha().before(fechaFinal))
			{
				if(cp.darInfraccion().compareTo(infraccion)==0)
				{
					contador++;
				}
				else
				{

					if(contador!=0)
					{
						String espacios1="";
						for(int j=0;j<10-infraccion.length(); ++j)
						{
							espacios1+=" ";
						}
						
						mensaje+=infraccion+espacios1+" | "+contador+"\n";
					}		

					infraccion = cp.darInfraccion();

					contador=1;
				}
			}	
		}

		if(contador!=0)
		{
			String espacios1="";
			for(int j=0;j<11-infraccion.length(); ++j)
			{
				espacios1+=" ";
			}
			
			mensaje+=infraccion+espacios1+"| "+contador+"\n";
		}
		
		return mensaje;
	}

	/**
	 * Da una tabla con N infracciones donde se muestra la cantidad de comparendos que la tienen en un intervalo de tiempo. 
	 * @param n cantidad de infracciones
	 * @param fechaInicial fecha inicial intervalo
	 * @param fechaFinal fecha final intervalo.
	 * @return Tabla con N infracciones con más repeticiones en un intervalo de tiempo, ordenadas de mayor a menor según la cantidad de comparendos que tienen la infracción.
	 */
	public String darNCodigosInfraccionConMasInfraccionesEnFecha(int n, Date fechaInicial, Date fechaFinal)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		String mensaje = "Ranking de las "+n+" mayores infracciones del "+sdf.format(fechaInicial)+" al "+sdf.format(fechaFinal)+
				         "\nInfracción | #Comparendos\n";	
		

		Comparable[] com = listaComparendos.darArreglo();
		Comparendo.ComparadorXInfraccionAscendente compXInfraccion = new Comparendo.ComparadorXInfraccionAscendente();
		Ordenamientos.mergeSort(com, compXInfraccion);
		
		Comparendo cp = (Comparendo) com[0];
		ListaEncadenada<Comparendo> cantidades = new ListaEncadenada<Comparendo>();
		String infraccion = cp.darInfraccion();
		int contador = 0;
		
		for (int i = 0; i < com.length; i++)
		{
			cp = (Comparendo) com[i];

			if(cp.darFecha().after(fechaInicial)&&cp.darFecha().before(fechaFinal))
			{
				if(cp.darInfraccion().compareTo(infraccion)==0)
				{
					contador++;
				}
				else
				{

					if(contador!=0)
					{
						cantidades.agregarOrdenado(new Comparendo(contador, null, "", "", "", "", infraccion, "", 0, 0));
					}
						
					
					infraccion = cp.darInfraccion();

					contador=1;
				}
			}	
		}
		
		int i = 0;
		while(cantidades.darLongitud()>0&&i!=n)
		{
			Comparendo c = cantidades.eliminarUltimo();

			String espacios1="";
			for(int j=0;j<11-c.darInfraccion().length(); ++j)
			{
				espacios1+=" ";
			}
			
			mensaje+=c.darInfraccion()+espacios1+"| "+c.darId()+"\n";
			
			i++;
		}
		
		return mensaje;
	}

	/**
	 * Genera tabla ASCII con las especificaciones dadas.
	 * @return tabal ASCII que muestre el número total de comparendos por cada localidad
	 */
	public String generarASCII()
	{
		
        String mensaje = "Aproximación del número de comparendos por localidad\n";

		Comparable[] com = listaComparendos.darArreglo();
		Comparendo.ComparadorXLocalidad compXLocalidad = new Comparendo.ComparadorXLocalidad();
		Ordenamientos.mergeSort(com, compXLocalidad);

		Comparendo cp = (Comparendo) com[0];
		String localidad = cp.darLocalidad();
		int contador = 0;

		for (int i = 0; i < com.length; i++)
		{
			cp = (Comparendo) com[i];

			if(cp.darLocalidad().compareTo(localidad)==0)
			{
				contador++;
			}
			else
			{
				String auxiliar = "";
				if(contador!=0)
				{
					
					String asteriscos ="";
					int nAsteriscos = contador/50;
					if(contador%50!=0)
						asteriscos+="*";
					
					int ast = 0;
					while(ast<nAsteriscos)
					{
						asteriscos+="*";
						ast++;
					}
						

					
					auxiliar=asteriscos;

				}	
				else
				{
					auxiliar="Sin comparendos";
				}
				String lineas="";
				for(int j=0;j<15-localidad.length(); ++j)
				{
					lineas+="-";
				}
				mensaje+=localidad+lineas+"|"+auxiliar+"\n";
				
				localidad = cp.darLocalidad();
				contador = 1;

			}
		}

		String auxiliar = "";
		if(contador!=0)
		{
			
			String asteriscos ="";
			int nAsteriscos = contador/50;
			if(contador%50!=0)
				asteriscos+="*";
			
			int ast = 0;
			while(ast<nAsteriscos)
			{
				asteriscos+="*";
				ast++;
			}
				
			auxiliar = asteriscos;

		}	
		else
		{
			auxiliar="Sin comparendos";
		}
		String lineas="";
		for(int j=0;j<15-localidad.length(); ++j)
		{
			lineas+="-";
		}
		mensaje+=localidad+lineas+"|"+auxiliar+"\n";
		
		return mensaje;
	}

	/**
	 * Da el ultimo comparendo de la lista
	 * @return ultimo dato
	 */
	public Comparendo darUltimoComparendo()
	{
		return listaComparendos.darUltimo().darElemento();
	}


	// Solucion de carga de datos publicada al curso Estructuras de Datos 2020-10
	/**
	 * Método que carga los comparendos
	 * @param ruta Rita archivo con los comparendos
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public void cargarComparendos(String ruta) throws FileNotFoundException, ParseException
	{		

		listaComparendos = new ListaEncadenada<Comparendo>();

		JsonReader reader = new JsonReader(new FileReader(ruta));
		JsonElement elem = JsonParser.parseReader(reader);
		JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


		SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

		for(JsonElement e: e2) {
			int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

			String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
			Date FECHA_HORA = parser.parse(s); 

			String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
			String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
			String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
			String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
			String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
			String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

			double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
					.get(0).getAsDouble();

			double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
					.get(1).getAsDouble();

			Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);
			listaComparendos.agregarFinal(c);
		}		
	}
	
	
}
