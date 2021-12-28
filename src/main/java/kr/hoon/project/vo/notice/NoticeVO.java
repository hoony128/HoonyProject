package kr.hoon.project.vo.notice;

import java.util.Date;

public class NoticeVO {

	private int idx;
	private int Id_emp;
	private String subject;
	private String content;
	private String dname;
	private Date regdate;
	private int hit;
	private int type;
	private int dw;
	
	
	
	public int getDw() {
		return dw;
	}
	public void setDw(int dw) {
		this.dw = dw;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getId_emp() {
		return Id_emp;
	}
	public void setId_emp(int id_emp) {
		Id_emp = id_emp;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "NoticeVO [idx=" + idx + ", Id_emp=" + Id_emp + ", subject=" + subject + ", content=" + content
				+ ", dname=" + dname + ", regdate=" + regdate + ", hit=" + hit + ", type=" + type + "]";
	}
	
}
