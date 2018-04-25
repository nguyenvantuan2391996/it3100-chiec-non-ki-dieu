package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import view.ChiecNon;
import view.GameJframe;

public class ControllerChiecNon extends JPanel implements Runnable, MouseMotionListener, MouseListener {

	public static Thread mythread = new Thread(new ControllerChiecNon());
	public static int lucQuay;
	
	@Override
	public void run() {
		int percent = 0;
		for (int i = 0; i <= 100; i++) {
			percent = i;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			GameJframe.pb.setValue(percent);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		mythread.stop();
		lucQuay = GameJframe.pb.getValue();
		ChiecNon.i = (lucQuay * 50) / 10;
		ChiecNon.heSoGocQuay = lucQuay / 10;
		lucQuay = 0;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			mythread.start();
		} catch (Exception e2) {

		}
	}

}
