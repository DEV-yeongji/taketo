package com.taketo.www.DTO;

public class AllianceDTO {
	private int no;
	private String storeName;
	private String local;
	private String mobile;
	private String askForm;
	private String wtime;
	private String email;
	private int state;
	private int readNotice;
	
	public int getReadNotice() {
		return readNotice;
	}
	public void setReadNotice(int readNotice) {
		this.readNotice = readNotice;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAskForm() {
		return askForm;
	}
	public void setAskForm(String askForm) {
		this.askForm = askForm;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	
}
