package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.ManageQuestion;
import model.PlayGame;
import model.Question;
import view.AdminJframe;
import view.CreatePlayerJframe;
import view.GameJframe;
import view.HelpJframe;
import view.NoticeMessage;
import view.RunGame;
import view.WelcomeJframe;

public class ControllerGame implements ActionListener, MouseListener {
	public static AdminJframe adminJframe;
	public static HelpJframe helpJframe;
	public static CreatePlayerJframe playerJframe;
	public static GameJframe gameJframe;
	public ManageQuestion manageQuestion = new ManageQuestion();
	public static Question question = new Question();
	public static int oChu;
	public static int round = 1;
	public static ArrayList<String> listTopicUsed = new ArrayList<>(); // danh sách topic đã chơi
	public static String player1;
	public static String player2;
	public static String player3;
	public static int pointPlayer1;
	public static int pointPlayer2;
	public static int pointPlayer3;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			// click button new game
			if (e.getSource() == WelcomeJframe.buttonMenu[0][0]) {
				RunGame.welcomeJframe.setVisible(false);
				playerJframe = new CreatePlayerJframe();
			}
			
			// click button Admin
			else if (e.getSource() == WelcomeJframe.buttonMenu[0][1]) {
				RunGame.welcomeJframe.setVisible(false);
				adminJframe = new AdminJframe();
			}
			
			// click button help
			else if (e.getSource() == WelcomeJframe.buttonMenu[0][2]) {
				RunGame.welcomeJframe.setVisible(false);
				helpJframe = new HelpJframe();
			}
			
			// click button exit
			else if (e.getSource() == WelcomeJframe.buttonMenu[0][3]) {
				RunGame.welcomeJframe.setVisible(false);
			}
			
			// click button thêm
			else if (e.getSource() == AdminJframe.buttonMenu[0][0]) {
				// lấy dữ liệu từ view
				Question question = new Question();
				question.setQuestionid(Integer.valueOf(AdminJframe.jTextField[0][0].getText()));
				question.setTopic(AdminJframe.jTextField[0][1].getText());
				question.setQuestion(AdminJframe.jTextField[0][2].getText());
				question.setDapan(AdminJframe.jTextField[0][3].getText());
				question.setDapantv(AdminJframe.jTextField[0][4].getText());
				// gọi phương thức thêm câu hỏi
				String notice = manageQuestion.addQuestion(question);
				NoticeMessage.noticeMessage(notice);
				AdminJframe.showData();

			}
			
			// click button sửa
			else if (e.getSource() == AdminJframe.buttonMenu[0][1]) {
				// lấy dữ liệu từ view
				Question question = new Question();
				question.setQuestionid(Integer.valueOf(AdminJframe.jTextField[0][0].getText()));
				question.setTopic(AdminJframe.jTextField[0][1].getText());
				question.setQuestion(AdminJframe.jTextField[0][2].getText());
				question.setDapan(AdminJframe.jTextField[0][3].getText());
				question.setDapantv(AdminJframe.jTextField[0][4].getText());
				// gọi phương thức sửa câu hỏi
				String notice = manageQuestion.editQuestion(question);
				NoticeMessage.noticeMessage(notice);
				AdminJframe.showData();

			}
			
			// click button xóa
			else if (e.getSource() == AdminJframe.buttonMenu[0][2]) {
				// lấy dữ liệu từ view
				Question question = new Question();
				question.setQuestionid(Integer.valueOf(AdminJframe.jTextField[0][0].getText()));
				// gọi phương thức xóa câu hỏi
				String notice = manageQuestion.deleteQuestion(question);
				NoticeMessage.noticeMessage(notice);
				AdminJframe.showData();

			}
			
			// click button back
			else if (e.getSource() == AdminJframe.buttonMenu[0][3]) {
				adminJframe.setVisible(false);
				RunGame.welcomeJframe.setVisible(true);
			}
			
			// click clear
			else if (e.getSource() == AdminJframe.buttonMenu[0][4]) {
				AdminJframe.jTextField[0][0].setText("");
				AdminJframe.jTextField[0][1].setText("");
				AdminJframe.jTextField[0][2].setText("");
				AdminJframe.jTextField[0][3].setText("");
				AdminJframe.jTextField[0][4].setText("");
			}
			
			// click back help
			else if (e.getSource() == HelpJframe.buttonHelp) {
				helpJframe.setVisible(false);
				RunGame.welcomeJframe.setVisible(true);
			}
			
			// click back CreatePlayerJframe
			else if (e.getSource() == CreatePlayerJframe.buttonPlayer[0][0]) {
				playerJframe.setVisible(false);
				RunGame.welcomeJframe.setVisible(true);
			}
			
			// click next CreatePlayerJframe
			else if (e.getSource() == CreatePlayerJframe.buttonPlayer[0][1]) {
				// lấy dữ liệu từ view
				String topic = playerJframe.jComboBox.getSelectedItem().toString();
				listTopicUsed.add(topic);
				if(player1 == null | player2 == null | player3 == null) {
					player1 = playerJframe.jTextFieldPlayer[0][0].getText();
					player2 = playerJframe.jTextFieldPlayer[0][1].getText();
					player3 = playerJframe.jTextFieldPlayer[0][2].getText();
				}
				playerJframe.setVisible(false);
				gameJframe = new GameJframe();
				PlayGame playGame = new PlayGame();
				ArrayList<Question> arrayQuestion = playGame.getQuestionInforByTopic(topic);
				question = playGame.randomQuestion(arrayQuestion); // lấy ngẫu nhiên câu hỏi cùng chủ đề
				oChu = playGame.countOChu(question);
				// set dữ liệu cho view
				gameJframe.label[0].setText("Chào mừng bạn đã đến vòng " + round + "");
				gameJframe.label[1].setText(question.getQuestion());
				gameJframe.label[2].setText("Chủ đề : " + topic + "");
				gameJframe.label[3].setText("Vòng " + round + "");
				gameJframe.label[4].setText(player1);
				gameJframe.label[5].setText(player2);
				gameJframe.label[6].setText(player3);
				gameJframe.createLabelOChu(oChu);
				if(pointPlayer1 != 0 | pointPlayer2 != 0 | pointPlayer3 != 0) {
					gameJframe.label[7].setText(String.valueOf(pointPlayer1));
					gameJframe.label[8].setText(String.valueOf(pointPlayer2));
					gameJframe.label[9].setText(String.valueOf(pointPlayer3));
				} else {
					gameJframe.label[7].setText(String.valueOf(0));
					gameJframe.label[8].setText(String.valueOf(0));
					gameJframe.label[9].setText(String.valueOf(0));
				}
			}
			
			// click next ở GameJframe
			else if (e.getSource() == gameJframe.buttonNext) {
				playerJframe.dispose(); // hủy đối tượng playerJframe cũ
				playerJframe = new CreatePlayerJframe();
				// set tên người chơi
				playerJframe.jTextFieldPlayer[0][0].setText(player1);
				playerJframe.jTextFieldPlayer[0][1].setText(player2);
				playerJframe.jTextFieldPlayer[0][2].setText(player3);
				gameJframe.setVisible(false);
				gameJframe.dispose(); // hủy đối tượng gameJframe cũ
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			NoticeMessage.noticeMessage("Hệ thống đang có lỗi");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = AdminJframe.tableManageQuestion.getSelectedRow();
		if (row != -1) {
			AdminJframe.jTextField[0][0].setText(AdminJframe.tableManageQuestion.getValueAt(row, 0).toString());
			AdminJframe.jTextField[0][1].setText(AdminJframe.tableManageQuestion.getValueAt(row, 1).toString());
			AdminJframe.jTextField[0][2].setText(AdminJframe.tableManageQuestion.getValueAt(row, 2).toString());
			AdminJframe.jTextField[0][3].setText(AdminJframe.tableManageQuestion.getValueAt(row, 3).toString());
			AdminJframe.jTextField[0][4].setText(AdminJframe.tableManageQuestion.getValueAt(row, 4).toString());
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
