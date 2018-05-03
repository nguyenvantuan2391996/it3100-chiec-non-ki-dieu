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

import model.ManagePoint;
import model.PlayGame;
import view.GameJframe;
import view.NoticeMessage;

public class ControllerClickButton implements ActionListener {
	public static PlayGame playGame = new PlayGame();
	public static String answer;
	public static int luotchoi;

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == ControllerGame.gameJframe.buttonAnswer) {
				answer = JOptionPane.showInputDialog(null, "Đáp án của bạn là");
				String notice = playGame.checkDapan(ControllerGame.question, answer);
				if("Rất tiếc bạn đã trả lời sai".equals(notice)) {
					if (luotchoi == 2) {
						luotchoi = 0;
					} else {
						luotchoi++;
					}
				} else {
					// vòng mới
					if (luotchoi == 0) {
						ManagePoint.pointPlayer1Round += ControllerGame.pointPlayer1;
					} else if (luotchoi == 1) {
						ManagePoint.pointPlayer2Round += ControllerGame.pointPlayer2;
					} else if (luotchoi == 2) {
						ManagePoint.pointPlayer3Round += ControllerGame.pointPlayer3;
					}
				}
				NoticeMessage.noticeMessage(notice);

				// nếu khóa thành công tăng vòng đấu lên 1
				if (playGame.lock(notice)) {
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

				// nếu tl không có -> chuyển lượt chơi
				if (count == 0) {
					if (luotchoi == 2) {
						luotchoi = 0;
					} else {
						luotchoi++;
					}
					PlayGame.setLuotChoi();
				} else {
					// nếu có ô chữ -> set điểm người chơi
					if (ControllerGame.point == 1) {
						if (luotchoi == 0) {
							ControllerGame.pointPlayer1 = ManagePoint.getPointAnswerTrueSpecial("matdiem",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 1) {
							ControllerGame.pointPlayer2 = ManagePoint.getPointAnswerTrueSpecial("matdiem",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 2) {
							ControllerGame.pointPlayer3 = ManagePoint.getPointAnswerTrueSpecial("matdiem",
									ControllerGame.pointPlayer1);
						}
					} else if (ControllerGame.point == 2) {
						// thêm lượt
					} else if (ControllerGame.point == 3) {
						if (luotchoi == 0) {
							ControllerGame.pointPlayer1 = ManagePoint.getPointAnswerTrueSpecial("chiadoi",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 1) {
							ControllerGame.pointPlayer2 = ManagePoint.getPointAnswerTrueSpecial("chiadoi",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 2) {
							ControllerGame.pointPlayer3 = ManagePoint.getPointAnswerTrueSpecial("chiadoi",
									ControllerGame.pointPlayer1);
						}
					} else if (ControllerGame.point == 5) {
						if (luotchoi == 0) {
							ControllerGame.pointPlayer1 = ManagePoint.getPointAnswerTrueSpecial("nhandoi",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 1) {
							ControllerGame.pointPlayer2 = ManagePoint.getPointAnswerTrueSpecial("nhandoi",
									ControllerGame.pointPlayer1);
						} else if (luotchoi == 2) {
							ControllerGame.pointPlayer3 = ManagePoint.getPointAnswerTrueSpecial("nhandoi",
									ControllerGame.pointPlayer1);
						}
					} else {
						if (luotchoi == 0) {
							ControllerGame.pointPlayer1 += ManagePoint.getPointAnswerTrue(count, ControllerGame.point);
						} else if (luotchoi == 1) {
							ControllerGame.pointPlayer2 += ManagePoint.getPointAnswerTrue(count, ControllerGame.point);
						} else if (luotchoi == 2) {
							ControllerGame.pointPlayer3 += ManagePoint.getPointAnswerTrue(count, ControllerGame.point);
						}
					}
					ControllerGame.gameJframe.label[7].setText(String.valueOf(ControllerGame.pointPlayer1));
					ControllerGame.gameJframe.label[8].setText(String.valueOf(ControllerGame.pointPlayer2));
					ControllerGame.gameJframe.label[9].setText(String.valueOf(ControllerGame.pointPlayer3));
				}

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
						if (playGame.lock(notice)) {
							ControllerGame.round++;
						}
					}
				} else {
					GameJframe.label[0]
							.setText("Rất tiếc chữ " + dapanPlayer + " ! Có " + count + " chữ " + dapanPlayer + "");
				}
			}
		} catch (Exception e2) {
			NoticeMessage.noticeMessage("Hệ thống đang có lỗi");
		}
	}

}
