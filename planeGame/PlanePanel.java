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
	private static ArrayList<Bullet> addList;
	
	public PlanePanel() {
		planes = new ArrayList<Plane>();
		bullets = new ArrayList<Bullet>();
		removeList = new ArrayList<Bullet>();
		addList = new ArrayList<Bullet>();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//下面有问题，若 this 改为 teString， 程序运行马上出错。		---- NULL POINTER， teString没有赋值！
		//若不改，子弹太多， 	 for (Bullet b : bullets) {  里句出错
		//应该系迭代器问题，子弹随时会删除 or 增加，而里度又重新绘制，出现矛盾
		//使用了两个数组列表，一个用于add，一个用于remove，暂时解决出现exception的问题。
		//熟悉线程与锁之间的关系后再优化
		synchronized (bullets) {
			g2.drawImage(p.getImage(), (int)p.getX(), (int)p.getY(), Plane.X_SIZE, Plane.Y_SIZE, null);
			for (Bullet bullet : addList) {
				bullets.add(bullet);
				//这样删除是会直接出现错误的！
//				addList.remove(bullet);
			}
			for (Bullet bullet : removeList) {
				bullets.remove(bullet);
//				removeList.remove(bullet);
			}
			for (Bullet b : bullets) {
				g2.fill(b.getShape());
			}
			addList = new ArrayList<Bullet>();
			removeList = new ArrayList<Bullet>();
		}
	}
	
	public synchronized void addBullet(Bullet b) {
		addList.add(b);
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
