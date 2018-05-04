package model;

public class ManagePoint {
	public static int pointPlayer1Round;
	public static int pointPlayer2Round;
	public static int pointPlayer3Round;
	
	/**
	 * tính điểm trả lời ô chữ đúng
	 * @param count : số ô chữ
	 * @param point : điểm quay được
	 * @return diem : điểm
	 */
	public static int getPointAnswerTrue(int count, int point) {
		int diem = point * count; // điểm quay được * số ô có
		return diem;
	}
	
	/**
	 * tính điểm quay vào ô đặc biệt : x2, chia đôi, mất điểm
	 * @param point : điểm hiện tại
	 * @param type : kiểu ô quay được
	 * @return pointcurrent : điểm
	 */
	public static int getPointAnswerTrueSpecial(String type, int point) {
		int pointcurrent = point;
		if ("matdiem".equals(type)) {
			pointcurrent = point * 0;
		} else if ("chiadoi".equals(type)) {
			pointcurrent = point / 2;
		} else if ("nhandoi".equals(type)) {
			pointcurrent = point * 2;
		}
		return pointcurrent;
	}
}
