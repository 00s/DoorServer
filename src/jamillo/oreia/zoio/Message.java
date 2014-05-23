package jamillo.oreia.zoio;

public class Message {
	
	// Retorna saudaçao a partir da hora informada
	public static String saudacao(String voce){
		int opa = Integer.parseInt(CurrentTime.getIt().split(":")[0]);

		String msg = ""; 
		
		if(opa < 7)
			msg +="O laboratório ainda não abriu, ";
		else if (opa >= 7 && opa <= 12)
			msg += "Bom dia, ";
		else if (opa > 12 && opa <= 18)
			msg += "Boa tarde, ";
		else if (opa > 18 && opa < 22)
			msg = "Boa noite, ";
		else
			msg += "Tarde demais. volte pra casa, ";
		
		msg += voce + ".\n";
		
		return msg;
	}

}
