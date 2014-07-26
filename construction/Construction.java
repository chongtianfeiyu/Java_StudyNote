package construction;

public class Construction{
	public static void main(String[] args) {
		finalTest f = new finalTest("zyh");
		finalTest a = new finalTest("haha");
		System.out.println(f.name);
		//可以像指针那样使用
		f.f = new finalTest();
		f = f.f;
		System.out.println(f.f);
	}
}

class finalTest {
	private String message;
	static String name = "final";
	//会一直调用构造器，直到栈空间用完抛出异常
	//不new的话可以正常使用
	finalTest f = new finalTest();
	public finalTest() {
		this.message = "nothing";
	}
	public finalTest(String m) {
		this.message = m;
	}
	
}
