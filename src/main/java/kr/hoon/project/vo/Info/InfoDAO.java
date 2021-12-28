package kr.hoon.project.vo.Info;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InfoDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 저장하기
	public void insert(InfoVO vo) {
		sqlSession.insert("info.insert",vo);
	}
	
	// 아이디 개수 가져오기
	public int idCount(int Id_emp) {
		return sqlSession.selectOne("info.idCount",Id_emp);
	}
	
	// 비밀번호 일치 확인
	public String passwordCheck(int Id_emp) {
		return sqlSession.selectOne("info.passwordCheck",Id_emp);
	}
	// 1개 가져오기
	public InfoVO selectByUserid(int Id_emp) {
		return sqlSession.selectOne("info.selectByUserid",Id_emp);
	}


	// 상태가져오기
	public String getState(int Id_emp) {
		return sqlSession.selectOne("info.getState",Id_emp);
	}
	// 변경하기
	public void updateInfo(String first_phone,String mid_phone,String last_phone, String email, String zipcode, String addr1, String addr2, String bank, String account, int Id_emp, Date birth) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("first_phone", first_phone);
		map.put("mid_phone",mid_phone);
		map.put("last_phone",last_phone);
		map.put("email",email);
		map.put("zipcode",zipcode);
		map.put("addr1",addr1);
		map.put("addr2",addr2);
		map.put("bank",bank);
		map.put("account",account);
		map.put("Id_emp",Id_emp);
		map.put("birth", birth);
		sqlSession.update("info.update",map);
		
	}
	// 전직원 명단
	public List<InfoVO> selectAllEmp() {
		return sqlSession.selectList("info.selectAllEmp");
	}
	
	// 부서별 명단
	public List<InfoVO> selectAlldep(int depart){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("depart", depart);
		return sqlSession.selectList("info.selectDepInfo", map);
	}
	
	// 부서별 인원수
	public int getCountDepartNo (int depart) {
		return sqlSession.selectOne("info.getCountDepartNo",depart);
	}
	
	// 전체 인원수
	public int getCountAllEmp() {
		return sqlSession.selectOne("info.getCountAllEmp");
	}
	
	// 리스트(전체 인원 페이징)
	public List<InfoVO> selectList(int startNo, int endNo){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return sqlSession.selectList("info.selectListfind",map);	
	}
	// 리스트(부서 페이징)
	public List<InfoVO> selectListDep(int startNo, int endNo, int depart ){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("depart", depart);
		return sqlSession.selectList("info.selectListfindDep",map);	
	}
	
	// 비밀번호 변경하기
	public void changepassword(String password, int Id_emp) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("Id_emp", Id_emp);
		sqlSession.update("info.changepassword",map);
	}
	
	// 아이디 찾기
	public int findid(String name, String email, String mid_phone, String last_phone) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		map.put("mid_phone", mid_phone);
		map.put("last_phone", last_phone);
		return sqlSession.selectOne("info.findid",map);
	}
	
	//비밀번호 찾기 테크
	// 1. 입력한 이름과 사번의 사번이 있는가?
	public int countpwfromname(int Id_emp ,String name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("name", name);
		return sqlSession.selectOne("info.countpwfromname",map);
	}
	// 2. 이메일 찾기~!
	public String selectemail(int Id_emp) {
		return sqlSession.selectOne("info.selectemail",Id_emp);
	}
	
	// 메일을 위하여
	// 키저장
	public void updateKey(int Id_emp, String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("key", key);
		sqlSession.update("info.updateKey", map);
	}
	// 키읽기
	public String getKey(int Id_emp) {
		return sqlSession.selectOne("info.getKey", Id_emp);
	}
}
