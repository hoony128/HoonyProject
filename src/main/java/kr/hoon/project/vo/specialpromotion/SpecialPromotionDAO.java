package kr.hoon.project.vo.specialpromotion;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.Info.InfoVO;

@Repository
public class SpecialPromotionDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertspecialpromotion(SpecialPromotionVO vo) {
		sqlSession.insert("specialpromotion.insertspecialpromotion",vo);
	}

	public void insertspecialpromotionsave(SpecialPromotionVO vo) {
		sqlSession.insert("specialpromotion.insertspecialpromotionsave",vo);
	}
	
	public List<SpecialPromotionVO> specialpromotionList(){
		return sqlSession.selectList("specialpromotion.specialpromotionList");
	}
	public List<SpecialPromotionVO> specialpromotionListDep(int depart){
		return sqlSession.selectList("specialpromotion.specialpromotionListDep", depart);
	}
	
	public void updateInfoLev(int lev, int Id_emp) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("lev", lev);
		map.put("Id_emp",Id_emp);
		sqlSession.update("specialpromotion.updateInfoLev",map);
	}
	
	public void deletespecialpromotion(int Id_emp) {
		sqlSession.delete("specialpromotion.deletespecialpromotion",Id_emp);
	}
	
	public void levuphistory(int Id_emp, int lev, int lev_after,int depart, int duty) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Id_emp", Id_emp);
		map.put("lev", lev);
		map.put("lev_after", lev_after);
		map.put("depart", depart);
		map.put("duty",duty);
		
		sqlSession.insert("specialpromotion.levuphistory",map);
	}
	
	public List<InfoVO> selectAllEmp() {
		return sqlSession.selectList("specialpromotion.selectAllEmp");
	}
	
	public List<InfoVO> selectAlldep(int depart){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("depart", depart);
		return sqlSession.selectList("specialpromotion.selectDepInfo", map);
	}
}
