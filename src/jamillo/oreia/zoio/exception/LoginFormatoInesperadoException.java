package jamillo.oreia.zoio.exception;

public class LoginFormatoInesperadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFormatoInesperadoException(){
		super("Impossível diferenciar chaves de acesso. Padrão de reconhecimento ':' não identificado");
	}

}
