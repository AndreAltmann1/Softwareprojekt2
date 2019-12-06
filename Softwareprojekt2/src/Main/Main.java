package Main;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;

import Model.DBConnector;
import View.MainView;


public class Main {

	public static void main(String[] args) {
		
		try {
			DBConnector.getEM();
			MainView mainview = new MainView();
			mainview.setVisible(true);
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(null,
					"Es konnte keine Verbindung mit der Datenbank hergestellt werden, bitte stellen Sie eine entsprechende Verbindung sicher.",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}				
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

}
