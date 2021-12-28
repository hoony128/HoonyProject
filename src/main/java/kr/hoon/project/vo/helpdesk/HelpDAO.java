package kr.hoon.project.vo.helpdesk;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.file.FileVO;
import kr.hoon.project.vo.file.FilehistoryVO;
import kr.hoon.project.vo.notice.NoticeVO;

@Repository
public class HelpDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 리스트
	public List<HelpdeskVO> HelpselectList (int startNo, int endNo){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo",endNo);
		return sqlSession.selectList("help.helpselectList",map);
	}
	
	// 1개 정보가져오기
	public HelpdeskVO helpselectbyidx(int idx) {
		return sqlSession.selectOne("help.helpselectbyidx",idx);
	}
	// 1개 Id로 정보가져오기
	public HelpdeskVO helpselectbyid(int Id_emp) {
		return sqlSession.selectOne("help.helpselectbyid",Id_emp);
	}
	// 개수 구하기
	public int helpgetCountTotal() {
		return sqlSession.selectOne("help.helpgetCountTotal");
	}
	// 글을 저장하기
	public void insertHelpDesk (HelpdeskVO vo) {
		sqlSession.insert("help.insertHelpDesk",vo);
	}
	// idx최고값 가져오기
	public int helpdeskmaxgetIdx() {
		return sqlSession.selectOne("help.helpdeskmaxgetIdx");
	}
	//파일 저장
	public void helpFileinsert(FileVO vo) {
		sqlSession.insert("help.helpFileinsert",vo);
	}
	//boardcheck에 저장
	public void helpCheckInsert(int Id_emp, String ip, int board_idx) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip", ip);
		map.put("board_idx", board_idx+"");
		sqlSession.insert("help.helpCheckInsert",map);
	}
	//조회수 증가
	public void helphitupdate(int idx) {
		sqlSession.update("help.hitupdate",idx);
	}
	// idx에 맞는 파일명 가져오기
	public FileVO helpfileload(int board_idx) {
		return sqlSession.selectOne("help.fileload",board_idx);
	}
	//삭제 -리스트에서
	public void helpdeskdelete (int idx) {
		sqlSession.update("help.helpdeskdelete",idx);
	}
	//삭제 - 기록에 남기기
	public void helpdeskCheckDelete(int Id_emp, String ip,int board_idx) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip", ip);
		map.put("board_idx", board_idx+"");
		sqlSession.insert("help.helpdeskCheckDelete",map);
	}
	
	//수정 부분
	// 내용 변경
	public void update(String subject, String content, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("idx", idx);
		sqlSession.update("help.update",map);
	}
	// 이력추가
	public void helpCheckupdate(int Id_emp, String ip, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip", ip);
		map.put("board_idx", idx);
		sqlSession.insert("help.helpCheckupdate",map);
	}
	//파일부분
	
	public int getCountidxfile(int board_idx) {
		return sqlSession.selectOne("help.getCountidxfile",board_idx);
	}
	
	// 파일 history에 저장
	public void filehistorysave(int idx,String ofile, String sfile ) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_idx", idx);
		map.put("ofile", ofile);
		map.put("sfile", sfile);
		sqlSession.insert("help.filehistorysave",map);
	}
	// 파일 내역을 삭제!
	public void filedelete(int board_idx) {
		sqlSession.delete("help.filedelete",board_idx);
	}
	
	//댓글 목록
	public List<HelpcommentVO> helpcommentList(int helpdesk_idx) {
		return sqlSession.selectList("help.helpcommentList",helpdesk_idx);
	}
	
	//댓글 갯수
	public int helpcommentcount(int helpdesk_idx) {
		return sqlSession.selectOne("help.helpcommentcount",helpdesk_idx);
	}
	
	//댓글 추가
	public void helpcommentInsert(HelpcommentVO vo) {
		sqlSession.insert("help.helpcommentInsert",vo);
	}
	
	// 댓글 삭제
	public void helpcommentdelete(int idx) {
		sqlSession.update("help.helpcommentdelete",idx);
	}
	
	
	
	//=======================검색결과
	
	public List<HelpdeskVO> searchHDResult(int startNo, int endNo, String field, String text){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("field",field);
		map.put("text",text);		
		return sqlSession.selectList("help.searchHDResult",map);
	}
	
	public int searchHDCount(String field, String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("field", field);
		map.put("text", text);
		return sqlSession.selectOne("help.searchHDCount",map);
	}
}
