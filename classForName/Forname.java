package classForName;

public class Forname {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/*
		* Class.forName("XXX") 将 XXX 类加载到JVM中，并返回一个 Class。
		* Class类可以调用newInstance方法新建一个实例，不过类中必须定义无参数的初始化函数
		*/
		Class.forName("testJava.Test");
	}
}
