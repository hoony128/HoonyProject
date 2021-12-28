package kr.hoon.project.vo.logcheck;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogCheckVO {

	private int idx;
	private int Id_emp;
	private int logType;
	private String ip;
	private Date logDate;
	
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
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	
	@Override
	public String toString() {
		return "LogCheckVO [idx=" + idx + ", Id_emp=" + Id_emp + ", logType=" + logType + ", ip=" + ip + ", logDate="
				+ logDate + "]";
	}
	
	
	
}
