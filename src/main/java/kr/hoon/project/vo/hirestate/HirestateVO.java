package kr.hoon.project.vo.hirestate;




public class HirestateVO {

	private int idx;
	private String hirestate;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getHirestate() {
		return hirestate;
	}
	public void setHirestate(String hirestate) {
		this.hirestate = hirestate;
	}
	@Override
	public String toString() {
		return "HirestateVO [idx=" + idx + ", hirestate=" + hirestate + ", getIdx()=" + getIdx() + ", getHirestate()="
				+ getHirestate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
