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
	private PlaneRunnable pRun;
	
	public PlaneFrame() {
		setTitle("MyPlane");
		setSize(LENGTH, HEIGHT);
		
		//初始化一个panel和飞机，将飞机加入到panel
		pPanel = new PlanePanel();
		plane = new Plane(getBounds());
		pPanel.addPlane(plane);
		pRun = new PlaneRunnable();
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
				// 若是一直按，会一直产生keyPressed，也就是会一直产生keyReleased
//				System.out.println("released: " + key);
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
						|| key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
					plane.setDirection(key, false);
//					plane.move();
				}
				pPanel.repaint();
			}
			//一直按键的话，会不断产生keyPressed，这一点鼠标不一样
			//但两个键一起按只能有一个键被算是一直按（另一个键会被算按下一次）
			//不过另一个键不会算Released
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
//				System.out.println(key);
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
						|| key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
					plane.setDirection(key, true);
					//在没用moveRunnable之前，每当按键才移动一次，很慢、很迟钝
//					plane.move();
				}
				if (key == KeyEvent.VK_SPACE) {
					new Thread(pRun).start();
				}
				pPanel.repaint();
			}
		});
		
		new Thread(new moveRunable()).start();
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
	
	//飞机移动的线程
	private class moveRunable implements Runnable {
		public void run() {
			while (true) {
				plane.move();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					System.out.println("test3");
				}
				pPanel.repaint();
			}
		}
	}
	//子弹移动的线程
	private class PlaneRunnable implements Runnable {
		public void run() {
			Bullet b = plane.shot();
			pPanel.addBullet(b);
			for (int i = 0; i < 1400; ++i) {
				b.move();
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					System.out.println("test2");
				}
			}
//			synchronized (pPanel) {
				pPanel.removeBullet(b);
//			}
		}
	}
}
