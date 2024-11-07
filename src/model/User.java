package model;

public class User {
	private String username;
	private String pass;
	private Role role;
	public User(String username, String pass, Role role) {
		super();
		this.username = username;
		this.pass = pass;
		this.role = role;
	}
	public User(String username, Role role) {
		super();
		this.username = username;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return  username + "-" + role;
	}
	
	
	
	
	

}
