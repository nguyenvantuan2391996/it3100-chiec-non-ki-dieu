package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.View;

public class RunGame {
	public static WelcomeJframe welcomeJframe;

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InstantiationException ex) {
					Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IllegalAccessException ex) {
					Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
				}
				try {
					welcomeJframe = new WelcomeJframe();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
