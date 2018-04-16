package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionDB.ConnectDB;
import view.AdminJframe;
import view.NoticeMessage;

public class ManageQuestion {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	/**
	 * Thêm câu hỏi
	 * 
	 * @param question
	 *            : đối tượng câu hỏi
	 * @return notice : thông báo
	 * @throws SQLException
	 */
	public String addQuestion(Question question) throws SQLException {
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		String notice = "";
		int parameterIndex = 1;
		String sql = "insert into question(questionid, question, topic, dapan, dapantv) "
					+"values(?, ?, ?, ?, ?) ";
		try {
			String sqlcheck = "select questionid, question "
							+ "from question "
							+ "where questionid = ? ";
			stmt = conn.prepareStatement(sqlcheck);
			stmt.setInt(parameterIndex++, question.getQuestionid());
			rs = stmt.executeQuery();
			if (!rs.next()) {
				parameterIndex = 1;
				stmt = conn.prepareStatement(sql);
				stmt.setInt(parameterIndex++, question.getQuestionid());
				stmt.setString(parameterIndex++, question.getQuestion());
				stmt.setString(parameterIndex++, question.getTopic());
				stmt.setString(parameterIndex++, question.getDapan());
				stmt.setString(parameterIndex++, question.getDapantv());
				stmt.executeUpdate();
				notice = "Thêm thành công";
			} else {
				notice = "Hãy dùng ID question khác";
				return notice;
			}
		} catch (Exception e) {
			notice = "Thêm thất bại";
		} finally {
			conn.close();
		}
		return notice;
	}

	/**
	 * Sửa câu hỏi
	 * 
	 * @param question
	 *            : đối tượng câu hỏi
	 * @return notice : thông báo
	 * @throws SQLException
	 */
	public String editQuestion(Question question) throws SQLException {
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		String notice = "";
		int parameterIndex = 1;
		String sql = "update question "
					+"set question = ?, topic = ?, dapan = ?, dapantv = ? "
					+"where questionid = ? ";
		try {
			parameterIndex = 1;
			stmt = conn.prepareStatement(sql);
			stmt.setString(parameterIndex++, question.getQuestion());
			stmt.setString(parameterIndex++, question.getTopic());
			stmt.setString(parameterIndex++, question.getDapan());
			stmt.setString(parameterIndex++, question.getDapantv());
			stmt.setInt(parameterIndex++, question.getQuestionid());
			stmt.executeUpdate();
			notice = "Sửa thành công";
		} catch (Exception e) {
			notice = "Sửa thất bại";
		} finally {
			conn.close();
		}
		return notice;
	}

	/**
	 * Xóa câu hỏi
	 * 
	 * @param question
	 *            : đối tượng câu hỏi
	 * @return notice : thông báo
	 * @throws SQLException
	 */
	public String deleteQuestion(Question question) throws SQLException {
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		String notice = "";
		int parameterIndex = 1;
		String sql = "delete from question "
					+"where questionid = ? ";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(parameterIndex++, question.getQuestionid());
			stmt.executeUpdate();
			notice = "Xóa thành công";
		} catch (Exception e) {
			notice = "Xóa thất bại";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return notice;
	}

	/**
	 * lấy dữ liệu question từ db
	 * 
	 * @return arrayQuestion : arraylist đối tượng Question
	 * @throws SQLException
	 */
	public ArrayList<Question> getData() throws SQLException {
		ArrayList<Question> arrayQuestion = new ArrayList<>();
		Question question;
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		int parameterIndex = 1;
		String sql = "select questionid, question, topic, dapan, dapantv "
					+"from question";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				question = new Question();
				question.setQuestionid(rs.getInt("questionid"));
				question.setTopic(rs.getString("topic"));
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
	 * lấy chủ đề
	 * 
	 * @return list[] : danh sách chủ đề
	 * @throws SQLException
	 */
	public ArrayList<String> getTopic() throws SQLException {
		ArrayList<String> list = new ArrayList<>();
		ConnectDB connect = new ConnectDB();
		conn = connect.getConnectDB();
		int parameterIndex = 0;
		String sql = "select distinct topic "
					+"from question ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("topic"));
			}
		} catch (Exception e) {
			NoticeMessage.noticeMessage("Hệ thống đang có lỗi");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}
}
