package planeGame;

import java.awt.event.*;

import javax.swing.*;

public class PlaneFrame extends JFrame{
	/**
	 * @author zyh
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int LENGTH = 500, HEIGHT = 500;
	private PlanePanel pPanel;
	private Plane plane;
	private PlaneRunnale pRun;
	
	public PlaneFrame() {
		setTitle("MyPlane");
		setSize(LENGTH, HEIGHT);
		
		//初始化一个panel和飞机，将飞机加入到panel
		pPanel = new PlanePanel();
		plane = new Plane(getBounds());
		pPanel.addPlane(plane);
		pRun = new PlaneRunnale();
		//加了 JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT 就不行了。如果加了的话，pPanel.add(new JButton)就可以了，估计是focus的原因
		//经查看， JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT ： 当这个组件包含了拥有键盘焦点的组件时      因pPanel没有拥有焦点的组件？
		//addKeyListener(new KeyListener(){ ... }); 按键也可以这么处理。
//		InputMap iMap = pPanel.getInputMap();
//		iMap.put(KeyStroke.getKeyStroke("UP"), "plane.up");
//		iMap.put(KeyStroke.getKeyStroke("DOWN"), "plane.down");
//		iMap.put(KeyStroke.getKeyStroke("LEFT"), "plane.left");
//		iMap.put(KeyStroke.getKeyStroke("RIGHT"), "plane.right");
//		iMap.put(KeyStroke.getKeyStroke("SPACE"), "plane.space");
//		
//		ActionMap aMap = pPanel.getActionMap();
//		aMap.put("plane.up", new DirectAction(Plane.UP));
//		aMap.put("plane.down", new DirectAction(Plane.DOWN));
//		aMap.put("plane.left", new DirectAction(Plane.LEFT));
//		aMap.put("plane.right", new DirectAction(Plane.RIGHT));
//		aMap.put("plane.space", new AbstractAction() {
//			public void actionPerformed(ActionEvent e) {
//				PlaneRunnale pRun = new PlaneRunnale();
//				new Thread(pRun).start();
//			}
//		});
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				
			}
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
						|| key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
					plane.setDirection(key, false);
					plane.move();
				}
				pPanel.repaint();
			}
			//一直按键的话，会不断产生keyPressed，这一点鼠标不一样
			//但两个键一起按只能有一个键被算是一直按（另一个键会被算按下一次）
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
//				System.out.println(key);
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
						|| key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
					plane.setDirection(key, true);
					plane.move();
				}
				if (key == KeyEvent.VK_SPACE)
					new Thread(pRun).start();
				pPanel.repaint();
			}
		});
		
		add(pPanel);
	}
	
//	private class DirectAction extends AbstractAction {
//		private static final long serialVersionUID = 1L;
//		private int direct;
//		public DirectAction(int direct) {
//			this.direct = direct;
//		}
//		public void actionPerformed(ActionEvent e) {
//			plane.move();
//			pPanel.repaint();
//		}
//	}
	
	private class PlaneRunnale implements Runnable {
		public void run() {
			Bullet b = plane.shot();
			pPanel.addBullet(b);
			for (int i = 0; i < 1400; ++i) {
				synchronized (pPanel) {
					b.move();
					pPanel.repaint();
				}
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			pPanel.removeBullect(b);
		}
	}
}
