package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.PlayGame;
import view.GameJframe;
import view.NoticeMessage;

public class ControllerClickButton implements ActionListener {
	public static PlayGame playGame = new PlayGame();
	public static String answer;
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == ControllerGame.gameJframe.buttonAnswer) {
				answer = JOptionPane.showInputDialog(null, "Đáp án của bạn là");
				String notice = playGame.checkDapan(ControllerGame.question, answer);
				NoticeMessage.noticeMessage(notice);
				// nếu khóa thành công tăng vòng đấu lên 1
				if(playGame.lock(notice)) {
					ControllerGame.round++;
				}
			} else if (e.getSource() == ControllerGame.gameJframe.buttonAnswer) {

			} else {
				JButton btn = (JButton) e.getSource(); // lấy đối tượng button click
				int i = (int) btn.getClientProperty("x"); // lấy tọa độ hàng
				int j = (int) btn.getClientProperty("y"); // lấy tọa độ cột
				String idImage = String.valueOf(i) + String.valueOf(j);
				String dapanPlayer = playGame.convertText(ControllerGame.gameJframe.buttonPlay[i][j].getName());
				int count = playGame.checkOChu(ControllerGame.question, dapanPlayer);
				if (count != 0) {
					ControllerGame.gameJframe.buttonPlay[i][j].setEnabled(false);
					GameJframe.label[0].setText(
							"Xin chúc mừng chữ " + dapanPlayer + " ! Có " + count + " chữ " + dapanPlayer + "");
					Image img = ImageIO.read(getClass().getResource("/image/" + idImage + ".jpg"));
					ArrayList<Integer> location = playGame.locationOChu(ControllerGame.question, dapanPlayer);
					for (Integer integer : location) {
						ControllerGame.gameJframe.labelOChu[integer].setIcon(new ImageIcon(img));
						ControllerGame.oChu--;
					}
					// mở hết các ô thông báo người chơi đoán ô chữ
					if (ControllerGame.oChu == 0) {
						answer = JOptionPane.showInputDialog(null, "Đáp án của bạn là");
						String notice = playGame.checkDapan(ControllerGame.question, answer);
						NoticeMessage.noticeMessage(notice);
						// nếu khóa thành công tăng vòng đấu lên 1
						if(playGame.lock(notice)) {
							ControllerGame.round++;
						}
					}
				} else {
					GameJframe.label[0].setText("Rất tiếc chữ " + dapanPlayer + " ! Có " + count + " chữ " + dapanPlayer + "");
				}
			}
		} catch (Exception e2) {
			NoticeMessage.noticeMessage("Hệ thống đang có lỗi");
		}
	}

}
