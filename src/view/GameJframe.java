package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import controller.ControllerChiecNon;
import controller.ControllerClickButton;
import controller.ControllerGame;

public class GameJframe extends JFrame implements Runnable {
	public static JButton buttonPlay[][] = new JButton[2][13];
	public static JButton buttonAnswer;
	public static JButton buttonNext;
	public static JLabel label[] = new JLabel[11];
	public static JButton labelOChu[];
	public static JLabel labelchiecnon;
	public static JButton buttonRonate;
	public static Image image;
	public static JProgressBar pb;
	public ChiecNon cn = new ChiecNon();
	int i = 300;
	int j = 0;

	public GameJframe() throws IOException {
		add(cn);
	}

	public void paintGameFrame() {
		setTitle("Chiếc Nón Kỳ Diệu");
		setSize(800, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);

		// create luc ke
		createLucKe();
		
		// create button quay
		createButtonQuay();
		
		// create button and label
		createButton();
		createLabel();
		createLabelOChu(ControllerGame.oChu);

		// set kim quay
		JLabel kim = new JLabel(new ImageIcon("src/image/cursor.png"));
		kim.setSize(30, 30);
		kim.setLocation(670, 250);
		add(kim);
		
		// set background
		JLabel background = new JLabel(new ImageIcon("src/image/gamejframe.jpg"));
		background.setSize(800, 550);
		add(background);
		
		
	}
	
	/**
	 * create luc ke
	 */
	public void createLucKe() {
		pb = new JProgressBar(0, 0, 100);
		pb.setOrientation(JProgressBar.VERTICAL);
		pb.setBounds(740, 28, 28, 100);
		pb.setStringPainted(true);
		pb.setVisible(true);
		add(pb);
	}
	
	/**
	 * create button quay
	 */
	public void createButtonQuay() {
		// button quay
		buttonRonate = new JButton("Quay");
		buttonRonate.setSize(75, 32);
		buttonRonate.setLocation(720, 140);
		buttonRonate.addMouseListener(new ControllerChiecNon());
		buttonRonate.addMouseMotionListener(new ControllerChiecNon());
		add(buttonRonate);
	}

	/**
	 * tạo buttton
	 */
	public void createButton() {
		String idImage;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 13; j++) {
				idImage = String.valueOf(i) + String.valueOf(j);
				setLayout(null);
				buttonPlay[i][j] = new JButton();
				buttonPlay[i][j].setSize(32, 32);
				buttonPlay[i][j].setLocation((j + 1) * 33 + 30, (i + 1) * 33 + 400);
				buttonPlay[i][j].setName(idImage);
				try {
					Image img = ImageIO.read(getClass().getResource("/image/" + idImage + ".png"));
					buttonPlay[i][j].setIcon(new ImageIcon(img));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi");
					e.printStackTrace();
				}
				buttonPlay[i][j].putClientProperty("x", i); // Gán tọa độ
				buttonPlay[i][j].putClientProperty("y", j); // Gán tọa độ
				add(buttonPlay[i][j]);
				buttonPlay[i][j].addActionListener(new ControllerClickButton());
			}
		}

		// button trả lời
		buttonAnswer = new JButton("Trả lời");
		buttonAnswer.setSize(75, 32);
		buttonAnswer.setLocation(63, 400);
		add(buttonAnswer);
		buttonAnswer.addActionListener(new ControllerClickButton());

		// button next
		buttonNext = new JButton("Next");
		buttonNext.setSize(75, 32);
		buttonNext.setLocation(145, 400);
		add(buttonNext);
		buttonNext.addActionListener(new ControllerGame());

	}

	/**
	 * tạo label
	 */
	public void createLabel() {
		for (int i = 0; i < 11; i++) {
			label[i] = new JLabel();
		}

		// label thông báo đoán ô chữ
		label[0].setSize(386, 78);
		label[0].setLocation(170, 240);
		label[0].setForeground(Color.RED);
		label[0].setFont(new Font("", Font.BOLD, 15));
		add(label[0]);

		// label câu hỏi
		label[1].setSize(540, 28);
		label[1].setLocation(170, 230);
		label[1].setFont(new Font("", Font.BOLD, 15));
		add(label[1]);

		// label chủ đề
		label[2].setSize(300, 28);
		label[2].setLocation(10, 8);
		add(label[2]);

		// label vòng
		label[3].setSize(50, 28);
		label[3].setLocation(10, 28);
		add(label[3]);

		// label player 1
		label[4].setSize(50, 28);
		label[4].setLocation(500, 28);
		label[4].setFont(new Font("", Font.BOLD, 15));
		add(label[4]);

		// label player 2
		label[5].setSize(50, 28);
		label[5].setLocation(560, 28);
		label[5].setFont(new Font("", Font.BOLD, 15));
		add(label[5]);

		// label player 3
		label[6].setSize(50, 28);
		label[6].setLocation(620, 28);
		label[6].setFont(new Font("", Font.BOLD, 15));
		add(label[6]);

		// label điểm 1
		label[7].setSize(50, 28);
		label[7].setLocation(510, 56);
		label[7].setFont(new Font("", Font.BOLD, 15));
		add(label[7]);

		// label điểm 2
		label[8].setSize(50, 28);
		label[8].setLocation(570, 56);
		label[8].setFont(new Font("", Font.BOLD, 15));
		add(label[8]);

		// label điểm 3
		label[9].setSize(50, 28);
		label[9].setLocation(630, 56);
		label[9].setFont(new Font("", Font.BOLD, 15));
		add(label[9]);
		
		// label thông báo điểm quay
		label[10].setSize(386, 78);
		label[10].setLocation(170, 280);
		label[10].setForeground(Color.RED);
		label[10].setFont(new Font("", Font.BOLD, 15));
		add(label[10]);
	}

	/**
	 * Tạo số ô chữ tương ứng câu hỏi
	 */
	public void createLabelOChu(int count) {
		labelOChu = new JButton[count];
		for (int i = 0; i < count; i++) {
			labelOChu[i] = new JButton();
			labelOChu[i].setSize(32, 32);
			labelOChu[i].setLocation((i + 1) * 33 + 150, 180);
			add(labelOChu[i]);
		}
	}

	/**
	 * set thông báo khi người chơi chọn chữ
	 * 
	 * @param notice
	 *            : thông báo
	 */
	public void setNotice(String notice) {
		label[0].setText(notice);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
