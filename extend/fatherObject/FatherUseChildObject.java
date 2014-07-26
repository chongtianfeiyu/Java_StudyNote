package extend.fatherObject;

public class FatherUseChildObject{
	public static void main(String[] args) {
		Someone s = new Someone();
		s.setFatherName("father");
		s.setName("child");
		Person p = (Person) s;
		/*
		 * Someon类继承了Person的getName方法，但调用时，会相当于父类在调用这个方法，so返回的是father, no "child"
		 * 但如果重载getName方法，即使是p.getName()，使用的还是Someone的getName，
		 */
		System.out.println(s.getName());
		System.out.println(p.getName());
	}
}

class Person {
	private String name;
	public String getName() {
		return this.name;
	}
	public void setName(String n) {
		this.name = n;
	}
}

class Someone extends Person{
	private String name;
	public void setName(String s) {
		this.name = s;
	}
	public void setFatherName(String s) {
		super.setName(s);
	}
	public String getN() {
		return this.name;
	}
	
//	@Override
//	public String getName() {
//		return this.name;
//	}
}