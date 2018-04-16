package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NoticeMessage extends JFrame {
	/**
	 * hiển thị thông báo
	 * @param notice : thông báo
	 */
	public static void noticeMessage(String notice) {
		JOptionPane.showMessageDialog(null, notice);
	}
}
