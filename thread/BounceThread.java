package thread;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class BounceThread {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
			{
				public void run() {
					JFrame frame = new BounceFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
	}
}

/*
 * 用于添加按钮 和 ball 
 */
class BounceFrame extends JFrame {
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");

		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener()
			{
				public void actionPerformed(ActionEvent event) {
					addBall();
				}
			});
		addButton(buttonPanel, "Close", new ActionListener()
			{	
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
			});
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	public void addBall() {
		BallRunnable ballRun = new BallRunnable();
		new Thread(ballRun).start();
	}

	private BallComponent comp;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	class BallRunnable implements Runnable {
		public void run() {
			Ball ball = new Ball();
			comp.add(ball);
			for (int i = 1; i <= STEPS; ++i) {
				ball.move(comp.getBounds());
				comp.repaint();
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) { }
			}
		}
	}
}

/*
 * 用于绘制和添加 ball
 */
class BallComponent extends JPanel {
	public void add(Ball b) {
		balls.add(b);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getShape());
		}
	}

	private ArrayList<Ball> balls = new ArrayList<Ball>();
}

class Ball {
	public Ball() {
		x = Math.random() * 500;
		y = Math.random() * 500;
		dx = Math.random() > 0.5 ? 1 : -1;
		dy = Math.random() > 0.5 ? 1 : -1;
	}
	/*
	 * bounds 得到 panel 的边界，当球碰到边界时 偏移量 改变
	 */
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		// x轴 和 y轴 分开来看， x轴上， 球碰到左边（小于getMinX） 和 球碰到右边（注意球的边界，要加XSIZE） 偏移量dx都要改
		if (x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		} 
	}

	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}

	private static final int XSIZE = 5;
	private static final int YSIZE = 5;
	private double x = 0;
	private double y = 0;
	private double dx = 1;
	private double dy = 1;
}