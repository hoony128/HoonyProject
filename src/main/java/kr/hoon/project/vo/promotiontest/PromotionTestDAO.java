package kr.hoon.project.vo.promotiontest;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.Info.InfoVO;

@Repository
public class PromotionTestDAO {

	@Autowired
	private SqlSession sqlSession;
	
	// 승진평가 대상자 모두를 리스트에 담기
	public List<PromotionTestVO> promotiontestpeople(int Id_emp_p) {
		return sqlSession.selectList("promotionTest.promotiontestpeople",Id_emp_p);
	}
	
	// 부서별 승진평가 대상자를 리스트에 담기
	public List<PromotionTestVO> promotiontestdepart(int depart,int Id_emp_p){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("depart", depart);
		map.put("Id_emp_p", Id_emp_p);
		return sqlSession.selectList("promotionTest.promotiontestdepart", map);
	}
	
	// 부장이상의 사람들의 정보를 가져온다-> 평가자 선발
	public List<InfoVO> promotionadmin(){
		return sqlSession.selectList("promotionTest.promotionadmin");
	}
	
	// 평가자의 권한을 부여하겠다
	public void givetopromotionadmin(int Id_emp) {
		sqlSession.update("promotionTest.givetopromotionadmin", Id_emp);
	}
	// 평가자의 권한을 해제하겠다
	public void nogivetopromotionadmin(int Id_emp) {
		sqlSession.update("promotionTest.nogivetopromotionadmin", Id_emp);
	}
}
