package view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ControllerGame;
import model.ManageQuestion;
import model.Question;

public class AdminJframe extends JFrame {
	public static JButton buttonMenu[][] = new JButton[1][5];
	public static JTextField jTextField[][] = new JTextField[1][5];
	public static JLabel jLabel[][] = new JLabel[1][5];
	public static JTable tableManageQuestion;
	public static JScrollPane spTable;

	public AdminJframe() throws SQLException, IOException {
		setTitle("Quản lý câu hỏi");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		
		// set background
		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/image/adminframe.jpg")))));
		
		// create button and label
		createButton();
		createJtextfiled();
		
		// show dữ liệu ra table
		tableManageQuestion = new JTable();
		spTable = new JScrollPane(tableManageQuestion);
		JPanel panel = new JPanel();
		panel.add(spTable);
		panel.setSize(450, 375);
		panel.setLocation(10, 75);
		add(panel);
		tableManageQuestion.addMouseListener(new ControllerGame()); // xử lý click bên ControllerGame
		showData();
	}

	/**
	 * Tạo button cho JFrame
	 */
	public void createButton() {
		// tạo button
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 5; j++) {
				// tạo button
				setLayout(null);
				buttonMenu[i][j] = new JButton();
				buttonMenu[i][j].setSize(100, 30);
				buttonMenu[i][j].setLocation((j + 1) * 120, 475);
				add(buttonMenu[i][j]);
				buttonMenu[i][j].addActionListener(new ControllerGame());
			}
		}
		buttonMenu[0][0].setText("Thêm");
		buttonMenu[0][1].setText("Sửa");
		buttonMenu[0][2].setText("Xóa");
		buttonMenu[0][3].setText("Back");
		buttonMenu[0][4].setText("Clear");
	}

	/**
	 * Tạo textfiled cho JFrame
	 */
	public void createJtextfiled() {
		//
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 5; j++) {
				// tạo textfiled
				jTextField[i][j] = new JTextField();
				jTextField[i][j].setSize(200, 25);
				jTextField[i][j].setLocation(550, (j + 1) * 75);
				add(jTextField[i][j]);
				// tạo label
				jLabel[i][j] = new JLabel();
				jLabel[i][j].setSize(75, 25);
				jLabel[i][j].setLocation(470, (j + 1) * 75);
				add(jLabel[i][j]);
			}
		}
		jLabel[0][0].setText("ID Câu Hỏi");
		jLabel[0][1].setText("Chủ Đề");
		jLabel[0][2].setText("Câu Hỏi");
		jLabel[0][3].setText("Đáp Án");
		jLabel[0][4].setText("Đáp Án TV");
	}

	/**
	 * Tạo table cho JFrame
	 * 
	 * @throws SQLException
	 */
	public static void showData() throws SQLException {
		// tạo table
		ManageQuestion manage = new ManageQuestion();
		ArrayList<Question> arrayQuestion = manage.getData();
		Vector cols = new Vector();
		cols.addElement("ID");
		cols.addElement("Chủ Đề");
		cols.addElement("Câu Hỏi");
		cols.addElement("Đáp Án");
		cols.addElement("Đáp Án TV");
		// tao du lieu
		Vector data = new Vector();
		for (Question question : arrayQuestion) {
			Vector user = new Vector();
			user.add(question.getQuestionid());
			user.add(question.getTopic());
			user.add(question.getQuestion());
			user.add(question.getDapan());
			user.add(question.getDapantv());
			data.add(user);
		}
		tableManageQuestion.setModel(new DefaultTableModel(data, cols));
	}
}
