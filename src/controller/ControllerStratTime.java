package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.PlayGame;
import view.SpecialGameRound;

public class ControllerStratTime extends Thread implements ActionListener {
	public static int timeAnswer = 60;
	public static String time;
	public int j;

	@Override
	public void run() {
		while (timeAnswer > 0) {
			timeAnswer--;
			int minute = timeAnswer / 60;
			int Second = timeAnswer - minute * 60;
			time = minute + ":" + Second;
			if (Second < 10) {
				time = minute + ":0" + Second;
				SpecialGameRound.labelTime.setForeground(Color.RED);
			}
			SpecialGameRound.labelTime.setText(time);
			try {
				j++;
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
			if (minute == 0 && Second == 0) {
				JOptionPane.showMessageDialog(null, "Time Out ! Bạn đã không giành chiến thắng vòng đặc biệt");
				PlayGame playGame = new PlayGame();
				if (playGame.lock("Time Out")) {
					SpecialGameRound.label[10].setText("Rất tiếc bạn đã thua ở vòng đặc biệt");
					SpecialGameRound.buttonAnswer.setEnabled(false);
					SpecialGameRound.buttonNext.setEnabled(false);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SpecialGameRound.buttonTime) {
			ControllerStratTime starttime = new ControllerStratTime();
			starttime.start();
			if("Chúc mừng bạn đã giành chiến thắng vòng đặc biệt".equals(ControllerGame.specialGameRound.label[10].getText())) {
				starttime.stop();
			}
		}
	}

}
