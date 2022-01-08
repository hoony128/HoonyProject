package kr.hoon.project.vo.lev;




public class LevVO {

	private int idx;
	private String lev;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	
	@Override
	public String toString() {
		return "LevVO [idx=" + idx + ", lev=" + lev + "]";
	}
	
	
}
