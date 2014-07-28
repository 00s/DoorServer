package jamillo.oreia.zoio;

import jamillo.oreia.zoio.serialcomunication.ArduinoDoorController;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
public class Main {
	
	public static void main(String[] args) throws Exception{
		
		System.out.println("Started");
<<<<<<< HEAD
		new ArduinoDoorController().run();
=======
		new ArduinoDoorController();
>>>>>>> 733d43eecb41a6527f9b68400b24c8cb06360f86
		
		
		/*if (SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            final PopupMenu popup = new PopupMenu();
            final TrayIcon trayIcon =
                    new TrayIcon(createImage("images/icon.png", "tray icon"));
            final SystemTray tray = SystemTray.getSystemTray();
            MenuItem doorItem = new MenuItem("Serviço da porta");
            doorItem.setEnabled(false);
            MenuItem exitItem = new MenuItem("Encerrar serviço");
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popup.add(doorItem);
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
    		tray.add(trayIcon);
		}
        
<<<<<<< HEAD
		new DoorServer(25678).run();*/
=======
		new DoorServer(25678).run();
>>>>>>> 733d43eecb41a6527f9b68400b24c8cb06360f86

	}
	
	
	protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

}
