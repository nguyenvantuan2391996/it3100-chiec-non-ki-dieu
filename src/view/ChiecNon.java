package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChiecNon extends JPanel implements Runnable {
	public static Thread mythread = new Thread(new ChiecNon());

	public ChiecNon() {

	}

	public static int i;
	public static int heSoGocQuay;
	
	@Override
	public void paint(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/image/chiecnon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		AffineTransform at = AffineTransform.getTranslateInstance(550, 260);

		if (i < 50) {
			int j = 0;
			try {
				Thread.sleep(50);
				j--;
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		at.rotate(Math.toRadians(heSoGocQuay * i--), img.getWidth() / 2, img.getHeight() / 2);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, at, null);
		if (i > 0) {
			repaint();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
