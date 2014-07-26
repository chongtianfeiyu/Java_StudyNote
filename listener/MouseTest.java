package listener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class MouseTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MouseFrame frame = new MouseFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class MouseFrame extends JFrame {
	public MouseFrame() {
		setTitle("Mouse");
		setSize(500, 500);
		
		MouseComponent c = new MouseComponent();
		add(c);
	}
}

class MouseComponent extends JComponent {
	public MouseComponent() {
		squares = new ArrayList<Rectangle2D>();
		current = null;
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		for (Rectangle2D r : squares)
			g2.draw(r);
	}

	/*
	 * 查找鼠标在按下时所在位置是否有矩形
	 */
	public Rectangle2D find(Point2D p) {
		//contains指的是包含，在矩形里面也是contain
		for (Rectangle2D r : squares)
			if (r.contains(p)) return r;
		return null;
	}

	public void add(Point2D p) {
		double x = p.getX();
		double y = p.getY();

		current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		squares.add(current);
		repaint();
	}

	public void remove(Rectangle2D s) {
		if (s == null) return;
		if (s == current) current = null;
		squares.remove(s);
		repaint();
	}

    private static final int SIDELENGTH = 50;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;
    /*
     * 按鼠标时的Listener
     * Adapter是实现了Listener一些方法的abstract
     */
	private class MouseHandler extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			//鼠标是否在矩形上
			current = find(event.getPoint());
			//测试点击一次鼠标后是否会一直产生mousePressed，结果否。
			System.out.println(event);
			if (current == null)
				add(event.getPoint());
		}

		public void mouseClicked(MouseEvent event) {
			current = find(event.getPoint());
			if (current != null && event.getClickCount() >= 2) {
				remove(current);
			}
		}
	}
	/*
	 * 移动鼠标时的Listener
	 */
	private class MouseMotionHandler implements MouseMotionListener {
		//移动鼠标
	    public void mouseMoved(MouseEvent event) {
	    	if (find(event.getPoint()) == null)
	    		setCursor(Cursor.getDefaultCursor());
	    	else
	    		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	    }
	    //拖拉鼠标
	    public void mouseDragged(MouseEvent event) {
	    	if (current != null) {
	    		int x = event.getX();
	    		int y = event.getY();
	    		//每移动一个像素都会输出一次
	    		System.out.println(event);
	    		current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
	    		repaint();
	    	}
	    }
	}
}


