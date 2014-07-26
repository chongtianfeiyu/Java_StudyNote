package classForName;

public class Test {

	public String hi() {
		return "you got me!";
	}

	// 类加载到JVM时执行 static 里的内容，所以 Class.forName("testJava.TestList") 会执行下面的语句
	static {
		System.out.println("my name is testList");		
	}
}
