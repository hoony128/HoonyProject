package kr.hoon.project.vo.state;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StateVO {
	private int idx;
	private String state;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "StateVO [idx=" + idx + ", state=" + state + "]";
	}
	
	
	
}
