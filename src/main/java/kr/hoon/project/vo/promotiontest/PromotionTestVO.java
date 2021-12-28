package kr.hoon.project.vo.promotiontest;

public class PromotionTestVO {
	private String dname;
	private String name;
	private int Id_emp;
	private String duty_d;
	private String lev_l;
	private int score;
	private String email;
	private int depart;
	private int duty;
	private int lev;
	private int testscore;
	private int finalscore;
	private int state;
	private int type;
	private int rt;
	
	
	
	
	
	public int getRt() {
		return rt;
	}
	public void setRt(int rt) {
		this.rt = rt;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public int getId_emp() {
		return Id_emp;
	}
	public void setId_emp(int id_emp) {
		Id_emp = id_emp;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDepart() {
		return depart;
	}
	public void setDepart(int depart) {
		this.depart = depart;
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
	
	
	public int getTestscore() {
		return testscore;
	}
	public void setTestscore(int testscore) {
		this.testscore = testscore;
	}
	public int getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(int finalscore) {
		this.finalscore = finalscore;
	}
	@Override
	public String toString() {
		return "PromotionTestVO [dname=" + dname + ", name=" + name + ", Id_emp=" + Id_emp + ", duty_d=" + duty_d
				+ ", lev_l=" + lev_l + ", score=" + score + ", email=" + email + ", depart=" + depart + ", duty=" + duty
				+ ", lev=" + lev + ", testscore=" + testscore + ", finalscore=" + finalscore + "]";
	}


	
	
}
