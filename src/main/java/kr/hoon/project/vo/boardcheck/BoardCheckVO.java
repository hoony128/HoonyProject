package kr.hoon.project.vo.boardcheck;

import java.util.Date;




public class BoardCheckVO {
	private int idx;
	private int Id_emp;
	private String Ip;
	private String boardtype;
	private String type;
	private Date updatedate;
	private int board_idx;
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
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public String getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	@Override
	public String toString() {
		return "BoardCheckVO [idx=" + idx + ", Id_emp=" + Id_emp + ", Ip=" + Ip + ", boardtype=" + boardtype + ", type="
				+ type + ", updatedate=" + updatedate + ", board_idx=" + board_idx + "]";
	}
	
	
}
