package planeGame;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PlaneStart {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				PlaneFrame pf = new PlaneFrame();
				pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pf.setVisible(true);
			}
		});
	}
}
