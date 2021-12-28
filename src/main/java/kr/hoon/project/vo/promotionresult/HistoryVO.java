package kr.hoon.project.vo.promotionresult;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HistoryVO {

	private int idx;
	private int Id_emp;
	private int type;
	private Date updatedate;
	private int lev;
	private int depart;
	private int state;
	private int duty;
	private String lev_l;
	private String depart_d;
	private String duty_du;
	private String state_s;
	private String name;
	private int lev_after;
	private String afterlev;
	
	
	
	
	public String getAfterlev() {
		return afterlev;
	}
	public void setAfterlev(String afterlev) {
		this.afterlev = afterlev;
	}
	public int getLev_after() {
		return lev_after;
	}
	public void setLev_after(int lev_after) {
		this.lev_after = lev_after;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLev_l() {
		return lev_l;
	}
	public void setLev_l(String lev_l) {
		this.lev_l = lev_l;
	}
	public String getDepart_d() {
		return depart_d;
	}
	public void setDepart_d(String depart_d) {
		this.depart_d = depart_d;
	}
	public String getDuty_du() {
		return duty_du;
	}
	public void setDuty_du(String duty_du) {
		this.duty_du = duty_du;
	}
	public String getState_s() {
		return state_s;
	}
	public void setState_s(String state_s) {
		this.state_s = state_s;
	}
	public int getDuty() {
		return duty;
	}
	public void setDuty(int duty) {
		this.duty = duty;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "HistoryVO [idx=" + idx + ", Id_emp=" + Id_emp + ", type=" + type + ", updatedate=" + updatedate
				+ ", lev=" + lev + ", depart=" + depart + ", state=" + state + ", duty=" + duty + ", lev_l=" + lev_l
				+ ", depart_d=" + depart_d + ", duty_du=" + duty_du + ", state_s=" + state_s + "]";
	}

	
	
	
	
	
}
