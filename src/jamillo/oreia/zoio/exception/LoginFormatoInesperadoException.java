package jamillo.oreia.zoio.exception;

public class LoginFormatoInesperadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginFormatoInesperadoException(){
		super("Imposs�vel diferenciar chaves de acesso. Padr�o de reconhecimento ':' n�o identificado");
	}

}
