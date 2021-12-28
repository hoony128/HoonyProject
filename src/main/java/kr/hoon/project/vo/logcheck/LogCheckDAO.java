package kr.hoon.project.vo.logcheck;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogCheckDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void login(int Id_emp, String ip) {
		HashMap<String, String> map =new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip", ip);
		sqlSession.insert("logcheck.insertlogin",map);
	}
	
	public void logout(int Id_emp, String ip) {
		HashMap<String, String> map =new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip", ip);
		sqlSession.insert("logcheck.insertlogout",map);
	}
	
	//개수
	public int getCountIdEmp(Integer Id_emp) {
		return sqlSession.selectOne("logcheck.getCountIdEmp",Id_emp);
	}
	public int getCount() {
		return sqlSession.selectOne("logcheck.getCount");
	}
	
	//리스트
	public List<LogCheckVO> selectListIdEmp(int startNo, int endNo,int Id_emp){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("Id_emp", Id_emp);
		return sqlSession.selectList("logcheck.selectListLogIdEmp",map);
		
	}
	//리스트
	public List<LogCheckVO> selectList(int startNo, int endNo){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return sqlSession.selectList("logcheck.selectListLog",map);
		
	}
	
	public List<LogCheckVO> excelselectList(){
		return sqlSession.selectList("logcheck.excelList");
	}

	public List<LogCheckVO> excelselectListIdEmp(int Id_emp){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Id_emp", Id_emp);
		return sqlSession.selectList("logcheck.excelListid",map);
	}
	
	

}
