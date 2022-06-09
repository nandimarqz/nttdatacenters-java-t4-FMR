package nttdatacenters_java_t4_FMR.components;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para los centros educativos
 * 
 * @author nandi
 *
 */
public class School implements Comparable<School> {

	/** Nombre del centro educativo */
	private String name;
	
	/** Ciudad del centro educativo */
	private String city;
	
	/** Logger para las trazas */
	private static final Logger SCHOOLLOG = LoggerFactory.getLogger(School.class);

	/**
	 * Constructor para el centro educativo
	 * 
	 * @param name
	 */
	public School(String name, String city) {
		super();
		
		SCHOOLLOG.info("Generando el centro educativo: {}", name);
		
		this.name = name.toUpperCase();
		this.city = city.toUpperCase();
		
	}
	
	/**
	 * Método que devuelve el nombre del centro educativo
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método que actualiza el nombre del centro educativo
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Genera el hashCode de los centros educativos por el nombre
	 * 
	 * @return Integer
	 */
	@Override
	public int hashCode() {
		return Objects.hash(city, name);
	}


	/**
	 * Dos centros educativos son iguales si sus nombres y ciudades lo son
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		School other = (School) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name);
	}
	
	/**
	 * Compara los centros educativos por el nombre
	 * 
	 * @return Integer
	 */
	@Override
	public int compareTo(School o) {
		
		int res = 0;
		
		if(this.name.compareTo(o.getName()) == 0) {
			
			if(this.city.compareTo(o.city) == 0) {
				
				res = 0;
				
			}else if(this.city.compareTo(o.city) > 0) {
				
				res = 1;
				
			}else {
				
				res = -1;
				
			}
				
		}else if(this.name.compareTo(o.getName()) > 0) {
			
			res = 1;
			
		}else {
			
			res = -1;
			
		}

		return res;
	}

	/**
	 * Devuelve un String con los datos del centro educativo
	 * 
	 * @return String
	 */
	@Override
	public String toString() {

		StringBuilder school = new StringBuilder();

		school.append("Nombre del centro educativo: ");
		school.append(name);
		school.append("\n");
		school.append("Ciudad: ");
		school.append(city);
		school.append("\n");
		
		

		return school.toString();
	}

}
