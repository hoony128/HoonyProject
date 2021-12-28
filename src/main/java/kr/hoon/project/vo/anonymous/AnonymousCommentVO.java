package kr.hoon.project.vo.anonymous;

import java.util.Date;

public class AnonymousCommentVO {
	private int idx;
	private int anonymous_idx;
	private String id;
	private String password;
	private String content;
	private Date regdate;
	private int type;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getAnonymous_idx() {
		return anonymous_idx;
	}
	public void setAnonymous_idx(int anonymous_idx) {
		this.anonymous_idx = anonymous_idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	@Override
	public String toString() {
		return "AnonymousCommentVO [idx=" + idx + ", anonymous_idx=" + anonymous_idx + ", id=" + id + ", password="
				+ password + ", content=" + content + ", regdate=" + regdate + ", type=" + type + "]";
	}
}
