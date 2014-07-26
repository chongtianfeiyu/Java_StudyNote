package fengzhuang;

import java.util.GregorianCalendar;

public class Person {

	public static void main(String[] args) {
		Employee me = new Employee();
		GregorianCalendar d = me.getHireDay();
		System.out.println(me.getT());
		d.set(4028, 7, 17);
		System.out.println(me.getT());
	}

}


class Employee {
	public Employee() {
		hireDay = new GregorianCalendar();
	}
	public GregorianCalendar getHireDay() {
		return hireDay;
	}
	public String getT(){
		return hireDay.get(1) + "/" + (hireDay.get(2) + 1);
	}
	private GregorianCalendar hireDay;
}