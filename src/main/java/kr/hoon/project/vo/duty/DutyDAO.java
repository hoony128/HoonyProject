package kr.hoon.project.vo.duty;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DutyDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertDuty(String duty) {
		sqlSession.insert("duty.insertDuty",duty);
	}
	
	public void updateDuty(String duty, int idx ) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("duty", duty);
		map.put("idx", idx+"");
		
		sqlSession.update("duty.updateDuty",map);
	}
	
	public void deleteDuty(int idx) {
		sqlSession.delete("duty.deleteDuty", idx);
	}
	
	public List<DutyVO> selectAll(){
		return sqlSession.selectList("duty.selectAll");
	}
}
