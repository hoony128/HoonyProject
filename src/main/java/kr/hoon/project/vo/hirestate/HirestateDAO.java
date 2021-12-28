package kr.hoon.project.vo.hirestate;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HirestateDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public void inserthirestate(String hirestate) {
		sqlSession.insert("hirestate.insertHirestate",hirestate);
	}
	
	public void updatehirestate(String hirestate, int idx ) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hirestate", hirestate);
		map.put("idx", idx+"");
		
		sqlSession.update("hirestate.updateHirestate",map);
	}
	
	public void deletehirestate(int idx) {
		sqlSession.delete("hirestate.deleteHirestate", idx);
	}
	
	public List<HirestateVO> selectAll(){
		return sqlSession.selectList("hirestate.selectAll");
	}
	
}
