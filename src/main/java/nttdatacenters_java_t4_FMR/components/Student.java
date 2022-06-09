package nttdatacenters_java_t4_FMR.components;

import java.util.Objects;

/**
 * Clase para los alumnos
 * 
 * @author nandi
 *
 */
public class Student implements Comparable<Student> {
	
	/** DNI del estudiante */
	private String dni;

	/** Nombre del estudiante */
	private String name;

	/** Apellido del estudiante */
	private String surname;

	/** Centro educativo del estudiante */
	private School school;
	
	
	/**
	 * Constructor del estudiante
	 * 
	 * @param dni
	 * @param name
	 * @param surname
	 * @param school
	 */
	public Student(String dni, String name, String surname, School school) {
		super();
		this.dni = dni;
		this.name = name;
		this.surname = surname;
		this.school = school;
	}

	/**
	 * Constructor del estudiante
	 * 
	 * @param dni
	 */
	public Student(String dni) {
		super();
		this.dni = dni;
	}


	/**
	 * Método que devuelve el DNI del estudiante
	 * 
	 * @return String
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Método que actualiza el DNI del estudiante
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Método que devuelve el nombre del estudiante
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método que actualiza el nombre del estudiante
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método que devuelve el apellido del estudiante
	 * 
	 * @return String
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Método que actualiza el apellido del estudiante
	 * 
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}


	/**
	 * Método que devuelve el centro educativo del estudiante
	 * 
	 * @return School
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * Método para actualizar el centro educativo del estudiante
	 * 
	 * @param school
	 */
	public void setSchool(School school) {
		this.school = school;
	}


	/**
	 * Compara los estudiantes por el nombre
	 * 
	 * @return Integer
	 */
	@Override
	public int compareTo(Student o) {

		return this.name.compareTo(o.name);
	}

	
	/**
	 * Genera el codigo hash del estudiante por el nombre
	 * 
	 *  @return Integer
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	/**
	 * Dos estudiantes son iguales si sus DNI lo son
	 * 
	 *  @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(dni, other.dni);
	}
	

	/**
	 * Muestra los detalles de la persona
	 * 
	 * @return String
	 */
	public String showDetails() {

		StringBuilder details = new StringBuilder();

		details.append("DNI: ");
		details.append(dni);
		details.append("\n");
		details.append("Nombre y apellido: ");
		details.append(name);
		details.append(" ");
		details.append(surname);
		details.append("\n");
		details.append("Centro educativo: ");
		details.append(school.getName());
		details.append("\n");
		
		

		return details.toString();

	}
	
	/**
	 * Muestra a el estudiante
	 * 
	 *  @return String
	 */
	@Override
	public String toString() {
		return "DNI: " + dni + " Nombre y Apellido: " + name + " " + surname;
	}

}
