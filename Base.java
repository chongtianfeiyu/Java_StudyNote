

public class Base
{
	public static void main(String[] args)
	{
		//字符串顺序的不同会有不同的结果。
		String a = 1 + 2 + "aaa";
		String b = "aaa" + 1 + 2;
		System.out.println(a);
		System.out.println(b);
	}
}
