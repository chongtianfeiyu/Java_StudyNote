package listener;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ActionListenerTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyWindow mw = new MyWindow();
				mw.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				mw.setVisible(true);
			}
		});
	}

}

class MyWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public MyWindow() {
		setTitle("Test Listener");		
		setBounds(100, 100, 1200, 600);
		// 布局 这个要多琢磨
		setLayout(null);
		
		
		JButton yellowButton = new JButton("Yellow");
		yellowButton.setBounds(0, 0, 100, 100);
		
		JButton blueButton = new JButton("blue");
		blueButton.setBounds(110, 0, 100, 100);
		blueButton.setBackground(Color.GREEN);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 400, 500);
		panel.add(yellowButton);
		panel.add(blueButton);
		
		add(panel);

		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		
		blueButton.addActionListener(blueAction);
		yellowButton.addActionListener(yellowAction);
		JPanel t = new JPanel();
		t.setBounds(500, 500, 100, 100);
		t.setBackground(Color.RED);
		add(t);
		
		WindowListener wl = new Terminator();
		addWindowListener(wl);
	}
	
	private class ColorAction implements ActionListener {
		public ColorAction(Color c) {
			backgroundColor = c;
		}
		public void actionPerformed(ActionEvent event) {
			panel.setBackground(backgroundColor);
		}
		private Color backgroundColor;
	}
	
	private JPanel panel;
}
//用于窗口的listener
class Terminator implements WindowListener {
	//需要改setDefaultCloseOperation(XXX)，由EXIT_ON_CLOSE -->  DO_NOTHING_ON_CLOSE
	public void windowClosing(WindowEvent e) {
		int option = JOptionPane.showConfirmDialog(null, "check out?", "OUT", JOptionPane.YES_NO_OPTION);
		
		System.out.println(option);
		if (option == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}