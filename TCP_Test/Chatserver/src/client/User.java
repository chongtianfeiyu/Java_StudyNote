package client;


public class User {
	public User() {
		this.name = "匿名用户";
	}
	public User(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	private String name;
}
