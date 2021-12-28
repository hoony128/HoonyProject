package kr.hoon.project.vo.depart;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertdept(String dept) {
		sqlSession.insert("depart.insertdept",dept);
	}
	
	public void updatedept(String depart, int idx ) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("depart", depart);
		map.put("idx", idx+"");
		
		sqlSession.update("depart.updatedept",map);
	}
	
	public void deletedept(int idx) {
		sqlSession.delete("depart.deletedept", idx);
	}
	
	public List<DepartVO> selectAll(){
		return sqlSession.selectList("depart.selectAll");
	}
	
}
