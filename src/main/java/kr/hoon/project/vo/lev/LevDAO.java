package kr.hoon.project.vo.lev;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LevDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertLev(String lev) {
		sqlSession.insert("lev.insertLev",lev);
	}
	
	public void updateLev(String lev, int idx ) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("lev", lev);
		map.put("idx", idx+"");
		
		sqlSession.update("lev.updateLev",map);
	}
	
	public void deleteLev(int idx) {
		sqlSession.delete("lev.deleteLev", idx);
	}
	
	public List<LevVO> selectAll(){
		return sqlSession.selectList("lev.selectAll");
	}
}
