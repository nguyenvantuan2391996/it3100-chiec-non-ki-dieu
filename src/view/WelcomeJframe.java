package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerGame;

public class WelcomeJframe extends JFrame {
	public static JButton buttonMenu[][] = new JButton[1][4];

	public WelcomeJframe() throws IOException {
		setTitle("Chiếc Nón Kỳ Diệu");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		// set background
		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/image/backgroup.jpg")))));
		
		// create button and label
		createButton();
		
	}
	
	/**
	 * Tạo button cho JFrame
	 */
	public void createButton() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				setLayout(null);
				buttonMenu[i][j] = new JButton();
				buttonMenu[i][j].setSize(100, 30);
				buttonMenu[i][j].setLocation((j + 1) * 150, 475);
				add(buttonMenu[i][j]);
				buttonMenu[i][j].addActionListener(new ControllerGame()); // xử lý click bên ControllerGame
			}
		}
		buttonMenu[0][0].setText("New Game");
		buttonMenu[0][1].setText("Admin");
		buttonMenu[0][2].setText("Help");
		buttonMenu[0][3].setText("Exit");
	}
}
