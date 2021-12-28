package kr.hoon.project.vo.promotionresult;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PromotionResultVO {

	private int idx;
	private int Id_emp_p;
	private String name_p;
	private String dname;
	private String lev_l;
	private String duty_d;
	private int Id_emp_v;
	private String name_v;
	private int testscore1;
	private int testscore2;
	private int testscore3;
	private int testscore4;
	private Date savedate;
	private int lev;
	private int depart;
	
	
	
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

	public String getDuty_d() {
		return duty_d;
	}

	public void setDuty_d(String duty_d) {
		this.duty_d = duty_d;
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
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLev_l() {
		return lev_l;
	}
	public void setLev_l(String lev_l) {
		this.lev_l = lev_l;
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
	public int getTestscore1() {
		return testscore1;
	}
	public void setTestscore1(int testscore1) {
		this.testscore1 = testscore1;
	}
	public int getTestscore2() {
		return testscore2;
	}
	public void setTestscore2(int testscore2) {
		this.testscore2 = testscore2;
	}
	public int getTestscore3() {
		return testscore3;
	}
	public void setTestscore3(int testscore3) {
		this.testscore3 = testscore3;
	}
	public int getTestscore4() {
		return testscore4;
	}
	public void setTestscore4(int testscore4) {
		this.testscore4 = testscore4;
	}
	public Date getSavedate() {
		return savedate;
	}
	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}
	@Override
	public String toString() {
		return "PromotionResultVO [idx=" + idx + ", Id_emp_p=" + Id_emp_p + ", name_p=" + name_p + ", dname=" + dname
				+ ", lev_l=" + lev_l + ", Id_emp_v=" + Id_emp_v + ", name_v=" + name_v + ", testscore1=" + testscore1
				+ ", testscore2=" + testscore2 + ", testscore3=" + testscore3 + ", testscore4=" + testscore4
				+ ", savedate=" + savedate + "]";
	}
	
	
	
}
