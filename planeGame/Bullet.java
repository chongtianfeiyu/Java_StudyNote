package planeGame;

import java.awt.geom.Ellipse2D;

public class Bullet {
	private static final int X_SIZE = 5;
	private static final int Y_SIZE = 5;
	private double x, y, dx, dy;
	
	public Bullet(Plane p) {
		this.x = Plane.X_SIZE + p.getX();
		this.y = Plane.Y_SIZE / 2 + p.getY();
		dy = 1;
		dx = dy;
	}
	
	public void move() {
		x += dx;
	}
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
	}
}
