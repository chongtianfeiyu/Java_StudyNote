package thread;

public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		T1 t1 = new T1("zyh", false);
		T1 tt1 = new T1("haha", true);
		T1 ttt1 = new T1("ttt", true);
		
		//两线程随机占用CPU
		new Thread(t1).start();
		new Thread(t1).start();
		Thread.sleep(1000);
		//两线程因为共用一个synchronized块，所以第一个线程输出完再到第二个线程
		new Thread(tt1).start();
		new Thread(tt1).start();
		Thread.sleep(1000);
		//synchronized中的 this 变量不一样，所以下面两个线程不会形成锁
		new Thread(tt1).start();
		new Thread(ttt1).start();
	}

}

class T1 implements Runnable {
	private String name;
	private static int staticInt;
	// false : 不要 synchronized
	private boolean syn;
	public T1() { }
	public T1(String n, boolean syn) { name = n; this.syn = syn; }
	
	public void run() {
		if (!syn)
			for (int i = 0; i < 5; ++i)
				System.out.println(name + ": " + i);
		else {
	/*
	 * synchronized 的 对象是线程共享的对象（不然没必要构成锁），
	 * 例如 类变量（static变量，如上面定义的staticInt）和 同一个实例的多线程之间的变量（即 t.start(); t.start(); 这两个线程共享 t 的变量） 
	 */
			 synchronized (this) {
				for (int i = 0; i < 5; ++i)
					System.out.println(name + ": " + i);
			}
		}
	}
}
