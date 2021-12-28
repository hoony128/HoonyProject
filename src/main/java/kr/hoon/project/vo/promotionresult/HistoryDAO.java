package kr.hoon.project.vo.promotionresult;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<HistoryVO> myhistoryAll(int Id_emp){
		return sqlSession.selectList("history.myhistoryAll",Id_emp);
	}
	public List<HistoryVO> myhistoryPro(int Id_emp){
		return sqlSession.selectList("history.myhistoryPro",Id_emp);
	}
	public List<HistoryVO> myhistoryDep(int Id_emp){
		return sqlSession.selectList("history.myhistoryDep",Id_emp);
	}
	public List<HistoryVO> historyAll(){
		return sqlSession.selectList("history.historyAll");
	}
	public List<HistoryVO> historyPro(){
		return sqlSession.selectList("history.historyPro");
	}
	public List<HistoryVO> historyRes(){
		return sqlSession.selectList("history.historyRes");
	}
	public List<HistoryVO> historyDep(){
		return sqlSession.selectList("history.historyDep");
	}
}
