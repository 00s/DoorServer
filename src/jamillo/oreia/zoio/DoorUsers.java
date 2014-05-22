package jamillo.oreia.zoio;

import java.util.HashMap;
import java.util.Map;

public class DoorUsers {

	private static DoorUsers instance;
	private Map<String, String> users;

	private DoorUsers() {
		this.users = new HashMap<>();
		this.users.put("adolfo", "usb");
		this.users.put("jamillo", "123");
		this.users.put("alessandro", "felicia");
		this.users.put("robson", "senha");
		this.users.put("alejandro", "alejandro");
		this.users.put("rebeca", "050641");
		this.users.put("joaoclaudio", "dev");
		this.users.put("danilo", "bytes");
		this.users.put("20122014040391", "okatsuralau");
		
		
	}

	public static DoorUsers getInstance() {
		if (instance != null)
			return instance;
		return instance = new DoorUsers();
	}

	public boolean authenticate(String login, String senha) {

		if(users.containsKey(login)){ // checa se a chave existe // nullPointer em caso negativo
			System.out.println(login + "\n" + senha +"\n");
			return (users.get(login).equals(senha));
		}else{
			System.out.println("Usuario não existe");
			return false;
		}
	}
}
