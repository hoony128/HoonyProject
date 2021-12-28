package kr.hoon.project.vo.resignation;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.Info.InfoVO;

@Repository
public class ResignationDAO {

	@Autowired
	private SqlSession sqlSession;
	

	public List<InfoVO> selectdep(int depart){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("depart", depart);
		return sqlSession.selectList("resignation.selectDepInfo",map);
	}
	
	
	public ResignationVO selectId_emp_v(int Id_emp_v) {
		return sqlSession.selectOne("resignation.selectId_emp_v",Id_emp_v);
	}
	
	public int getCountSubmit(int Id_emp_v) {
		return sqlSession.selectOne("resignation.getCountSubmit",Id_emp_v);
	}
	
	public void tempsave(ResignationVO vo) {
		sqlSession.insert("resignation.resignationtempsave",vo);
	}
	
	public void deletetemp(int Id_emp_v) {
		sqlSession.delete("resignation.tempdelete",Id_emp_v);
	}
	
	public void resignationviewsave(ResignationVO vo) {
		sqlSession.insert("resignation.resignationviewsave",vo);
	}


	public List<ResignationVO> resignListAll() {
		return sqlSession.selectList("resignation.resignListAll");
	}
	public List<ResignationVO> resignListDep(int depart) {
		return sqlSession.selectList("resignation.resignListDep",depart);
	}
	public void viewdelete(int Id_emp_v) {
		sqlSession.delete("resignation.viewdelete",Id_emp_v);
	}
	
	public void resignationapproval(String dep,int lev, String lev_l,String duty_d, int duty, String reason,String name_p,String name_v, int id_v,int id_p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp_p",id_p);
		map.put("Id_emp_v", id_v);
		map.put("name_p", name_p);
		map.put("name_v", name_v);
		map.put("reason", reason);
		map.put("dname", dep);
		map.put("duty_d", duty_d);
		map.put("lev_l", lev_l);
		map.put("duty", duty);
		map.put("lev", lev);
		sqlSession.insert("resignation.resignationapproval",map);
	}
	
	public List<ResignationVO> resignListApprovalAll() {
		return sqlSession.selectList("resignation.resignListApprovalAll");
	}
	public List<ResignationVO> resignListApprovalDep(int depart) {
		return sqlSession.selectList("resignation.resignListApprovalDep",depart);
	}
	
	public void resignationonlysave(String dep,int lev, String lev_l,String duty_d, int duty, String reason,String name_p,String name_v, int id_v,int id_p) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp_p",id_p);
		map.put("Id_emp_v", id_v);
		map.put("name_p", name_p);
		map.put("name_v", name_v);
		map.put("reason", reason);
		map.put("dname", dep);
		map.put("duty_d", duty_d);
		map.put("lev_l", lev_l);
		map.put("duty", duty);
		map.put("lev", lev);
		sqlSession.insert("resignation.resignationonlysave",map);
	}
	
	public void insertHistory(int Id_emp, int lev, int depart, int duty) {
		HashMap<String, Integer> map  = new HashMap<String, Integer>();
		map.put("Id_emp", Id_emp);
		map.put("lev", lev);
		map.put("depart", depart);
		map.put("duty", duty);
		sqlSession.insert("resignation.insertHistory",map);
	}
	
	public void updateInfoState(int Id_emp) {
		sqlSession.update("resignation.updateInfoState",Id_emp);
	}
	
	public void deleteapproval(int Id_emp_v) {
		sqlSession.delete("resignation.deleteapproval",Id_emp_v);
	}
	
	public void resignerNo(int Id_emp_v) {
		sqlSession.update("resignation.resignerNo",Id_emp_v);
	}
	public void resigneApprovalNo(int Id_emp_v) {
		sqlSession.update("resignation.resigneApprovalNo",Id_emp_v);
	}
}
