package jamillo.oreia.zoio;

import jamillo.oreia.zoio.exception.DoorNotOppennedException;
import jamillo.oreia.zoio.serialcomunication.ArduinoDoorController;

public class DoorArduino {

	private static DoorArduino da;

	private DoorArduino() {

	}

	public static DoorArduino getInstance() {
		if (da != null)
			return da;
		return da = new DoorArduino();
	}

	public void open() throws DoorNotOppennedException {
		ArduinoDoorController arduino = new ArduinoDoorController();
		try {
			arduino.enviaDados(97); // 'a' == 97 in ASCII
		} finally {
			arduino.close();
		}
		System.out.println("pluft plaft ziu a porta se abriu");
	}
}
