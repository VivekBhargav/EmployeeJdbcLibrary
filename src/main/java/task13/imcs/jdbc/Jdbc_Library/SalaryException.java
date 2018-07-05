package task13.imcs.jdbc.Jdbc_Library;

public class SalaryException extends Exception {

	private static final long serialVersionUID = 1L;
	public SalaryException() {
	}

	public SalaryException(String message){
		super(message);
	}
}