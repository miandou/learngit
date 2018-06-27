package email.bean;

import java.util.List;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String nickname;
	private int power;
	private boolean disable_flag;
	private String create_date;
	private boolean isfilter;

	
	

	public boolean isIsfilter() {
		return isfilter;
	}
	public void setIsfilter(boolean isfilter) {
		this.isfilter = isfilter;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public boolean isDisable_flag() {
		return disable_flag;
	}
	public void setDisable_flag(boolean disable_flag) {
		this.disable_flag = disable_flag;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	
	
	
}
