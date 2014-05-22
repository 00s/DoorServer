package jamillo.oreia.zoio;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class Main {
	
	public static void main(String[] args) throws Exception{
		
		
		if (SystemTray.isSupported()) {
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
        
		new DoorServer(25678).run();
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
