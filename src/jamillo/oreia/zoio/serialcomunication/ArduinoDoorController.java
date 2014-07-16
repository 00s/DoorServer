package jamillo.oreia.zoio.serialcomunication;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class ArduinoDoorController implements SerialPortEventListener, Runnable{
	private OutputStream serialInOut;
	private SerialPort serialPort;

	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	private static final CharSequence MASTER_KEY = "0A 14 68 A1";
	/** Default bits per second for COM port. */
	private final int DATA_RATE = 9600;
	
	private final String PORTA_COM = "COM7";
	
	public ArduinoDoorController() {
		//TODO verificar necessidade do construtor
		this.initialize();
	}

	/**
	 * Médoto que verifica se a comunicação com a porta serial está ok
	 */
	private void initialize() {

		CommPortIdentifier portId = null;
		
		try {
			// Tenta verificar se a porta COM informada existe
			portId = CommPortIdentifier.getPortIdentifier(this.PORTA_COM);
			} catch (NoSuchPortException npe) {
			// Caso a porta COM não exista será exibido um erro
			System.out.println("Porta COM não encontrada.");
			}
		
		try {	
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			
			serialInOut = serialPort.getOutputStream();
			serialPort.setSerialPortParams(this.DATA_RATE, // taxa de transferência da porta
					SerialPort.DATABITS_8, // taxa de 10 bits 8 (envio)
					SerialPort.STOPBITS_1, // taxa de 10 bits 1 (recebimento)
					SerialPort.PARITY_NONE); // receber e enviar dados
			
			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que fecha a comunicação com a porta serial
	 */
	public synchronized void close() {
		try {
			serialInOut.close();
			serialPort.removeEventListener();
			serialPort.close();
		} catch (IOException e) {
			System.out.println("Não foi possível fechar porta COM.");
		}
	}

	/**
	 * @param opcao
	 *            - Valor a ser enviado pela porta serial
	 */
	public void enviaDados(int opcao) {
		try {
			serialInOut.write(opcao);// escreve o valor de opcao na porta serial
			serialInOut.flush();
			System.out.println("opcao = " + opcao + " enviada.\n");
		} catch (IOException ex) {
			System.out.println("Não foi possível enviar o dado. ");
		}
	}

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		 if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
		    try {
		        String inputLine=null;
		        while (input.ready()) {
		            inputLine = input.readLine();
		            
		            if(!inputLine.contains("Tag")){
		            	System.out.println(inputLine);
		            	if(isValid(inputLine)){
		            		enviaDados(97);
		            		System.out.println("Acesso liberado ");
		            	}else if(isMasterKey(inputLine)){
		            		//TODO cadastrar nova tag
		            		enviaDados(99);
		            		System.out.println("Master Key");
		            		while(oEvent.getEventType() != SerialPortEvent.DATA_AVAILABLE) {
		            			
		            			
		            		    try {
		            		        String newCardInputLine=null;
		            		        while (input.ready()) {
		            		        	newCardInputLine = input.readLine();
		            		            
		            		            if(!inputLine.contains("Tag")){
		            		            	tags.add(inputLine);
		            		            	System.out.println("Tag cadastrada: "+ newCardInputLine);
		            		            }
		            		        }
	            		        }catch(Exception e){
	            		        	e.printStackTrace();
	            		        }
	            		    }

		            	}else{
		            		enviaDados(98);
		            		System.out.println("Acesso negado");
		            	}
		            }
		        }
		    } catch (Exception e) {
		        System.err.println(e.toString());
		    }
		 }
		// Ignore all the other eventTypes, but you should consider the other ones.
		}
	private boolean isMasterKey(String inputLine) {
		if(inputLine.contains(MASTER_KEY))
			return true;
		return false;
	}
	
	
	private List<String> tags = new LinkedList<String>();
	private boolean isValid(String uid){
		for(String tag : tags){
			if(uid.contains(tag))
				return true;
		}
		return false;
	}

	@Override
	public void run() {
	}
}