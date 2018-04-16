package view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.ControllerGame;
import model.ManageQuestion;
import model.Question;

public class CreatePlayerJframe extends JFrame {
	public static JButton buttonPlayer[][] = new JButton[1][2];
	public static JLabel jLabelPlayer[][] = new JLabel[1][4];
	public static JTextField jTextFieldPlayer[][] = new JTextField[1][3];
	public static JComboBox jComboBox;
	
	public CreatePlayerJframe() throws SQLException {
		setTitle("Chiếc Nón Kỳ Diệu");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/image/adminframe.jpg")))));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi");
			e.printStackTrace();
		}
		createButton();
		createTextfield();
		createLabel();
		createComboBox();
	}

	/**
	 * tạo button
	 */
	public void createButton() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 2; j++) {
				setLayout(null);
				buttonPlayer[i][j] = new JButton();
				buttonPlayer[i][j].setSize(100, 30);
				buttonPlayer[i][j].setLocation((j + 1) * 120 + 450, 450);
				add(buttonPlayer[i][j]);
				buttonPlayer[i][j].addActionListener(new ControllerGame());
			}
		}
		buttonPlayer[0][0].setText("< Back");
		buttonPlayer[0][1].setText("Next >");
	}

	/**
	 * tạo texfield
	 */
	public void createTextfield() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 3; j++) {
				jTextFieldPlayer[i][j] = new JTextField();
				jTextFieldPlayer[i][j].setSize(200, 25);
				jTextFieldPlayer[i][j].setLocation(280, (j + 1) * 100);
				add(jTextFieldPlayer[i][j]);
			}
		}
	}

	/**
	 * tạo label
	 */
	public void createLabel() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				jLabelPlayer[i][j] = new JLabel();
				jLabelPlayer[i][j].setSize(75, 25);
				jLabelPlayer[i][j].setLocation(200, (j + 1) * 100);
				add(jLabelPlayer[i][j]);
			}
		}
		jLabelPlayer[0][0].setText("Player 1");
		jLabelPlayer[0][1].setText("Player 2");
		jLabelPlayer[0][2].setText("Player 3");
		jLabelPlayer[0][3].setText("Chủ Đề");
	}
	
	public void createComboBox() throws SQLException {
		ManageQuestion manage = new ManageQuestion();
		ArrayList<String> arrayTopic = manage.getTopic();
		String cmbList[] = new String[arrayTopic.size() - ControllerGame.listTopicUsed.size()];
		int indexparam = 0;
		int check = 0;
		for (String list : arrayTopic) {
			if (!ControllerGame.listTopicUsed.isEmpty()) {
				for (String listUsed : ControllerGame.listTopicUsed) {
					if (listUsed.equals(list)) {
						check++;
					}
				}
				if (check == 0) {
					cmbList[indexparam++] = list;
				} else {
					check = 0;
				}
			} else {
				cmbList[indexparam++] = list;
			}
		}
		jComboBox = new JComboBox(cmbList);
		jComboBox.setSize(100, 25);
		jComboBox.setLocation(280, 400);
		jComboBox.setName("cmbTopic");
		add(jComboBox);
	}
	public static void main(String[] args) throws SQLException {
		CreatePlayerJframe a = new CreatePlayerJframe();
	}
}
