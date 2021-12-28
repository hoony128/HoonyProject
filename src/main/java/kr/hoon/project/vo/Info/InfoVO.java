package kr.hoon.project.vo.Info;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/*
 	Info_idx NUMBER PRIMARY KEY,
	Id_emp NUMBER NOT NULL,
	password varchar2(50) NOT NULL,
	name varchar2(20) NOT NULL,
	first_phone NUMBER NOT NULL,
	mid_phone	NUMBER NOT NULL,
	last_phone	NUMBER NOT NULL,
	email varchar2(50) NOT NULL,
	lev NUMBER NOT null,
	FOREIGN KEY(lev) REFERENCES LEV(idx),
	depart NUMBER NOT NULL,
	FOREIGN KEY(depart) REFERENCES DEPART(idx),
	zipcode varchar2(10) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL,
	hiredate timestamp NOT NULL,
	bank varchar2(10) NOT NULL,
	account varchar2(25) NOT NULL,
	temp1 varchar2(1000) NOT NULL,
	temp2 varchar2(1000) NOT NULL
 */
@XmlRootElement
public class InfoVO {
	private int Info_idx;
	private int Id_emp;
	private String password;
	private String first_phone;
	private String mid_phone;
	private String last_phone;
	private String email;
	private int lev;
	private String zipcode;
	private String addr1;
	private String addr2;
	private Date hiredate;
	private int hirestate;
	private String bank;
	private String account;
	private String temp1;
	private String name;
	private int depart;
	private int state;
	private int duty;
	private Date birth;
	private String depart_d;
	private String lev_l;
	private String duty_du;
	private int rw;
	
	
	
	public int getRw() {
		return rw;
	}
	public void setRw(int rw) {
		this.rw = rw;
	}
	public int getInfo_idx() {
		return Info_idx;
	}
	public void setInfo_idx(int info_idx) {
		Info_idx = info_idx;
	}
	public int getId_emp() {
		return Id_emp;
	}
	public void setId_emp(int id_emp) {
		Id_emp = id_emp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_phone() {
		return first_phone;
	}
	public void setFirst_phone(String first_phone) {
		this.first_phone = first_phone;
	}
	public String getMid_phone() {
		return mid_phone;
	}
	public void setMid_phone(String mid_phone) {
		this.mid_phone = mid_phone;
	}
	public String getLast_phone() {
		return last_phone;
	}
	public void setLast_phone(String last_phone) {
		this.last_phone = last_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getHirestate() {
		return hirestate;
	}
	public void setHirestate(int hirestate) {
		this.hirestate = hirestate;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getDepart_d() {
		return depart_d;
	}
	public void setDepart_d(String depart_d) {
		this.depart_d = depart_d;
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getDuty_du() {
		return duty_du;
	}
	public void setDuty_du(String duty_du) {
		this.duty_du = duty_du;
	}
	
	@Override
	public String toString() {
		return "InfoVO [Info_idx=" + Info_idx + ", Id_emp=" + Id_emp + ", password=" + password + ", first_phone="
				+ first_phone + ", mid_phone=" + mid_phone + ", last_phone=" + last_phone + ", email=" + email
				+ ", lev=" + lev + ", zipcode=" + zipcode + ", addr1=" + addr1 + ", addr2=" + addr2 + ", hiredate="
				+ hiredate + ", hirestate=" + hirestate + ", bank=" + bank + ", account=" + account + ", temp1=" + temp1
				+ ", name=" + name + ", depart=" + depart + ", state=" + state + ", duty=" + duty + ", birth=" + birth
				+ ", depart_d=" + depart_d + ", lev_l=" + lev_l + ", duty_du=" + duty_du + "]";
	}


}



