package kr.hoon.project.vo.duty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DutyVO {
	private int idx;
	private String duty;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	@Override
	public String toString() {
		return "DutyVO [idx=" + idx + ", duty=" + duty + "]";
	}
	
	

}
