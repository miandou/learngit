package email.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Email {
	
	private int id;
	
	private int user_id;
	
	private String title;
	
	private String send_date;
	
	private String send_by;
	
	private boolean read_flag;
	
	private String content;

	private User owner;

	
	private List<Email_File> email_Files;

	public List<Email_File> getEmail_Files() {
		return email_Files;
	}

	public void setEmail_Files(List<Email_File> email_Files) {
		this.email_Files = email_Files;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSend_date()
	{
		return this.send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}

	public String getSend_by() {
		return send_by;
	}

	public void setSend_by(String send_by) {
		this.send_by = send_by;
	}

	public boolean isRead_flag() {
		return read_flag;
	}

	public void setRead_flag(boolean read_flag) {
		this.read_flag = read_flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
