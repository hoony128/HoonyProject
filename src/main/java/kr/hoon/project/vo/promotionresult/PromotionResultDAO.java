package kr.hoon.project.vo.promotionresult;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.promotiontest.PromotionTestVO;

@Repository
public class PromotionResultDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 결과볼때 사용!
	public void submitResult(PromotionResultVO vo) {
		sqlSession.insert("promotionresult.submitResult",vo);
	}

	
	
	// 결과를 저장!
	public void saveResult(PromotionResultVO vo) {
		sqlSession.insert("promotionresult.saveResult",vo);
	}
	
	// type을 1에서 2로 변화
	public void updateType() {
		sqlSession.update("promotionresult.updateType");
	}
	
	
	// 2차결과만 보기
	public List<PromotionResultVO> selectResultList() {
		return sqlSession.selectList("promotionresult.selectResultList");
	}
	
	public int getCountsbmit(int Id_emp_p, int Id_emp_v) {
		HashMap<String, Integer>map =new HashMap<String, Integer>();
		map.put("Id_emp_p", Id_emp_p);
		map.put("Id_emp_v", Id_emp_v);
		return sqlSession.selectOne("promotionresult.getCountsbmit",map);
	}
	
	
	
	
	//승진결과 전체
	public List<PromotionTestVO> findTestResultall(){
		return sqlSession.selectList("promotionresult.findTestResultall");
	}
	//승진결과 성공
	public List<PromotionTestVO> findTestResultsuc(){
		return sqlSession.selectList("promotionresult.findTestResultsuc");
	}
	//승진결과 전체
	public List<PromotionTestVO> findTestResultfail(){
		return sqlSession.selectList("promotionresult.findTestResultfail");
	}
	
	//승진결과 성공된 것 중 부서별로 보기
	public List<PromotionTestVO> findTestResultsucDepart(int depart){
		return sqlSession.selectList("promotionresult.findTestResultsucDepart",depart);
	}
	
	//승진을 Info에 저장
	public void updatelevInfo(int Id_emp) {
		sqlSession.update("promotionresult.updatelevInfo",Id_emp);
	}
	//이력을 추가
	public void levuphistory(int Id_emp,int depart, int lev,int duty, int state) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Id_emp", Id_emp);
		map.put("depart",depart);
		map.put("lev",lev);
		map.put("duty",duty);
		map.put("state",state);
		
		sqlSession.insert("promotionresult.levuphistory",map);
	}
	//승진평가 초기화
	public void deletepromotionread() {
		sqlSession.delete("promotionresult.deletepromotionread");
	}
	//승진시 인사고과 초기화
	public void deleteevalationread(int Id_emp) {
		sqlSession.delete("promotionresult.deleteevalationread",Id_emp);
	}
	//평가자 권한 회수
	public void updateadmininfo() {
		sqlSession.update("promotionresult.updateadmininfo");
	}
	
}