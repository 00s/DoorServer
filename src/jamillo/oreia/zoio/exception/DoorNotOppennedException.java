package jamillo.oreia.zoio.exception;

public class DoorNotOppennedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoorNotOppennedException(){
		super("O arduino foi incapaz de abrir a porta");
	}
	
}
