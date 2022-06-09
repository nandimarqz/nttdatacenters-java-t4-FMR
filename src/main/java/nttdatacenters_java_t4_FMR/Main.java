package nttdatacenters_java_t4_FMR;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdatacenters_java_t4_FMR.components.EducationManagmentImpl;
import nttdatacenters_java_t4_FMR.components.School;
import nttdatacenters_java_t4_FMR.components.Student;
import nttdatacenters_java_t4_FMR.exceptions.EqualStudent;
import nttdatacenters_java_t4_FMR.exceptions.SchoolNotFound;
import nttdatacenters_java_t4_FMR.exceptions.StudentNotFound;

/**
 * Clase principal
 * 
 * @author nandi
 *
 */
public class Main {

	/** String para la petición del DNI */
	private static final String REQUESTDNI = "Introduzca el DNI del alumno";

	/** String para la petición de la ciudad del centro educativo */
	private static final String REQUESTSCHOOLCITY = "Introduzca la ciudad donde se ubica";

	/** String para la petición del nombre del centro educativo */
	private static final String REQUESTSCHOOLNAME = "Introduzca el nombre del centro";

	/** String para el menú */
	private static final String MENU = "1.- Dar de alta centro educativo" + " \n2.- Dar de alta alumno"
			+ "\n3.- Eliminar alumno " + " \n4.- Cambiar de centro un alumno " + " \n5.- Mostrar el centro del alumno"
			+ "\n6.- Comprobar si existe estudiante" + "\n7.- Enseñar alumnos de un centro educativo"
			+ "\n8.- Mostrar todos los centros y sus alumnos" + "\n9.- Eliminar centro educativo" + "\n10.- Salir"
			+ "\nInserte el numero de la opción que quiere realizar";

	/** Inyecta el servicio de gestión educativa */
	private static final EducationManagmentImpl em = new EducationManagmentImpl();
	
	/** Logger para las trazas */
	private static final Logger MAINLOG = LoggerFactory.getLogger(Main.class);

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			// String para el nombre del alumno
			String studentName = "";

			// String para el DNI del alumno
			String dni = "";

			// String para los apellidos del alumno
			String surname = "";

			// String para el nombre del centro educativo
			String schoolName = "";

			// String para la ciudad del centro educativo
			String city = "";

			// Muestra la bienvenida y el menu por consola
			System.out.println("Bienvenido a la gestion de alumnos de la DUAL de NTTDATA");
			System.out.println(MENU);

			// Guarda la opción escogida
			int option = sc.nextInt();
			
			MAINLOG.info("Muestra el menu");

			// Mientras que la opción sea diferente a 10 entra en el bucle
			while (option != 10) {
				try {
					switch (option) {
					case 1:
						MAINLOG.info("CASO 1");

						// Pide el nombre de la escuela y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLNAME);
						sc.nextLine();
						schoolName = sc.nextLine();

						// Pide el nombre de la ciudad y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLCITY);
						city = sc.nextLine();

						// Llama al método dischargeSchool del servicio de gestión educativo
						em.dischargeSchool(new School(schoolName, city));

						break;

					case 2:
						MAINLOG.info("CASO 2");

						// Pide el nombre del alumno y guarda el nombre en una variable
						System.out.println("Introduzca el nombre del alumno");
						sc.nextLine();
						studentName = sc.nextLine();

						// Pide los apellidos del alumno y guarda los apellidos en una variable
						System.out.println("Introduzaca los apellidos del alumno");
						surname = sc.nextLine();

						// Pide el DNI del alumno y lo guarda en una variable
						System.out.println(REQUESTDNI);
						dni = sc.next();

						// Pide el nombre de la escuela y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLNAME);
						sc.nextLine();
						schoolName = sc.nextLine();

						// Pide el nombre de la ciudad y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLCITY);
						city = sc.nextLine();

						// Llama al método dischargeStudent del servicio de gestión educativo
						em.dischargeStudent(
								new Student(dni.toUpperCase(), studentName, surname, new School(schoolName, city)));

						break;
					case 3:
						MAINLOG.info("CASO 3");

						// Pide el DNI del alumno y lo guarda en una variable
						System.out.println(REQUESTDNI);
						dni = sc.next();

						// Llama al método removeStudent del servicio de gestión educativo
						em.removeStudent(new Student(dni.toUpperCase()));

						break;
					case 4:
						MAINLOG.info("CASO 4");

						// Pide el DNI del alumno y lo guarda en una variable
						System.out.println(REQUESTDNI);
						dni = sc.next();

						// Pide el nombre del centro al que se va a cambiar y lo guarda en una variable
						System.out.println("Introduzca el nombre del centro al que se va a cambiar");
						sc.nextLine();
						schoolName = sc.nextLine();

						// Pide el nombre de la ciudad y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLCITY);
						city = sc.nextLine();

						// Llama al método changeSchool del servicio de gestión educativo
						em.changeSchool(new Student(dni.toUpperCase()), new School(schoolName, city));

						break;
					case 5:
						MAINLOG.info("CASO 5");

						// Pide el DNI del alumno y lo guarda en una variable
						System.out.println(REQUESTDNI);
						dni = sc.next();

						// Muestra por consola el centro educativo que devuelve
						System.out.println(em.containtStudent(new Student(dni.toUpperCase())));

						break;
					case 6:
						MAINLOG.info("CASO 6");

						// Pide el DNI del alumno y lo guarda en una variable
						System.out.println(REQUESTDNI);
						dni = sc.next();

						// Muestra por consola el booleano que devuelve
						System.out.println(em.checkStudent(new Student(dni.toUpperCase())));

						break;
					case 7:
						MAINLOG.info("CASO 7");

						// Pide el nombre de la escuela y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLNAME);
						sc.nextLine();
						schoolName = sc.nextLine();

						// Pide el nombre de la ciudad y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLCITY);
						city = sc.nextLine();

						// Muestra por consola los estudiantes que devuelve el método
						System.out.println(em.showStudentsFromSchool(new School(schoolName, city)));

						break;
					case 8:
						MAINLOG.info("CASO 8");

						// Muestra por consola todos lso centros educativos y alumnos
						System.out.println(em.showAllStudents());

						break;
					case 9:
						MAINLOG.info("CASO 9");

						// Pide el nombre de la escuela y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLNAME);
						sc.nextLine();
						schoolName = sc.nextLine();

						// Pide el nombre de la ciudad y guarda el nombre en una variable
						System.out.println(REQUESTSCHOOLCITY);
						city = sc.nextLine();

						//Llama al método rmeoveSchool del serivicio de gestion educativo
						em.removeSchool(new School(schoolName, city));

						break;
					default:
						
						MAINLOG.warn("La opción elegida no esta en el menu vuelva a elegir otra opción.");
						System.out.println("La opción elegida no esta en el menu vuelva a elegir otra opción.");

						break;
					}

					//Se capturan las excepciones
				} catch (EqualStudent | SchoolNotFound | StudentNotFound e) {

					System.out.println(e.getMessage());
					MAINLOG.warn(e.getMessage());
				}

				//Se vulve a mostrar el menu y guarda la opción
				System.out.println(MENU);
				option = sc.nextInt();
			}

		}
	}
}
