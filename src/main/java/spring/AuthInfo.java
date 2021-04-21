package spring;

public class AuthInfo {

	private String id;
	private String email;
	private String name;

	public AuthInfo(String id, String email, String name) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

}
