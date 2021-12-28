package kr.hoon.project.vo.boardcheck;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardCheckDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1차 조건 검색
	public List<BoardCheckVO> boardType() {
		return sqlSession.selectList("boardcheck.boardtype");
	}
	
	//2차 조건 검색
	public List<BoardCheckVO> typeselect(String boardtype) {
		return sqlSession.selectList("boardcheck.typeselect",boardtype);
	}
	
	//검색후 보여줄 것!
	public List<BoardCheckVO> boardCheckSearch(String boardtype, String type) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("boardtype", boardtype);
		map.put("type", type);
		return sqlSession.selectList("boardcheck.boardCheckSearch",map);
	}
	
	public List<BoardCheckVO> boardCheckSearchAll(){
		return sqlSession.selectList("boardcheck.boarCehckSearchAll");
	}
}
