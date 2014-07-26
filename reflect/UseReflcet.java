package reflect;
import java.lang.reflect.*;

public class UseReflcet {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		T t= new T();
		Class cl = t.getClass();
		try {
			Field f = cl.getDeclaredField("n");
			//若 setAccessible(true)放在 println(f.get(t)) 之后，则会抛出 SecurityException异常，因为不能读private的数据
			f.setAccessible(true);
			System.out.println(f.get(t));
			f.set(t, 500);
			System.out.println(f.get(t));
		} catch (SecurityException | NoSuchFieldException e1) {
			e1.printStackTrace();
		}
	}
}

class T {
	public T() {
		
	}
	private Integer n = 100;
}