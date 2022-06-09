package nttdatacenters_java_t4_FMR.components;

import nttdatacenters_java_t4_FMR.exceptions.EqualStudent;
import nttdatacenters_java_t4_FMR.exceptions.SchoolNotFound;
import nttdatacenters_java_t4_FMR.exceptions.StudentNotFound;

/**
 * Interfaz de la gestión educativa
 * 
 * @author nandi
 *
 */
public interface EducationManagmentI {

	/**
	 * Da de alta un estudiante, un estudiante no puede estar en dos centros educativos
	 * 
	 * @param s
	 * @throws EqualStudent
	 * @throws SchoolNotFound
	 */
	public void dischargeStudent(Student s) throws EqualStudent, SchoolNotFound;
	
	/**
	 * Elimina el estudiante pasado por parámetro
	 * 
	 * @param s
	 * @throws StudentNotFound
	 */
	public void removeStudent(Student s) throws StudentNotFound;
	
	/**
	 * Cambia de centro educativo el alumno pasado por parámetro al centro pasado por parámetro,
	 * si no esta dado de alta el centro que se pasa por parámetro se borra el alumno.
	 * 
	 * @param t
	 * @param s
	 * @throws StudentNotFound
	 * @throws EqualStudent
	 * @throws SchoolNotFound
	 */
	public void changeSchool(Student t, School s) throws StudentNotFound, EqualStudent, SchoolNotFound;
	
	/**
	 * Devuelve el centro educativo del estudiante pasado por parámetro
	 * 
	 * @param s
	 * @return
	 */
	public School containtStudent (Student s);
	
	/**
	 * Devuelve un booleano a true si el alumno pasado por parámetro existe, false si no existe
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkStudent(Student s);

	/**
	 * Devuelve un el alumno que tiene el DNI pasado por parámetro
	 * 
	 * @param dni
	 * @return
	 */
	public Student getStudent(String dni);
	
	/**
	 * Da de alta los centros educativos, no pueden existir centros educativos con mismo nombre y ciudad
	 * 
	 * @param s
	 */
	public void dischargeSchool(School s);
	
	/**
	 * Muestra los alumnos del centro pasado por parámetro
	 * 
	 * @param s
	 * @return String
	 * @throws SchoolNotFound
	 */
	public String showStudentsFromSchool (School s) throws SchoolNotFound;
	
	/**
	 * Muuestra todos los datos del alumno pasado por parámetro
	 * 
	 * @param s
	 * @return String
	 * @throws StudentNotFound
	 */
	public String showStudentDetails(Student s) throws StudentNotFound;
	
	/**
	 * Devuelve todos los centros educativos con sus alumnos
	 * 
	 * @return String
	 * @throws SchoolNotFound
	 */
	public String showAllStudents() throws SchoolNotFound;
	
	/**
	 * Elimina el centro educativo pasado por parámetro
	 * 
	 * @param s
	 * @throws SchoolNotFound
	 */
	public void removeSchool(School s) throws SchoolNotFound;

}
