package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.ControllerGame;

public class HelpJframe extends JFrame {
	public static JLabel label[][] = new JLabel[1][5];
	public static JButton buttonHelp;

	public HelpJframe() {
		setTitle("Hướng dẫn chơi game");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		// create button and label
		createJLabel();
		createButton();
		
		// set background
		JLabel background = new JLabel(new ImageIcon("src/image/helpjframe.jpg"));
		background.setSize(800, 550);
		add(background);
	}
	/**
	 * tạo label
	 */
	public void createJLabel() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 5; j++) {
				setLayout(null);
				label[i][j] = new JLabel();
				label[i][j].setSize(800, 50);
				label[i][j].setLocation(50, (j + 6) * 30);
				add(label[i][j]);
			}
		}
		label[0][0].setText(
				"Chiếc nón kỳ diệu là một trò chơi truyền hình (game show) của kênh VTV3 - Đài Truyền hình Việt Nam phối hợp cùng Viet Ba");
		label[0][1].setText(
				"Media thực hiện, được mua bản quyền dựa trên trò chơi Wheel of Fortune (Vòng quay may mắn) phát sóng từ ngày 6 tháng 1 năm 1975");
		label[0][2].setText(
				"tại Mỹ. Chương trình được lên sóng kể từ ngày 2 tháng 6 năm 2001 cho tới số cuối cùng phát sóng thứ bảy ngày 24 tháng 12 năm 2016,");
		label[0][3].setText(
				"đã phát sóng được 811 số. Ba người chơi lần lượt quay trên một mặt phẳng hình tròn (Chiếc nón) chia làm các ô để giành quyền đoán chữ");
		label[0][4].setText(
				"cái trong một cụm từ cho trước và ghi điểm, điểm số của từng người chơi sẽ quyết định phần thưởng bằng tiền mặt và hiện vật.");
	}
	/**
	 * tạo button
	 */
	public void createButton() {
		buttonHelp = new JButton("Back");
		buttonHelp.setSize(100, 30);
		buttonHelp.setLocation(350, 450);
		add(buttonHelp);
		buttonHelp.addActionListener(new ControllerGame());
	}
}
