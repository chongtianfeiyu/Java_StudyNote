package interFace;

public class Face implements face{
	public static void main(String[] args) {
		Face f = new Face();
	}
	
	@Override
	public String getN() {
		return this.n;
	}
	@Override
	public void setN(String n) {
//		this.n = n;
	}
}



interface face {
	String n = "dsf";
	public String getN();
	public void setN(String n);
}