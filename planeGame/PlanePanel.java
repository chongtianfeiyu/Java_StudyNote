package planeGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PlanePanel extends JPanel{
	/**
	 * @author zyh
	 */
	private static final long serialVersionUID = 1L;
	
	private Plane p;
	private ArrayList<Plane> planes;
	private ArrayList<Bullet> bullets;
	
	public PlanePanel() {
		planes = new ArrayList<Plane>();
		bullets = new ArrayList<Bullet>();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		synchronized (this) {
			g2.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), Plane.X_SIZE, Plane.Y_SIZE, null);
			for (Bullet b : bullets) {
				g2.fill(b.getShape());
			}
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	public void addPlane(Plane plane) {
		this.p = plane;
		planes.add(p);
	}
	public void removeBullect(Bullet b) {
		bullets.remove(b);
	}
}
