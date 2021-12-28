package kr.hoon.project.vo.resignation;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResignationVO {
	private int idx;
	private int Id_emp_p;
	private String name_p;
	private String reason;
	private int Id_emp_v;
	private String name_v;
	private Date savedate;
	private String dname;
	private String duty_d;
	private String lev_l;
	private int duty;
	private int lev;
	private int type;
	private int state;
	private int depart;
	
	
	
	
	public int getDepart() {
		return depart;
	}
	public void setDepart(int depart) {
		this.depart = depart;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getId_emp_p() {
		return Id_emp_p;
	}
	public void setId_emp_p(int id_emp_p) {
		Id_emp_p = id_emp_p;
	}
	public String getName_p() {
		return name_p;
	}
	public void setName_p(String name_p) {
		this.name_p = name_p;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getId_emp_v() {
		return Id_emp_v;
	}
	public void setId_emp_v(int id_emp_v) {
		Id_emp_v = id_emp_v;
	}
	public String getName_v() {
		return name_v;
	}
	public void setName_v(String name_v) {
		this.name_v = name_v;
	}
	public Date getSavedate() {
		return savedate;
	}
	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDuty_d() {
		return duty_d;
	}
	public void setDuty_d(String duty_d) {
		this.duty_d = duty_d;
	}
	public String getLev_l() {
		return lev_l;
	}
	public void setLev_l(String lev_l) {
		this.lev_l = lev_l;
	}
	public int getDuty() {
		return duty;
	}
	public void setDuty(int duty) {
		this.duty = duty;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	
	@Override
	public String toString() {
		return "ResignationVO [idx=" + idx + ", Id_emp_p=" + Id_emp_p + ", name_p=" + name_p + ", reason=" + reason
				+ ", Id_emp_v=" + Id_emp_v + ", name_v=" + name_v + ", savedate=" + savedate + ", dname=" + dname
				+ ", duty_d=" + duty_d + ", lev_l=" + lev_l + ", duty=" + duty + ", lev=" + lev + "]";
	}
	
	
	
}
