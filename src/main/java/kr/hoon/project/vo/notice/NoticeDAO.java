package kr.hoon.project.vo.notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hoon.project.vo.file.FileVO;

@Repository
public class NoticeDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//테스트 오브 테스트
	public void testing () {
		sqlSession.insert("notice.testing");
	}
	
	
	// 리스트 보여주기
	public List<NoticeVO> selectList(int startNo, int endNo){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		return sqlSession.selectList("notice.selectList",map);
	}
	// 1개보여주기~!
	public NoticeVO selectbyuserid(int Id_emp){
		return sqlSession.selectOne("notice.selectbyuserid",Id_emp);
	}
	// 1개보여주기~!
	public NoticeVO selectbyidx(int idx){
		return sqlSession.selectOne("notice.selectbyidx",idx);
	}
	// 전체 개수~!
	public int getCount() {
		return sqlSession.selectOne("notice.getCount");
	}
	
	// 글쓰기 저장!
	public void insert(NoticeVO vo) {
		sqlSession.insert("notice.insert",vo);
	}
	// 조회수 증가
	public void hitupdate(int idx) {
		sqlSession.update("notice.hitupdate",idx);
	}
	
	// idx 최고값 가져오기
	public int maxgetIdx() {
		return sqlSession.selectOne("notice.maxgetIdx");
	}
	
	// 파일저장!
	public void noticeFileinsert(FileVO vo) {
		sqlSession.insert("notice.noticeFileinsert",vo);
	}
	
	// 게시글 체크에 등록
	public void noticeCheckInsert(int Id_emp, String ip, int board_idx) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip",ip);
		map.put("board_idx", board_idx+"");
		sqlSession.insert("notice.noticeCheckInsert",map);
	}
	
	//파일명부르기
	public FileVO noticefileload(int board_idx) {
		return sqlSession.selectOne("notice.fileload",board_idx);
	}
	
	//변경
	
	//내용변경
	public void noticeupdate(String subject, String content, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("content", content);
		map.put("idx", idx);
		sqlSession.update("notice.update",map);
	}
	
	//변경내역 저장
	public void noticeCheckupdate(int Id_emp, String ip, int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Id_emp", Id_emp);
		map.put("ip", ip);
		map.put("board_idx", idx);
		sqlSession.insert("notice.noticeCheckupdate",map);
	}
	
	//파일부분
	// 파일 개수 알아내기
	public int getCountidxfile(int board_idx) {
		return sqlSession.selectOne("notice.getCountidxfile",board_idx);
	}
	//파일 내역을 저장
	public void filehistorysave(int idx, String ofile ,String sfile) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("board_idx", idx);
		map.put("ofile", ofile);
		map.put("sfile", sfile);
		
		sqlSession.insert("notice.filehistorysave",map);
	}
	// 파일 원본 내역을 삭제
	public void filedelete(int board_idx) {
		sqlSession.delete("notice.filedelete",board_idx);
	}
	
	
	
	//삭제 -리스트에서
	public void noticedelete (int idx) {
		sqlSession.update("notice.noticedelete",idx);
	}
	//삭제 - 기록에 남기기
	public void noticeCheckDelete(int Id_emp, String ip,int board_idx) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Id_emp", Id_emp+"");
		map.put("ip", ip);
		map.put("board_idx", board_idx+"");
		sqlSession.insert("notice.noticeCheckDelete",map);
	}
	
	
	//=======================검색
	
	
	public List<NoticeVO> searchNoticeResult(int startNo, int endNo, String field, String text){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("field",field);
		map.put("text",text);		
		return sqlSession.selectList("notice.searchNoticeResult",map);
	}
	
	public int searchNoticeCount(String field, String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("field", field);
		map.put("text", text);
		return sqlSession.selectOne("notice.searchNoticeCount",map);
	}
	
	
	// ========== 자동삭제를 위한 DAO
	public void removeNT(){
		 sqlSession.delete("notice.removeNT");
	}
}
