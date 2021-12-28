package kr.hoon.project.vo.evaluation;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.Info.InfoVO;

@Repository
public class EvaluationDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<InfoVO> selectdep(int depart){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("depart", depart);
		return sqlSession.selectList("evaluation.selectDepInfo",map);
	}
	
	public void evaluationsave(EvaluationSaveVO vo) {
		sqlSession.insert("evaluation.insertEvaluationsave",vo);
	}
	
	public int getCountEvaluationsave(int Id_emp_v) {
		return sqlSession.selectOne("evaluation.getCountEvaluationsave",Id_emp_v);
	}
	
	public void deleteEvaluationsave(int Id_emp_v) {
		sqlSession.delete("evaluation.deleteEvaluationsave",Id_emp_v);
	}
	
	public EvaluationSaveVO selectId_emp_v (int Id_emp_v) {
		return sqlSession.selectOne("evaluation.selectId_emp_v",Id_emp_v);
	}
	
	public void sumbitEvaluation(EvaluationVO vo) {
		sqlSession.insert("evaluation.insertEvaluation",vo);
	}
	public int getCountSubmitEvaluation(int Id_emp_v) {
		return sqlSession.selectOne("evaluation.getCountSubmit", Id_emp_v);
	}
	
	public void submitReadEvaluation(EvaluationVO vo) {
		sqlSession.insert("evaluation.insertEvaluationread",vo);
	}
	
	//평가 안된사원 전체
	public List<InfoVO> findevaluation(){
		return sqlSession.selectList("evaluation.findevaluation");
	}
	// 평가 안된사원 부서별!
	public List<InfoVO> findevaluationDep(int depart){
		return sqlSession.selectList("evaluation.findevaluationDep",depart);
	}
}
