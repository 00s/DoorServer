package jamillo.oreia.zoio;

import jamillo.oreia.zoio.exception.DoorNotOppennedException;
import jamillo.oreia.zoio.exception.LoginFormatoInesperadoException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class DoorClient implements Runnable {

	private Socket client;

	public DoorClient(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {

		try {
			try {
				System.out.println("Atendendo cliente "
						+ this.client.getRemoteSocketAddress().toString());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						client.getInputStream()));

				DataOutputStream out = new DataOutputStream(
						client.getOutputStream());

				String mensagem = in.readLine();

				if (mensagem == null)
					return;

				String tokens[] = mensagem.split(":");

				if (tokens.length == 2) {

					if (DoorUsers.getInstance().authenticate(tokens[0],
							tokens[1])) {
						try {
							DoorArduino.getInstance().open();
							out.writeBytes("OK\n");
						} catch (DoorNotOppennedException e) {
							out.writeBytes("FAIL\n");
							System.out.println("FAIL");
						}

					} else {
						System.out.println("Senha inválida");
						out.writeBytes("NOK\n");
						System.out.println("NOK");
					}
				} else {
					throw new LoginFormatoInesperadoException();
				}
			} finally {
				this.client.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
