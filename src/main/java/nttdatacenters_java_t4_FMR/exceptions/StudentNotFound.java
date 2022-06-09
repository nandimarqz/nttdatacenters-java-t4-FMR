package nttdatacenters_java_t4_FMR.exceptions;

/**
 * Excepcion si no se encuentra el alumno
 * @author nandi
 *
 */
public class StudentNotFound extends Exception{
	
	private static final long serialVersionUID = 1L;

	public StudentNotFound(String msg) {
		super(msg);

	}

}
