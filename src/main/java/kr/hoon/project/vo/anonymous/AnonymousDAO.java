package kr.hoon.project.vo.anonymous;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.helpdesk.HelpdeskVO;

@Repository
public class AnonymousDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 리스트로 가져오기!
	public List<AnonymousVO> AnoselectList (int startNo, int endNo) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return sqlSession.selectList("anonymous.AnoselectList",map);
	}
	
	// 한개 가져오기
	public AnonymousVO Anoselectbyidx(int idx) {
		return sqlSession.selectOne("anonymous.Anoselectbyidx",idx);
	}
	// 아이디를 통해 가져오기
	public AnonymousVO Anoselectbyid(int id) {
		return sqlSession.selectOne("anonymous.Anoselectbyid",id);
	}
	
	// 개수 구하기(페이징에서 필요)
	public int AnogetCount() {
		return sqlSession.selectOne("anonymous.AnogetCount");
	}
	
	//조회수 증가
	public void anohitupdate(int idx) {
		sqlSession.update("anonymous.hitupdate",idx);
	}
	
	//최대값
	public int maxidx() {
		return sqlSession.selectOne("anonymous.maxidx");
	}
	
	// 데이터에 추가하기
	public void insert(AnonymousVO vo) {
		sqlSession.insert("anonymous.insert",vo);
	}
	
	// 추가 -기록에저장
	public void CheckInsert(int Id_emp, String ip, int idx) {
		HashMap<String,Object> map =new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip", ip);
		map.put("board_idx", idx);
		sqlSession.insert("anonymous.CheckInsert",map);
	}
	
	//비밀번호
	public String getPassword(int idx) {
		return sqlSession.selectOne("anonymous.getPassword",idx);
	}
	
	// 데이터 변경하기
	public void update(String subject, String content, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("idx", idx);
		sqlSession.update("anonymous.update",map);
	}
	
	// 변경 -기록에저장
	public void CheckUpdate(int Id_emp, String ip, int idx) {
		HashMap<String,Object> map =new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip", ip);
		map.put("board_idx", idx);
		sqlSession.insert("anonymous.CheckUpdate",map);
	}
	
	// 데이터에 삭제하기
	public void delete(int idx) {
		sqlSession.update("anonymous.delete",idx);
	}
	// 삭제 -기록에저장
	public void CheckDelete(int Id_emp, String ip, int idx) {
		HashMap<String,Object> map =new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip", ip);
		map.put("board_idx", idx);
		sqlSession.insert("anonymous.CheckDelete",map);
	}
	
	// 댓글 목록
	public List<AnonymousCommentVO> commentList(int anonymous_idx){
		return sqlSession.selectList("anonymous.commentList",anonymous_idx);
	}
	// 댓글 갯수
	public int commentCount(int anonymous_idx) {
		return sqlSession.selectOne("anonymous.commentCount",anonymous_idx);
	}
	
	//댓글 추가
	public void commentInsert(AnonymousCommentVO vo) {
		sqlSession.insert("anonymous.commentInsert",vo);
	}
	
	//댓글 추가 이력
	public void CheckCommentInsert(int Id_emp, String ip, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip",ip);
		map.put("board_idx",idx);
		sqlSession.insert("anonymous.CheckCommentInsert",map);
	}
	
	// 댓글 삭제
	public void commentdelete(int idx, String password) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("password", password);
		
		sqlSession.update("anonymous.commentdelete",map);
	}
	
	// 댓글 추가이력
	public void CheckCommentDelete(int Id_emp, String ip, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip",ip);
		map.put("board_idx",idx);
		sqlSession.insert("anonymous.CheckCommentDelete",map);
	}
	
	
	// 댓글의 비밀번호 알아오기
	public String commentgetpassword(int idx) {
		return sqlSession.selectOne("anonymous.commentgetpassword",idx);
	}

	//=======================검색결과
	
	public List<AnonymousVO> searchAResult(int startNo, int endNo, String field, String text){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("field",field);
		map.put("text",text);		
		return sqlSession.selectList("anonymous.searchAResult",map);
	}
	
	public int searchACount(String field, String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("field", field);
		map.put("text", text);
		return sqlSession.selectOne("anonymous.searchACount",map);
	}


}
