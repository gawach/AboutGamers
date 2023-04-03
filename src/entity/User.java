package entity;

public class User {
	private int id_user;
	private String name_user;
	private String email_user;
	private String password_user;
	private int level_user;

	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public String getPassword_user() {
		return password_user;
	}
	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}
	public int getLevel_user() {
		return level_user;
	}
	public void setLevel_user(int level_user) {
		this.level_user = level_user;
	}
}
