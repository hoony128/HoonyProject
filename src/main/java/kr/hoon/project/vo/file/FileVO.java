package kr.hoon.project.vo.file;

import java.util.Date;

public class FileVO {
	private int idx;
	private String boardType;
	private int board_idx;
	private String ofile;
	private String sfile;
	private Date savedate;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public String getOfile() {
		return ofile;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	public Date getSavedate() {
		return savedate;
	}
	public void setSavedate(Date savedate) {
		this.savedate = savedate;
	}
	@Override
	public String toString() {
		return "FileVO [idx=" + idx + ", boardType=" + boardType + ", board_idx=" + board_idx + ", ofile=" + ofile
				+ ", sfile=" + sfile + ", savedate=" + savedate + "]";
	}
	
	
	
	
}
