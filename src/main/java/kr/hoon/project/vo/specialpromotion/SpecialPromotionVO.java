package kr.hoon.project.vo.specialpromotion;


public class SpecialPromotionVO {

	private int idx;
	private int Id_emp;
	private String name;
	private String reason;
	private String duty;
	private String lev_now;
	private int lev_after;
	private int depart;
	private String dname;
	private int type;
	private String lev;
	private int duty_d;
	private int state;
	private int nowlev;
	
	
	
	
	public int getNowlev() {
		return nowlev;
	}
	public void setNowlev(int nowlev) {
		this.nowlev = nowlev;
	}
	public int getDuty_d() {
		return duty_d;
	}
	public void setDuty_d(int duty_d) {
		this.duty_d = duty_d;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getDepart() {
		return depart;
	}
	public void setDepart(int depart) {
		this.depart = depart;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getLev_now() {
		return lev_now;
	}
	public void setLev_now(String lev_now) {
		this.lev_now = lev_now;
	}
	public int getLev_after() {
		return lev_after;
	}
	public void setLev_after(int lev_after) {
		this.lev_after = lev_after;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SpecialPromotionVO [idx=" + idx + ", Id_emp=" + Id_emp + ", name=" + name + ", reason=" + reason
				+ ", duty=" + duty + ", lev_now=" + lev_now + ", lev_after=" + lev_after + ", dname=" + dname
				+ ", type=" + type + ", lev=" + lev + "]";
	}

	

	
}
