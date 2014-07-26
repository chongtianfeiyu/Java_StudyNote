package listener;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * @author acer
 *
 */
public class ActionTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ActionFrame frame = new ActionFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

class ActionFrame extends JFrame {
	private JPanel buttonPanel;

	public ActionFrame() {
		setTitle("Action");
		setSize(300, 200);
		
		buttonPanel = new JPanel();
		
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow.jpg"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("blue.jpg"), Color.BLUE);
		
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		
		add(buttonPanel);
		
		InputMap iMap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		iMap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		iMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		
		ActionMap aMap = buttonPanel.getActionMap();
		aMap.put("panel.yellow", yellowAction);
		aMap.put("panel.blue", blueAction);
	}
	
	public class ColorAction extends AbstractAction {
		
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "set panel color to " + name.toLowerCase());
			putValue("color", c);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = (Color)getValue("color");
			buttonPanel.setBackground(c);
		}
		
	}
}