package jamillo.oreia.zoio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DoorServer implements Runnable {


	private ServerSocket ss;

	public DoorServer(int port) throws IOException {
		this.ss = new ServerSocket(port);
	}


	@Override
	public void run() {
		
		
		while (true) {
			System.out.println("Waiting...");

			try {
				Socket connectionSocket = ss.accept();

				new Thread(new DoorClient(connectionSocket)).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
