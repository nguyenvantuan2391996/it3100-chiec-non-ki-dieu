package view;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerGame;

public class StatisticJframe extends JFrame {
	public static JLabel labelStatistic[][] = new JLabel[1][3];
	public static JLabel labelKetQua = new JLabel();
	public static JButton buttonRoundSpecial;
	public static JPanel panelbuttonRoundSpecial;

	public StatisticJframe() throws SQLException {
		setTitle("Thống kê điểm số");
		setSize(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		// create Label
		createJLabel();

		// create button
		createJButton();

		// set background
		JLabel background = new JLabel(new ImageIcon("src/image/thongkejframe.png"));
		background.setSize(500, 250);
		add(background);
	}

	/**
	 * create Label
	 */
	public void createJLabel() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 3; j++) {
				labelStatistic[i][j] = new JLabel();
				labelStatistic[i][j].setSize(150, 25);
				labelStatistic[i][j].setLocation(180, (j + 1) * 25);
				add(labelStatistic[i][j]);
			}
		}

		labelKetQua.setSize(300, 25);
		labelKetQua.setLocation(120, 140);
		labelKetQua.setForeground(Color.RED);
		add(labelKetQua);
	}

	/**
	 * create Button
	 */
	public void createJButton() {
		buttonRoundSpecial = new JButton("OK");
		buttonRoundSpecial.setSize(60, 30);
		buttonRoundSpecial.setLocation(200, 180);
		buttonRoundSpecial.addActionListener(new ControllerGame());
		add(buttonRoundSpecial);
	}
}
