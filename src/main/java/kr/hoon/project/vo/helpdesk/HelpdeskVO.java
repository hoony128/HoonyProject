package kr.hoon.project.vo.helpdesk;

import java.util.Date;
import java.util.List;

public class HelpdeskVO {

	private int idx;
	private int Id_emp;
	private String subject;
	private String content;
	private String dname;
	private String name;
	private String lev;
	private Date regdate;
	private int hit;
	private int type;
	private int commentcount;
	private List<HelpcommentVO> commentlist;
	
	
	
	public List<HelpcommentVO> getCommentlist() {
		return commentlist;
	}
	public void setCommentlist(List<HelpcommentVO> commentlist) {
		this.commentlist = commentlist;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
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
	
	
}
