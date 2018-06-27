package email.bean;

public class Sys_Parameter {

	private int port_smtp;
	private int port_pop3;
	private String name_domain;
	private int size_pop3;
	private int size_smtp;
	private boolean flag_pop3;
	private boolean flag_smtp;
	private int size;
	private String pos_pop3;
	private String pos_smtp;
	
	
	
	public boolean isFlag_smtp() {
		return flag_smtp;
	}
	public void setFlag_smtp(boolean flag_smtp) {
		this.flag_smtp = flag_smtp;
	}
	public String getPos_smtp() {
		return pos_smtp;
	}
	public void setPos_smtp(String pos_smtp) {
		this.pos_smtp = pos_smtp;
	}
	public int getPort_smtp() {
		return port_smtp;
	}
	public void setPort_smtp(int port_smtp) {
		this.port_smtp = port_smtp;
	}
	public int getPort_pop3() {
		return port_pop3;
	}
	public void setPort_pop3(int port_pop3) {
		this.port_pop3 = port_pop3;
	}
	public String getName_domain() {
		return name_domain;
	}
	public void setName_domain(String name_domain) {
		this.name_domain = name_domain;
	}
	public int getSize_pop3() {
		return size_pop3;
	}
	public void setSize_pop3(int size_pop3) {
		this.size_pop3 = size_pop3;
	}
	public int getSize_smtp() {
		return size_smtp;
	}
	public void setSize_smtp(int size_smtp) {
		this.size_smtp = size_smtp;
	}
	public boolean isFlag_pop3() {
		return flag_pop3;
	}
	public void setFlag_pop3(boolean flag_pop3) {
		this.flag_pop3 = flag_pop3;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getPos_pop3() {
		return pos_pop3;
	}
	public void setPos_pop3(String pos_pop3) {
		this.pos_pop3 = pos_pop3;
	}
	
	
}
