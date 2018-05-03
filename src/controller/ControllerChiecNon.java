package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import view.ChiecNon;
import view.GameJframe;

public class ControllerChiecNon extends JPanel implements MouseMotionListener, MouseListener, Runnable {

	public static Thread mythread;
	public static int lucQuay;
	public static int percent;
	
	@Override
	public void run() {
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
		mythread = new Thread(new ControllerChiecNon());
		mythread.start();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mythread.stop();
		lucQuay = GameJframe.pb.getValue();
		GameJframe.pb.setValue(lucQuay);
		ChiecNon.i = lucQuay * 5;
		ChiecNon.heSoGocQuay = lucQuay / 10;
		ChiecNon.timequay = lucQuay / 10 * lucQuay * 5;
		System.out.println("luc quay la " + lucQuay);
		lucQuay = 0;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
