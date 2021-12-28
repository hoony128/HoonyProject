package kr.hoon.project.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.depart.DepartDAO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.duty.DutyDAO;
import kr.hoon.project.vo.duty.DutyVO;
import kr.hoon.project.vo.hirestate.HirestateDAO;
import kr.hoon.project.vo.hirestate.HirestateVO;
import kr.hoon.project.vo.lev.LevDAO;
import kr.hoon.project.vo.lev.LevVO;
import kr.hoon.project.vo.state.StateDAO;
import kr.hoon.project.vo.state.StateVO;

@Service
public class DevelopService {

	@Autowired
	private LevDAO levDAO;
	@Autowired
	private DepartDAO departDAO;
	@Autowired
	private StateDAO stateDAO;
	@Autowired
	private HirestateDAO hirestateDAO;
	@Autowired
	private DutyDAO dutyDAO;
	
	public void insertLev(String lev) {
		levDAO.insertLev(lev);
	}
	
	public void updateLev(String lev, int idx) {
		levDAO.updateLev(lev, idx);
	}
	
	public void deleteLev(int idx) {
		levDAO.deleteLev(idx);
	}
	
	public List<LevVO> selectAllLev(){
		return levDAO.selectAll();
	}
	//=========================================

	public void insertdept(String depart) {
		departDAO.insertdept(depart);
	}
	
	public void updatedept(String depart, int idx) {
		departDAO.updatedept(depart, idx);
	}
	
	public void deletedept(int idx) {
		departDAO.deletedept(idx);
	}
	
	public List<DepartVO> selectAllDept(){
		return departDAO.selectAll();
	}
	
	//=========================================
	public void insertstate(String state) {
		stateDAO.insertState(state);;
	}
	
	public void updatestate(String state, int idx) {
		stateDAO.updatestate(state, idx);
	}
	
	public void deletestate(int idx) {
		stateDAO.deletestate(idx);
	}
	
	public List<StateVO> selectAllState(){
		return stateDAO.selectAll();
	}
	//=======================================
	public void inserthirestate(String hirestate) {
		hirestateDAO.inserthirestate(hirestate);
	}
	
	public void updatehirestate(String hirestate, int idx) {
		hirestateDAO.updatehirestate(hirestate, idx);
	}
	
	public void deletehirestate(int idx) {
		hirestateDAO.deletehirestate(idx);
	}
	
	public List<HirestateVO> selectAllHirestate(){
		return hirestateDAO.selectAll();
	}
	//=======================================
	public void insertDuty(String duty) {
		dutyDAO.insertDuty(duty);
	}
	
	public void updateDuty(String duty, int idx) {
		dutyDAO.updateDuty(duty, idx);
	}
	
	public void deleteDuty(int idx) {
		dutyDAO.deleteDuty(idx);
	}
	
	public List<DutyVO> selectAllDuty(){
		return dutyDAO.selectAll();
	}
		
	
}
