package com.taketo.www.DTO;

public class CommentDTO {
	private int no;
	private int commentNo;
	private String writeUser;
	private String contents;
	private String wtime;
	private String writeUserID;
	
	
	public String getWriteUserID() {
		return writeUserID;
	}
	public void setWriteUserId(String writeUserID) {
		this.writeUserID = writeUserID;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public void setWriteUserID(String writeUserID) {
		this.writeUserID = writeUserID;
	}
	public String getWriteUser() {
		return writeUser;
	}
	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
