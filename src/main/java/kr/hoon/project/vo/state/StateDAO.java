package kr.hoon.project.vo.state;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class StateDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertState(String state) {
		sqlSession.insert("state.insertState",state);
	}
	
	public void updatestate(String state, int idx ) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", state);
		map.put("idx", idx+"");
		
		sqlSession.update("state.updateState",map);
	}
	
	public void deletestate(int idx) {
		sqlSession.delete("state.deleteState", idx);
	}
	
	public List<StateVO> selectAll(){
		return sqlSession.selectList("state.selectAll");
	}
	
}
