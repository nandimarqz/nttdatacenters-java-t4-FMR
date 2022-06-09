package nttdatacenters_java_t4_FMR.components;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdatacenters_java_t4_FMR.Main;
import nttdatacenters_java_t4_FMR.exceptions.EqualStudent;
import nttdatacenters_java_t4_FMR.exceptions.SchoolNotFound;
import nttdatacenters_java_t4_FMR.exceptions.StudentNotFound;

public class EducationManagmentImpl implements EducationManagmentI {

	private static final String OUTPUTMETHOD = "FIN AL MÉTODO";

	private static final String INPUTMETHOD = "ENTRADA AL MÉTODO";

	/** String para la excepcion StudentNotFund */
	private static final String STUDENTNOTFOUND = "El alumno introducido no se encuentra en los centros registrados";

	/** String para la excepcion EqualStudent */
	private static final String EQUALSTUDENT = "Existe un alumno con el mismo DNI en algún centro";

	/** String para la excepcion SchoolNotFound */
	private static final String SCHOOLNOTFOUND = "No se encuentra la escuela";

	/** Mapa donde se guardan los centros y sus estudiantes */
	private Map<School, Set<Student>> schoolStudents;
	
	/** Logger para las trazas */
	private static final Logger EMLOG = LoggerFactory.getLogger(Main.class);

	/**
	 * Constructor para el servicio de gestión educativa
	 */
	public EducationManagmentImpl() {
		super();

		EMLOG.info("Generando el servicio de gestión educativo");
		schoolStudents = new TreeMap<>();
	}

	@Override
	public void dischargeStudent(Student s) throws EqualStudent, SchoolNotFound {

		EMLOG.info(INPUTMETHOD);
		
		// Si el mapa schoolStudents contiene como clave el centro educativo del
		// estudiante pasado por parametro entra en la condición, si no, laza una
		// excepción
		if (schoolStudents.containsKey(s.getSchool())) {

			// Si el metodo checkStudent devuelve false entra en la condicion, eso significa
			// que no existe ese alumno y lo añade a su escuela, si diera true no entraria
			// en la condicion y lanzaría una excepción
			if (!this.checkStudent(s)) {

				schoolStudents.get(s.getSchool()).add(s);
				
				EMLOG.info("ALUMNO DADO DE ALTA");

			} else {

				throw new EqualStudent(EQUALSTUDENT);

			}

		} else {

			throw new SchoolNotFound(SCHOOLNOTFOUND);

		}
		
		EMLOG.info(OUTPUTMETHOD);

	}

	@Override
	public void removeStudent(Student s) throws StudentNotFound {
		
		EMLOG.info(INPUTMETHOD);

		// Genera un booleano para indicar cuando se ha borrado
		boolean removed = Boolean.FALSE;

		// Si el usuario existe entra en la condicion, si no existe lanza una excepción
		if (this.checkStudent(s)) {

			// Genera un iterador para recorrer los conjuntos de alumnos de los centros
			// educativos
			Iterator<Set<Student>> itStudents = schoolStudents.values().iterator();

			// Mientras removed sea falso y el iterador tenga valor siguiente entra en el
			// bucle
			while (!removed && itStudents.hasNext()) {

				// Guarda el valor de iterador
				Set<Student> students = itStudents.next();

				// Genera otro iterador para recorrer los alumnos del conjunto
				Iterator<Student> itStudent = students.iterator();

				// Mientras removed sea falso y el iterador tenga valor siguiente entra en el
				// bucle
				while (!removed && itStudent.hasNext()) {

					// Guarda el valor de iterador
					Student student = itStudent.next();

					// Si student es igual al usuario pasador por parametro se elimina y removed
					// pasa al true
					if (student.equals(s)) {

						itStudent.remove();
						removed = Boolean.TRUE;
						EMLOG.info("ALUMNO ELIMINADO");
						
					}

				}

			}

		} else {

			throw new StudentNotFound(STUDENTNOTFOUND);

		}

		EMLOG.info(OUTPUTMETHOD);
		
	}

	@Override
	public void changeSchool(Student t, School s) throws StudentNotFound, EqualStudent, SchoolNotFound {

		EMLOG.info(INPUTMETHOD);
		
		// Obtenemos el estudiante a través de su DNI
		Student student = this.getStudent(t.getDni());

		// Llamamos al metodo removeStudent para que lo borre
		this.removeStudent(t);

		// Le seteamos el nuevo centro
		student.setSchool(s);

		// Le damos de alta de nuevo
		this.dischargeStudent(student);
		
		EMLOG.info("ALUMNO CAMBIADO");
		
		EMLOG.info(OUTPUTMETHOD);

	}

	@Override
	public School containtStudent(Student s) {
		
		EMLOG.info(INPUTMETHOD);

		// Genera un centro que va a ser el que devuelva
		School school = null;

		// Genera un booleano para indicar cuando se encuentra el alumno
		boolean found = Boolean.FALSE;

		// Genera un iterador para recorrer los centros educativos
		Iterator<School> itSchools = schoolStudents.keySet().iterator();

		// Mientras found sea falso y el iterador tenga valor siguiente entra en el
		// bucle
		while (!found && itSchools.hasNext()) {

			// Guarda el valor del iterador
			school = itSchools.next();

			// Si el conjunto de estudiantes del centro no esta vacío entra en la condicion,
			// si no, no realiza nada
			if (!schoolStudents.get(school).isEmpty()) {

				// Guarda el valor de iterador
				Iterator<Student> itStudents = schoolStudents.get(school).iterator();

				// Mientras found sea falso y el iterador tenga valor siguiente entra en el
				// bucle
				while (!found && itStudents.hasNext()) {

					// Guarda el valor de iterador
					Student student = itStudents.next();

					// Si el student es igual al alumno pasado por parametro entra en la condicion y
					// found = true
					if (student.equals(s)) {
						
						found = Boolean.TRUE;

					}

				}
			}
		}

		// Si found es false school = a null
		if (!found) {

			school = null;

		}
		
		EMLOG.info(OUTPUTMETHOD);

		return school;
	}

	@Override
	public boolean checkStudent(Student s) {
		
		EMLOG.info(INPUTMETHOD);

		// Genera un booleano para indicar si exise o no
		boolean exist = Boolean.FALSE;

		// Si contiene al estudiante pasador por parametro develve un valor distinto a
		// null y entra en la condicion, si no, no hace nada devuelve false que es que
		// no existe
		if (this.containtStudent(s) != null) {
			
			exist = Boolean.TRUE;
			EMLOG.info("EL USUARIO EXISTE");

		}
		
		EMLOG.info(OUTPUTMETHOD);

		return exist;
	}

	@Override
	public Student getStudent(String dni) {

		EMLOG.info(INPUTMETHOD);
		
		// Genera el alumno que va devolver
		Student s = null;

		// Genera un booleano para indicar cuando lo ha encontrado
		boolean found = Boolean.FALSE;

		// Genera un iterador para recorrer los conjuntos de estudiantes de los centros
		// educativos
		Iterator<Set<Student>> itStudents = schoolStudents.values().iterator();

		// Mientras found sea falso y el iterador tenga valor siguiente entra en el
		// bucle
		while (itStudents.hasNext() && !found) {

			// Guarda el valor del iterador
			Set<Student> students = itStudents.next();

			// Genera un iterador para recorrer los alumnos del conjunto
			Iterator<Student> itStudent = students.iterator();

			// Mientras found sea falso y el iterador tenga valor siguiente entra en el
			// bucle
			while (itStudent.hasNext() && !found) {

				// Guarda el valor del iterador
				s = itStudent.next();

				// Si el DNI del s es igual al DNI pasado por parámetro entra en la condición y
				// found = true
				if (s.getDni().equals(dni)) {

					found = Boolean.TRUE;

				}
			}

		}

		EMLOG.info(OUTPUTMETHOD);
		
		return s;
	}

	@Override
	public void dischargeSchool(School s) {
		
		EMLOG.info(INPUTMETHOD);

		// Si el mapa no contiene como clave el centro pasado por parametro entra en la
		// condicion y da el centro de alta
		if (!schoolStudents.containsKey(s)) {

			schoolStudents.put(s, new TreeSet<Student>());
			EMLOG.info("CENTRO EDUCATIVO DADO DE ALTA");

		}
		
		EMLOG.info(OUTPUTMETHOD);

	}

	@Override
	public String showStudentsFromSchool(School s) throws SchoolNotFound {
		
		EMLOG.info(INPUTMETHOD);

		// Genera un StringBuilder para guardar los estudiantes y devolverlos
		StringBuilder students = new StringBuilder();

		students.append(s);
		students.append("Alumnos:");
		students.append("\n");

		// Si el mapa contiene el centro pasado por parametro entra en la condicion, si
		// no, laza una excepción
		if (schoolStudents.containsKey(s)) {

			// Recorre los alumnos del centro y los va añadiendo al StringBuilder
			for (Student student : schoolStudents.get(s)) {

				students.append(student);
				students.append("\n");
			}

		} else {

			throw new SchoolNotFound(SCHOOLNOTFOUND);

		}
		
		EMLOG.info(OUTPUTMETHOD);

		return students.toString();
	}

	@Override
	public String showStudentDetails(Student s) throws StudentNotFound {

		EMLOG.info(INPUTMETHOD);
		
		// Genera un StringBuilder para guardar los datos del alumno y devolverlos
		StringBuilder details = new StringBuilder();

		// Si checkStudent da true entra en la condicon y añade al StringBuilder los
		// detalles del alumno, si da false, laza una excepción
		if (this.checkStudent(s)) {

			details.append(this.getStudent(s.getDni()).showDetails());

		} else {

			throw new StudentNotFound(STUDENTNOTFOUND);

		}
		
		EMLOG.info(OUTPUTMETHOD);

		return details.toString();
	}

	@Override
	public String showAllStudents() throws SchoolNotFound {
		
		EMLOG.info(INPUTMETHOD);

		// Genera un StringBuilder para guardar los estudiantes, los centros educativos
		// y devolverlos
		StringBuilder all = new StringBuilder();

		// Recorre las claves del mapa que son centros eduativos
		for (School s : schoolStudents.keySet()) {

			// Llama al metodo showStudentsFromSchool como parametro el centro que esta
			// recorriendo y añade todos los estudiuantes
			all.append(this.showStudentsFromSchool(s));

			all.append("Numero de estudiantes: ");
			all.append(schoolStudents.get(s).size());
			all.append("\n");
			all.append("\n");
		}
		
		EMLOG.info(OUTPUTMETHOD);

		return all.toString();
	}

	@Override
	public void removeSchool(School s) throws SchoolNotFound {
		
		EMLOG.info(INPUTMETHOD);

		// Si el mapa contiene como clave el centro pasado como párametro entra en la
		// condición y borra el centro, si no, lanza una excepción
		if (schoolStudents.containsKey(s)) {

			schoolStudents.remove(s);

		} else {

			throw new SchoolNotFound(SCHOOLNOTFOUND);

		}

		EMLOG.info(OUTPUTMETHOD);
		
	}
}
