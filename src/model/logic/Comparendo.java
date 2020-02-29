package model.logic;

import java.util.Comparator;
import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	/**
	 * ID comparendo
	 */
	private int id;
	
	/**
	 * Fecha comparendo
	 */
	private Date fecha;
	
	/**
	 * Medio de detencion
	 */
	private String detencion;

	/**
	 * Vehiculo comparedo
	 */
	private String vehiculo;
	
	/**
	 * Servicio comparendo
	 */
	private String servicio;
	
	/**
	 * Infracción comparendo
	 */
	private String infraccion;
	
	/**
	 * Descripcion infraccion
	 */
	private String des_infrac;
	
	/**
	 * Localidad comparendo
	 */
	private String localidad;
	
	/**
	 * Latitud comparendo
	 */
	private double latitud;
	
	/**
	 * Longitud comparendo
	 */
	private double longitud;
	
	/**
	 * Metodo constructor para crear un comparendo
	 * @param pId ID comparendo
	 * @param pFecha Fecha comparendo
	 * @param pVehiculo Vehiculo comparendo
	 * @param pServicio servicio comparendo
	 * @param pInfraccion infraccion comparendo
	 * @param pDescripcion descripcion comparendo
	 * @param pLocalidad localidad comparendo
	 * @param pCoordenadas coordenadas comparendo
	 */
	public Comparendo(int pId, Date pFecha, String pDescripcion, String pDetencion, String pVehiculo, String pServicio, String pInfraccion, String pLocalidad, double pLongitud, double pLatitud)
	{
		id = pId;
		fecha = pFecha;
		vehiculo = pVehiculo;
		servicio = pServicio;
		infraccion = pInfraccion;
		des_infrac = pDescripcion;
		localidad = pLocalidad;
		longitud = pLongitud;
		latitud = pLatitud;
		detencion = pDetencion;
	}
	
	/**
	 * Da el ID del comparendo
	 * @return ID comparendo
	 */
	public int darId() {
		return id;
	}

	/**
	 * Da la fecha del comparendo
	 * @return Fecha comparendo
	 */
	public Date darFecha() {
		return fecha;
	}

	/**
	 * Da el vehiculo del comparendo
	 * @return Vehiculo comparendo
	 */
	public String darVehiculo() {
		return vehiculo;
	}

	/**
	 * Da el servicio del comparendo
	 * @return servicio comparendo
	 */
	public String darServicio() {
		return servicio;
	}

	/**
	 * Da la infraccion del comparendo
	 * @return infraccion comparendo
	 */
	public String darInfraccion() {
		return infraccion;
	}

	/**
	 * Da descripcion del comparendo
	 * @return descripcion comparendo
	 */
	public String darDescripcion() {
		return des_infrac;
	}

	/**
	 * Da la localidad del comparendo
	 * @return localidad comparendo
	 */
	public String darLocalidad() {
		return localidad;
	}


	
	/**
	 * Da la longitud de la coordenada
	 * @return longitud
	 */
	public double darLongitud()
	{
		return longitud;
	}
	
	/**
	 * Da latitud de la coordenada
	 * @return latitud
	 */
	public double darLatitud()
	{
		return latitud;
	}

	/**
	 * Clase 
	 */
	public static class ComparadorXFecha implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2){
			return c1.darFecha().compareTo(c2.darFecha());
		}
	}
	
    public static class ComparadorXInfraccion implements Comparator<Comparendo>{
		
		public int compare(Comparendo c1, Comparendo c2){
			return c2.darInfraccion().compareTo(c1.darInfraccion());
		}
	}
	
	@Override
	/**
	 * Compara los comparendos por codigo
	 */
	public int compareTo(Comparendo o) {
		return id-o.darId();
	}
	
	@Override
	/**
	 * Da la informacion del comparendo
	 */
	public String toString() {
		return "Comparendo [OBJECTID: " + id + ", FECHA: " + fecha + ", INFRACCION:" + infraccion
				+ ", VEHICULO:" + vehiculo + ", SERVICIO:" + servicio + ", LOCALIDAD: " + localidad;
	}

	
}
