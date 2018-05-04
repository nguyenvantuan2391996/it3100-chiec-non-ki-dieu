package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerGame;
import controller.ControllerStratTime;

public class SpecialGameRound extends GameJframe {
	public static JButton buttonTime;
	public static JLabel labelTime;
	
	public SpecialGameRound() {
		setTitle("Vòng đặc biệt");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		// create
		createButton();
		createLabel();
		createLabelOChu(ControllerGame.oChu);
		
		// create button đếm thời gian
		buttonTime = new JButton("Start");
		buttonTime.setSize(100, 32);
		buttonTime.setLocation(650, 140);
		buttonTime.addActionListener(new ControllerStratTime());
		add(buttonTime);
		
		// create label đếm thời gian
		labelTime = new JLabel();
		labelTime.setSize(100, 32);
		labelTime.setLocation(680, 100);
		add(labelTime);
		
		// set background
		JLabel background = new JLabel(new ImageIcon("src/image/gamejframe.jpg"));
		background.setSize(800, 550);
		add(background);
	}
}
