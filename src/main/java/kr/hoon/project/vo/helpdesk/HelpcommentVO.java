package kr.hoon.project.vo.helpdesk;

import java.util.Date;

public class HelpcommentVO {
	private int idx;
	private int helpdesk_idx;
	private int Id_emp;
	private String name;
	private String dname;
	private String lev;
	private String subject;
	private Date regdate;
	private int type;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getHelpdesk_idx() {
		return helpdesk_idx;
	}
	public void setHelpdesk_idx(int helpdesk_idx) {
		this.helpdesk_idx = helpdesk_idx;
	}
	public int getId_emp() {
		return Id_emp;
	}
	public void setId_emp(int id_emp) {
		Id_emp = id_emp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
