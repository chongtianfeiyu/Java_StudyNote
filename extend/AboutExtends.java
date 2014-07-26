package extend;

public class AboutExtends {

	public static void main(String[] args) {
		 AbstraTest a = new AbstraTest();
		 System.out.println(a.getS());
	}
}

class AbstraTest extends Abs{
//	protected String s;
	protected String z = "c";
	public AbstraTest() {
		this.s = "change";
	}

	public String getS() {
		return super.getS() + z;
	}
	public void setS(String n) {
		this.s = n;
	}
	
}

class Abs extends FatherFather{
//	protected String s;
	protected String z = "f";
	public Abs() {
		s = "father";
	}
	public String getS() {
		return super.getS() + z;
	}
}

class FatherFather {
	protected String s;
	protected String z = "FF";
	public FatherFather() {
		s = "fatherfather";
	}
	public String getS() {
		return this.s + z;
	}
}
