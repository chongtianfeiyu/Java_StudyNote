package fanXing;

import java.io.Serializable;

public class PairTest {

	public static void main(String[] args) {
		Pair<String> p = new Pair<String>("first", "second");
		d(p);
		System.out.println(p.getFirst() + "\n" + p.getSecond());
		Pair.<String, Integer>dd(null);
	}
	//要小心这种强制转换， instanceof也是要小心的
	// ? 是通配符
	public static void d(Pair<?> a) {
		//即使 a 是 Pair<String>，也不会出错，貌似还能正常使用
		Pair<Integer> ta = (Pair<Integer>) a;
		System.out.println(ta.first + "\n" + a);
	}

}

/*
 * 泛型类
 */
class Pair<T> {
	T first, second;
	public Pair() {}
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	public T getFirst() { return first;}
	public T getSecond() { return second;}
	public void setFirst(T f) { first = f;}
	
	/*
	 * 泛型方法
	 */
	// U 是返回类型
	// Comparable 说明只能被实现了Comparable接口的类调用
	public static <T extends Comparable<?> & Serializable, U> T dd(T a) {
		return a;
	}
	
}