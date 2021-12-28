package kr.hoon.project.vo.depart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartVO {

	private int idx;
	private String depart;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	@Override
	public String toString() {
		return "DepartVO [idx=" + idx + ", depart=" + depart + "]";
	}
	
	
	
	
}
