package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.ControllerClickButton;
import controller.ControllerGame;
import model.PlayGame;

public class ChiecNon extends JPanel {
	public static int i;
	public static int heSoGocQuay;
	public static int k;
	public static int timequay;
	public static int gocXoay;

	@Override
	public void paint(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/image/chiecnon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		AffineTransform at = AffineTransform.getTranslateInstance(550, 300);

		if (timequay < 90) {
			int j = 0;
			try {
				Thread.sleep(50);
				j--;
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		gocXoay = 3 * k-- + 1;
		at.rotate(Math.toRadians(gocXoay), img.getWidth() / 2, img.getHeight() / 2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, at, null);
		if (timequay-- > 0) {
			repaint(550, 300, 250, 300);
		} else {
			k = 0;
			// lấy điểm theo góc xoay được
			int goc = gocXoay % 360;
			System.out.println("góc"+goc);
			if(goc <= 0 && goc > -18 ) {
				ControllerGame.point = 1; // mất điểm
				GameJframe.label[10].setText("Bạn đã quay vào ô mất điểm");
			} else if (goc <= -18 && goc > -36){
				ControllerGame.point = 800;
			} else if (goc <= -36 && goc > -54){
				ControllerGame.point = 900;
			} else if (goc <= -54 && goc > -72){
				ControllerGame.point = 0; // thưởng
				// thưởng hoặc may mắn
				NoticeMessage.noticeMessage("Hãy nhận phần thưởng sau khi chương trình kết thức");
				GameJframe.label[10].setText("Bạn đã quay vào ô phần thưởng");
			} else if (goc <= -72 && goc > -90){
				ControllerGame.point = 300;
			} else if (goc <= -90 && goc > -108){
				ControllerGame.point = 200;
			} else if (goc <= -108 && goc > -126){
				ControllerGame.point = 2; // thêm lượt
				GameJframe.label[10].setText("Bạn đã quay vào ô thêm lượt");
			} else if (goc <= -126 && goc > -144){
				ControllerGame.point = 100;
			} else if (goc <= -144 && goc > -162){
				ControllerGame.point = 500;
			} else if (goc <= -162 && goc > -180){
				ControllerGame.point = 3; // chia đôi
				GameJframe.label[10].setText("Bạn đã quay vào ô chia đôi");
			} else if (goc <= -180 && goc > -198){
				ControllerGame.point = 600;
			} else if (goc <= -198 && goc > -216){
				ControllerGame.point = 0; // mất lượt
				// mất lượt
				if (ControllerClickButton.luotchoi == 2) {
					ControllerClickButton.luotchoi = 0;
				} else {
					ControllerClickButton.luotchoi++;
				}
				PlayGame.setLuotChoi();
				GameJframe.label[10].setText("Bạn đã quay vào ô mất lượt");
			} else if (goc <= -216 && goc > -234){
				ControllerGame.point = 700;
			} else if (goc <= -234 && goc > -252){
				ControllerGame.point = 300;
			} else if (goc <= -252 && goc > -270){
				ControllerGame.point = 0; // may mắn
				// thưởng hoặc may mắn
				NoticeMessage.noticeMessage("Hãy nhận phần thưởng sau khi chương trình kết thức");
				GameJframe.label[10].setText("Bạn đã quay vào ô may mắn");
			} else if (goc <= -270 && goc > -288){
				ControllerGame.point = 400;
			} else if (goc <= -288 && goc > -306){
				ControllerGame.point = 300;
			} else if (goc <= -306 && goc > -324){
				ControllerGame.point = 5; // x2
				GameJframe.label[10].setText("Bạn đã quay vào ô nhân đôi");
			} else if (goc <= -324 && goc > -342){
				ControllerGame.point = 200;
			} else if (goc <= -342 && goc > -360){
				ControllerGame.point = 100;
			}
			if (ControllerGame.point != 0 && ControllerGame.point != 1 && ControllerGame.point != 2 && ControllerGame.point != 3 && ControllerGame.point != 5) {
				GameJframe.label[10].setText("Bạn đã quay vào ô "+ControllerGame.point+" điểm");
			}
		}
	}
}
