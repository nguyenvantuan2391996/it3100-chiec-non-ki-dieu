package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import connectionDB.ConnectDB;
import controller.ControllerGame;
import view.NoticeMessage;

public class PlayGame {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Kiểm tra đáp án của người chơi với đáp án câu hỏi
	 * 
	 * @param question
	 *            : đối tượng câu hỏi
	 * @return notice : thông báo
	 * @throws SQLException
	 */
	public String checkDapan(Question question, String answer) throws SQLException {
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		String notice = "";
		int parameterIndex = 1;
		String sql = "select questionid "
					+"from question "
					+"where dapantv = ? and questionid = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(parameterIndex++, answer);
			stmt.setInt(parameterIndex++, question.getQuestionid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				notice = "Chính xác";
			} else {
				notice = "Rất tiếc bạn đã trả lời sai";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return notice;
	}

	/**
	 * Kiểm tra ô chữ người chơi đoán
	 * 
	 * @param question
	 *            : đối tượng câu hỏi
	 * @return notice : thông báo
	 * @throws SQLException
	 */
	public int checkOChu(Question question, String dapanPlayer) throws SQLException {
		System.out.println(question.getDapan());
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		int count = 0;
		int parameterIndex = 1;
		String sql = "select questionid, dapan " + "from question " + "where questionid= ? and dapan like ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(parameterIndex++, question.getQuestionid());
			stmt.setString(parameterIndex++, "%" + dapanPlayer + "%");
			rs = stmt.executeQuery();
			if (!rs.next()) {
				count = 0;
			} else {
				String dapan = rs.getString("dapan");
				for (int i = 0; i < dapan.length(); i++) {
					if (dapanPlayer.equals(String.valueOf(dapan.charAt(i)))) {
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return count;
	}

	/**
	 * tìm vị trí ô chữ trong đáp án
	 * 
	 * @param question
	 *            : thông tin câu hỏi
	 * @return vị trí ô chữ
	 */
	public ArrayList<Integer> locationOChu(Question question, String dapanPlayer) {
		ArrayList<Integer> location = new ArrayList<>();
		String dapan = question.getDapan();
		for (int i = 0; i < dapan.length(); i++) {
			if (dapanPlayer.equals(String.valueOf(dapan.charAt(i)))) {
				location.add(i);
			}
		}
		return location;
	}

	/**
	 * lấy thông tin câu hỏi theo topic
	 * 
	 * @param topic
	 *            : chủ đề
	 * @return arraylistQuestion : list câu hỏi
	 * @throws SQLException
	 */
	public ArrayList<Question> getQuestionInforByTopic(String topic) throws SQLException {
		ArrayList<Question> arrayQuestion = new ArrayList<>();
		Question question;
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		int parameterIndex = 1;
		String sql = "select questionid, question, dapan, dapantv " + "from question " + "where topic = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(parameterIndex++, topic);
			rs = stmt.executeQuery();
			while (rs.next()) {
				question = new Question();
				question.setQuestionid(Integer.valueOf(rs.getString("questionid")));
				question.setQuestion(rs.getString("question"));
				question.setDapan(rs.getString("dapan"));
				question.setDapantv(rs.getString("dapantv"));
				arrayQuestion.add(question);
			}
		} catch (Exception e) {
			NoticeMessage.noticeMessage("Hệ thống đang có lỗi");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return arrayQuestion;
	}

	/**
	 * Random câu hỏi từ arraylist câu hỏi
	 * 
	 * @param arrayQuestion
	 *            : arraylist câu hỏi
	 * @return question : thông tin câu hỏi
	 */
	public Question randomQuestion(ArrayList<Question> arrayQuestion) {
		Question question = new Question();
		Random rn = new Random();
		question = arrayQuestion.get(rn.nextInt(arrayQuestion.size() - 1));
		return question;
	}

	/**
	 * tạo số ô đáp án cho câu hỏi
	 * 
	 * @return count : số ô cần tạo
	 */
	public int countOChu(Question question) {
		String dapan = question.getDapan();
		int count = dapan.length();
		return count;
	}

	/**
	 * chuyển đổi sang chữ cái
	 * 
	 * @param text
	 *            : text đổi
	 * @return chữ cái
	 */
	public String convertText(String text) {
		if ("00".equals(text)) {
			return "A";
		} else if ("01".equals(text)) {
			return "B";
		} else if ("02".equals(text)) {
			return "C";
		} else if ("03".equals(text)) {
			return "D";
		} else if ("04".equals(text)) {
			return "E";
		} else if ("05".equals(text)) {
			return "F";
		} else if ("06".equals(text)) {
			return "G";
		} else if ("07".equals(text)) {
			return "H";
		} else if ("08".equals(text)) {
			return "I";
		} else if ("09".equals(text)) {
			return "J";
		} else if ("010".equals(text)) {
			return "K";
		} else if ("011".equals(text)) {
			return "L";
		} else if ("012".equals(text)) {
			return "M";
		} else if ("10".equals(text)) {
			return "N";
		} else if ("11".equals(text)) {
			return "O";
		} else if ("12".equals(text)) {
			return "P";
		} else if ("13".equals(text)) {
			return "Q";
		} else if ("14".equals(text)) {
			return "R";
		} else if ("15".equals(text)) {
			return "S";
		} else if ("16".equals(text)) {
			return "T";
		} else if ("17".equals(text)) {
			return "U";
		} else if ("18".equals(text)) {
			return "V";
		} else if ("19".equals(text)) {
			return "W";
		} else if ("110".equals(text)) {
			return "X";
		} else if ("111".equals(text)) {
			return "Y";
		} else if ("112".equals(text)) {
			return "Z";
		}
		return text;
	}

	/**
	 * khóa ô chữ
	 * @param notice : thông báo
	 * @return true khóa thành công và ngược lại
	 */
	public boolean lock(String notice) {
		if ("Chính xác".equals(notice)) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 13; j++) {
					ControllerGame.gameJframe.buttonPlay[i][j].setEnabled(false);
				}
			}
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws SQLException {
		PlayGame a = new PlayGame();
		Question question = new Question();
		question.setQuestionid(10);
		String answer = "Bồ Đào Nh";
		System.out.println(a.checkDapan(question, answer));
		// ArrayList<Question> q = a.getQuestionInforByTopic("Bóng Đá");
		// Question question = a.randomQuestion(q);
		// System.out.println(question.getDapan());
		// String dapanPlayer = "O";
		// System.out.println(a.checkOChu(question, dapanPlayer));
		// for (Question question : q) {
		// System.out.println(question.getQuestion());
		// System.out.println(question.getDapan());
		// System.out.println(question.getDapantv());
		// }
	}
}
