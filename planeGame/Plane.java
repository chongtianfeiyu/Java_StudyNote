package planeGame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Plane {
	static final int X_SIZE = 20;
	static final int Y_SIZE = 25;
	private double x, y, dx, dy;
	private Image planeImage;
	boolean up = false, right = false, down = false, left = false;
	private Rectangle2D bound;
	public Plane(Rectangle2D bound) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		planeImage = kit.getImage("/home/zyh/workspace/firstGame/src/planeGame/plane2.jpg");
		// 设置起始点
		this.bound = bound;
		x = 0;
		y = (bound.getMaxY() - bound.getMinY() - Y_SIZE) / 2;
		dx = dy = 10;
	}
	public void move() {
		if (left) {
			x -= dx;
			if (x <= 0) {
				x = 0;
			}
		}
		if (right) {
			x += dx;
			if (x >= bound.getWidth() - X_SIZE) {
				x = bound.getWidth() - X_SIZE;
			}
		}
		if (up) {
			y -= dy;
			if (y <= 0) {
				y = 0;
			}
		}
		if (down) {
			y += dy;
			if (y >= bound.getHeight() - Y_SIZE) {
				y = bound.getHeight() - Y_SIZE;
			}
		}
	}
	public Bullet shot() {
		return new Bullet(this);
	}
	public Image getImage() {
		return planeImage;
	}
	public void setDirection(int d, boolean b) {
		switch (d) {
			case KeyEvent.VK_UP:
				up = b;
				break;
			case KeyEvent.VK_DOWN:
				down = b;
				break;
			case KeyEvent.VK_RIGHT:
				right = b;
				break;
			case KeyEvent.VK_LEFT:
				left = b;
				break;
			default:
				break;
		}
	}
	public double getX() { return x; }
	public double getY() { return y; }
}
