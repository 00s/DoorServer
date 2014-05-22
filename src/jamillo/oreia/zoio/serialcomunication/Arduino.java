package jamillo.oreia.zoio.serialcomunication;

import jamillo.oreia.zoio.DoorArduino;

/**
* @author klauder
*/
public class Arduino {
  
  /**
   * Construtor da classe Arduino
   */
  public Arduino(){
      // arduino = new ArduinoDoorController("COM7",9600);//Windows - porta e taxa de transmissão
      //arduino = new ControlePorta("/dev/ttyUSB0",9600);//Linux - porta e taxa de transmissão
  }
 
  /**
   * Envia o comando para a porta serial
   * @param button - Botão que é clicado na interface Java
   */
  public void abrir() {
	  
  }
}