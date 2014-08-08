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
	public static String teString = "hello";
	private Plane p;
	private ArrayList<Plane> planes;
	private static ArrayList<Bullet> bullets;
	private static ArrayList<Bullet> removeList;
	
	public PlanePanel() {
		planes = new ArrayList<Plane>();
		bullets = new ArrayList<Bullet>();
		removeList = new ArrayList<Bullet>();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//下面有问题，若 this 改为 teString， 程序运行马上出错。		---- NULL POINTER， teString没有赋值！
		//若不改，子弹太多， 	 for (Bullet b : bullets) {  里句出错
		//应该系迭代器问题，子弹随时会删除 or 增加，而里度又重新绘制，出现矛盾
		synchronized (bullets) {
			g2.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), Plane.X_SIZE, Plane.Y_SIZE, null);
			for (Bullet bullet : removeList)
				bullets.remove(bullet);
			for (Bullet b : bullets) {
				g2.fill(b.getShape());
			}
		}
	}
	
	public synchronized void addBullet(Bullet b) {
		bullets.add(b);
	}
	public void addPlane(Plane plane) {
		this.p = plane;
		planes.add(p);
	}
	public synchronized void removeBullet(Bullet b) {
		removeList.add(b);
//		bullets.remove(b);
	}
}
