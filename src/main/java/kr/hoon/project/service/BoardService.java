package kr.hoon.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import kr.hoon.project.vo.anonymous.AnonymousCommentVO;
import kr.hoon.project.vo.anonymous.AnonymousDAO;
import kr.hoon.project.vo.anonymous.AnonymousPaging;
import kr.hoon.project.vo.anonymous.AnonymousVO;
import kr.hoon.project.vo.file.FileBucket;
import kr.hoon.project.vo.file.FileVO;
import kr.hoon.project.vo.file.MultiFileBucket;
import kr.hoon.project.vo.helpdesk.HelpDAO;
import kr.hoon.project.vo.helpdesk.HelpPaging;
import kr.hoon.project.vo.helpdesk.HelpcommentVO;
import kr.hoon.project.vo.helpdesk.HelpdeskVO;
import kr.hoon.project.vo.notice.NoticeDAO;
import kr.hoon.project.vo.notice.NoticePaging;
import kr.hoon.project.vo.notice.NoticeVO;

@Service
@EnableScheduling
public class BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private HelpDAO helpDAO;
	@Autowired
	private AnonymousDAO anonymousDAO;
	
	//리스트 가져오기
	public NoticePaging<NoticeVO> noticelist(int currentPage,int pageSize,int blockSize){
		NoticePaging<NoticeVO> paging =null;
		int totalCount = noticeDAO.getCount();
		paging = new NoticePaging<NoticeVO>(totalCount, currentPage, pageSize, blockSize);
		if(paging.getTotalCount()>0) {	
			System.out.println(paging.getStartNo()+","+paging.getEndNo());
			List<NoticeVO> list = noticeDAO.selectList(paging.getStartNo(), paging.getEndNo());
			paging.setLists(list);
		}
		return paging;
	}
	
	//공지사항 쿠키메서드
	public void noticeCookie( HttpServletRequest request,HttpServletResponse response ,int idx, int mode) {
		Cookie[] cookies = request.getCookies();
		boolean isRead = false;
		
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie : cookies){
				
				System.out.println(cookie.getName());
				if(("notice"+idx).equals(cookie.getName())){
					isRead = true;
					break;
				}
			}
		}
		if(isRead){
			// 저장되어 있으면 이미 글을 본적이 있다 --- 조회수 증가시키지 않는다
			mode = 0;		
		}else{
			// 저장되어있지 않으면 조회수를 증가시키고 글번호를 쿠키로 저장해준다.
			mode = 1;
			Cookie cookie = new Cookie("notice" + idx, "notice" + idx);
			cookie.setMaxAge(7*60*60*24); // 7일동안 저장
			response.addCookie(cookie);
		}
		
		if(mode==1) {
			noticeDAO.hitupdate(idx);
		}
	}
	
	//view보이기
	public NoticeVO noticeView(int idx) {
		NoticeVO vo  = noticeDAO.selectbyidx(idx);

		return vo;
	}
	// view에 보이기
	public FileVO noticeDownList(int idx) {
		return noticeDAO.noticefileload(idx);
	}
	
	// 공지사항 글저장, 파일저장, 체크에 등록
	public void noticeInsert(NoticeVO vo , MultiFileBucket multiFileBucket, HttpServletRequest request) {
		noticeDAO.insert(vo);
		int board_idx = noticeDAO.maxgetIdx();
		noticeDAO.noticeCheckInsert(vo.getId_emp(), request.getRemoteAddr(),board_idx);
		if(multiFileBucket!=null) {
			for(FileBucket bucket : multiFileBucket.getFiles()){
				if(multiFileBucket.getFiles()!=null) {
					FileVO dto = new FileVO();
					String oriFile = bucket.getFile().getOriginalFilename();

					// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
					String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
					System.out.println("realPath:" + request.getRealPath("/upload"));
					String saveFile = request.getRealPath("/upload/") + makeFileName;
					if(oriFile!=null && oriFile.trim().length()>0) {
						try {
							FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
						} catch (IOException e) {
							System.out.println("이름에서 에러!");
						}
						dto.setBoard_idx(board_idx);
						dto.setOfile(oriFile);
						dto.setSfile(makeFileName);
						noticeDAO.noticeFileinsert(dto);
					}
				}
			}
		}else {
			;
			}	
	}
	// IDX 최대값 구하기 -> 글번호로 바로가기위해 필요
	public int NoticemaxgetIdx() {
		return noticeDAO.maxgetIdx(); 
	}
	
	
	// 업무도움 글 수정, 파일 수정, 체크에 등록
	public void noticeUpdate(
								int idx, 
								MultiFileBucket multiFileBucket,
								String beforefile,
								String subject, 
								String content,
								int Id_emp, 
								HttpServletRequest request,
								String ofile, 
								String sfile) {
		
		noticeDAO.noticeupdate(subject, content, idx);
		noticeDAO.noticeCheckupdate(Id_emp, request.getRemoteAddr(), idx);
		int count = noticeDAO.getCountidxfile(idx);
		System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("beforefile"+ beforefile);
		System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		if(count ==1) {
			if(multiFileBucket.getFiles().get(0).getFile().getOriginalFilename().trim().length()>0) {
				//이력추가
				noticeDAO.filehistorysave(idx, ofile, sfile);
				//삭제
				noticeDAO.filedelete(idx);
				for(FileBucket bucket : multiFileBucket.getFiles()){
					if(multiFileBucket.getFiles()!=null) {
						FileVO dto = new FileVO();
						String oriFile = bucket.getFile().getOriginalFilename();
						System.out.println("realPath:" + request.getRealPath("/upload"));

						// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
						String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
						String saveFile = request.getRealPath("/upload/") + makeFileName;
						if(oriFile!=null && oriFile.trim().length()>0) {
							try {
								FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
							} catch (IOException e) {
								System.out.println("이름에서 에러!");
							}
							dto.setBoard_idx(idx);
							dto.setOfile(oriFile);
							dto.setSfile(makeFileName);
							noticeDAO.noticeFileinsert(dto);
						}
					}
				}				
			}else {

				System.out.println(beforefile.trim().length());
				if( beforefile.trim().length()==0) {
					//이력추가
					noticeDAO.filehistorysave(idx, ofile, sfile);
					//삭제
					noticeDAO.filedelete(idx);
				}else {
					;
				}
			}
		}else {
			if(multiFileBucket.getFiles()!=null) {
				for(FileBucket bucket : multiFileBucket.getFiles()){
					if(multiFileBucket.getFiles().get(0).getFile().getOriginalFilename().trim().length()>0) {
						FileVO dto = new FileVO();
						String oriFile = bucket.getFile().getOriginalFilename();
						System.out.println("realPath:" + request.getRealPath("/upload"));

						// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
						String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
						String saveFile = request.getRealPath("/upload/") + makeFileName;
						if(oriFile!=null && oriFile.trim().length()>0) {
							try {
								FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
							} catch (IOException e) {
								System.out.println("이름에서 에러!");
							}
							dto.setBoard_idx(idx);
							dto.setOfile(oriFile);
							dto.setSfile(makeFileName);
							noticeDAO.noticeFileinsert(dto);
						}
					}
				}
			}else {
				;
			}			
		}
	}
	
	// 다운받기
	public void down(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("/upload/");
		String oFile = request.getParameter("o");
		String sFile = request.getParameter("s");
		

	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false; // 존재하지않는 파일일경우 패스
	    String client = "";
	    try{
	        // 파일을 읽어 스트림에 담기
	        try{
	            file = new File(path, sFile);
	            System.out.println(file.getPath());
	            System.out.println(file.getAbsolutePath());
	            System.out.println(file.length());
	            in = new FileInputStream(file);
	            System.out.println(in.toString().length());
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }
			// 브라우져 종류         
	        client = request.getHeader("User-Agent");

	        // 파일 다운로드 헤더 지정
	        response.reset();
	        response.setContentType("application/octet-stream"); // 핸제 데이터가 스트림이다라고 알려준다.
	        // response.setHeader("Content-Description", "JSP Generated Data");
	        System.out.println(skip);
	        if(!skip){ // 파일이 존재 한다면
	            // 한글 파일명 처리
	            if(client.indexOf("Trident")==-1){
	            	oFile = new String(oFile.getBytes("utf-8"),"iso-8859-1");
	            }else{
	            	oFile = URLEncoder.encode(oFile, "UTF-8" ).replaceAll("\\+","%20" );
	            }
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + oFile + "\"");
	            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            response.setHeader ("Content-Length", ""+file.length() );
				
	            // 출력스트림 얻기
				// getOutputStream() has already been called for this response - error!!!
				// JSP에서는 SERVLET으로 변환될 때 내부적으로 out 객체가 자동으로 생성하기 때문에 
				// out객체를 만들면 충돌이 일어나서 저런 메시지가 뜨는 것이다.
				// 그래서 먼저 out를 초기화하고 생성하면 된다.
				//out.clear();
				//out = pageContext.pushBody();
	            
				os = response.getOutputStream();
				System.out.println(os.toString().length());
				// 복사
				byte b[] = new byte[(int)file.length()]; // 파일 크기만큼 배열선언
	            int leng = 0;
	            while( (leng = in.read(b)) > 0 ){ // 읽기
	                os.write(b,0,leng); // 쓰기
	                os.flush();
	            }
	        }
	        in.close();
	        os.close();
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	}
	
	public void noticeDelete(int idx,String ip,int Id_emp) {
		noticeDAO.noticedelete(idx);
		noticeDAO.noticeCheckDelete(Id_emp, ip, idx);
	}
	
	public NoticeVO noticeselectbyidx(int idx) {
		return noticeDAO.selectbyidx(idx);
	}
	
	
	// 검색용!!!
	public NoticePaging<NoticeVO> searchNoticeResult(int currentPage,int pageSize,int blockSize,String field, String text){
		NoticePaging<NoticeVO> paging =null;
		int totalCount = noticeDAO.searchNoticeCount(field, text);
		paging = new NoticePaging<NoticeVO>(totalCount, currentPage, pageSize, blockSize);

		if(paging.getTotalCount()>0) {	
			List<NoticeVO> list = noticeDAO.searchNoticeResult(paging.getStartNo(), paging.getEndNo(), field, text);
			paging.setLists(list);
		}
		return paging;
	}
	
	// 자동삭제
	
	@Scheduled(cron="0/5 * * * * *")
	public void removeNT() {
		try {
			
			noticeDAO.removeNT();
			System.out.println("삭제완료" );
		}catch(Exception e) {
			System.out.println("Error발생");
		}
		
	}
	
	
	// =============================================================================
	// 여기서부터 업무도움
	// =============================================================================
	
	//업무도움리스트 가져오기
	public HelpPaging<HelpdeskVO> helplist(int currentPage,int pageSize,int blockSize){
		HelpPaging<HelpdeskVO> paging =null;
		int totalCount = helpDAO.helpgetCountTotal();
		paging = new HelpPaging<HelpdeskVO>(totalCount, currentPage, pageSize, blockSize);
		if(paging.getTotalCount()>0) {	
			List<HelpdeskVO> list = helpDAO.HelpselectList(paging.getStartNo(), paging.getEndNo());
			if(list !=null && list.size()>0) {
				for(HelpdeskVO vo:list) {
					int commentCount= helpDAO.helpcommentcount(vo.getIdx());
					vo.setCommentcount(commentCount);
				}
			}
			paging.setLists(list);
		}
		return paging;
	}
	
	// 검색용!!!
	public HelpPaging<HelpdeskVO> searchHDResult(int currentPage,int pageSize,int blockSize,String field, String text){
		HelpPaging<HelpdeskVO> paging =null;
		int totalCount = helpDAO.searchHDCount(field, text);
		paging = new HelpPaging<HelpdeskVO>(totalCount, currentPage, pageSize, blockSize);
		if(paging.getTotalCount()>0) {	
			List<HelpdeskVO> list = helpDAO.searchHDResult(paging.getStartNo(), paging.getEndNo(), field, text);
			paging.setLists(list);
		}
		return paging;
	}
	
	
	
	// 업무도움 글저장, 파일저장, 체크에 등록
	public void helpdeskInsert(HelpdeskVO vo , MultiFileBucket multiFileBucket, HttpServletRequest request) {

		helpDAO.insertHelpDesk(vo);
		int board_idx = helpDAO.helpdeskmaxgetIdx();
		helpDAO.helpCheckInsert(vo.getId_emp(), request.getRemoteAddr(),board_idx);
		if(multiFileBucket!=null) {
			for(FileBucket bucket : multiFileBucket.getFiles()){
				if(multiFileBucket.getFiles()!=null) {
					FileVO dto = new FileVO();
					String oriFile = bucket.getFile().getOriginalFilename();
					System.out.println("realPath:" + request.getRealPath("/upload"));

					// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
					String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
					String saveFile = request.getRealPath("/upload/") + makeFileName;
					if(oriFile!=null && oriFile.trim().length()>0) {
						try {
							FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
						} catch (IOException e) {
							System.out.println("이름에서 에러!");
						}
						dto.setBoard_idx(board_idx);
						dto.setOfile(oriFile);
						dto.setSfile(makeFileName);
						helpDAO.helpFileinsert(dto);
					}
				}
			}
		}else {
			;
			}	
	}
	
	
	// IDX 최대값 구하기 -> 글번호로 바로가기위해 필요
	public int HelpdeskmaxgetIdx() {
		return helpDAO.helpdeskmaxgetIdx(); 
	}
	
	// 업무도움 글 수정, 파일 수정, 체크에 등록
	public void helpdeskUpdate(
								int idx, 
								MultiFileBucket multiFileBucket,
								String beforefile,
								String subject, 
								String content,
								int Id_emp, 
								HttpServletRequest request,
								String ofile, 
								String sfile) {
		helpDAO.update(subject, content, idx);
		helpDAO.helpCheckupdate(Id_emp, request.getRemoteAddr(), idx);
		
		System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("beforefile"+ beforefile);
		System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		
		
		
		int count = helpDAO.getCountidxfile(idx);
		
	
		if(count ==1) {
			
			/*
			 * 
			List<FileBucket> list = multiFileBucket.getFiles();
			System.out.println(list.size()+"개~~!!!!");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(list.size()>0) {
				for(FileBucket bucket : list) {
					System.out.println(bucket);
					if(bucket.getFile()!=null)
						System.out.println("["+bucket.getFile().getOriginalFilename().trim()+"]");
				}
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			 */
			if(multiFileBucket.getFiles().get(0).getFile().getOriginalFilename().trim().length()>0 ) {
				System.out.println("업로드한 파일이 존재하는가? 이름은 무엇인가?"+multiFileBucket.getFiles());
				//이력추가
				helpDAO.filehistorysave(idx, ofile, sfile);
				//삭제
				helpDAO.filedelete(idx);
				for(FileBucket bucket : multiFileBucket.getFiles()){
					if(multiFileBucket.getFiles()!=null) {
						FileVO dto = new FileVO();
						String oriFile = bucket.getFile().getOriginalFilename();
						System.out.println("realPath:" + request.getRealPath("/upload"));

						// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
						String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
						String saveFile = request.getRealPath("/upload/") + makeFileName;
						if(oriFile!=null && oriFile.trim().length()>0) {
							try {
								FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
							} catch (IOException e) {
								System.out.println("이름에서 에러!");
							}
							dto.setBoard_idx(idx);
							dto.setOfile(oriFile);
							dto.setSfile(makeFileName);
							helpDAO.helpFileinsert(dto);
						}
					}
				}				
			}else {
				System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println("beforefile"+ beforefile);
				System.out.println("~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				if(beforefile.trim().length()==0) { // 이전파일을 삭제했다는 의미!
					//이력추가
					helpDAO.filehistorysave(idx, ofile, sfile);
					//삭제
					helpDAO.filedelete(idx);
				}else {
					;
				}
			}
		}else { //해당 IDX의 파일이 존재하지 않았다면
			if(multiFileBucket.getFiles().get(0).getFile().getOriginalFilename().trim().length()>0) {
				for(FileBucket bucket : multiFileBucket.getFiles()){
					if(multiFileBucket.getFiles()!=null) {
						FileVO dto = new FileVO();
						String oriFile = bucket.getFile().getOriginalFilename();
						System.out.println("realPath:" + request.getRealPath("/upload"));

						// 파일 형식을 비교하여 원하는 데이터만 업로드 가능하게 할 수 있다.
						String makeFileName = System.nanoTime()+"_"+new Random().nextInt(1000);
						String saveFile = request.getRealPath("/upload/") + makeFileName;
						if(oriFile!=null && oriFile.trim().length()>0) {
							try {
								FileCopyUtils.copy(bucket.getFile().getBytes(), new File(saveFile));
							} catch (IOException e) {
								System.out.println("이름에서 에러!");
							}
							dto.setBoard_idx(idx);
							dto.setOfile(oriFile);
							dto.setSfile(makeFileName);
							helpDAO.helpFileinsert(dto);
						}
					}
				}
			}else {
				;
			}			
		}
	}
	
	
	
	//업무도움방 쿠키메서드
	public void helpdeskCookie( HttpServletRequest request,HttpServletResponse response ,int idx, int mode) {
		Cookie[] cookies = request.getCookies();
		boolean isRead = false;
		
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie : cookies){
				
				System.out.println(cookie.getName());
				if(("helpdesk"+idx).equals(cookie.getName())){
					isRead = true;
					break;
				}
			}
		}
		if(isRead){
			// 저장되어 있으면 이미 글을 본적이 있다 --- 조회수 증가시키지 않는다
			mode = 0;		
		}else{
			// 저장되어있지 않으면 조회수를 증가시키고 글번호를 쿠키로 저장해준다.
			mode = 1;
			Cookie cookie = new Cookie("helpdesk" + idx, "helpdesk" + idx);
			cookie.setMaxAge(7*60*60*24); // 7일동안 저장
			response.addCookie(cookie);
		}
		if(mode==1) {
			helpDAO.helphitupdate(idx);
		}
	}
	
	//view보이기
	public HelpdeskVO helpdeskView(int idx) {
		HelpdeskVO vo  = helpDAO.helpselectbyidx(idx);
		List<HelpcommentVO> list = helpDAO.helpcommentList(idx);
		vo.setCommentlist(list);
		vo.setCommentcount(list.size());
		return vo;
	}
	// view에 파일 보이기
	public FileVO helpdeskDownList(int idx) {
		return helpDAO.helpfileload(idx);
	}	
	
	public void helpdeskDelete(int idx,String ip,int Id_emp) {
		helpDAO.helpdeskdelete(idx);
		helpDAO.helpdeskCheckDelete(Id_emp, ip, idx);
	}
	
	// 댓글 추가
	public void helpcommentInsertOk(HelpcommentVO vo) {
		helpDAO.helpcommentInsert(vo);
	}
	
	// 댓글 삭제
	public void helpcommentdelete(int idx) {
		helpDAO.helpcommentdelete(idx);
	}
	

//======================================================================================
// 여기서부터는 익명 게시판!
//======================================================================================

// 익명게시판 리스트 가져오기!
	public AnonymousPaging<AnonymousVO> anonymouslist(int currentPage,int pageSize,int blockSize){
		AnonymousPaging<AnonymousVO> paging = null;
		int totalCount = anonymousDAO.AnogetCount();
		paging =  new AnonymousPaging<AnonymousVO>(totalCount, currentPage, pageSize, blockSize);
		if(paging.getTotalCount() > 0) {
			List<AnonymousVO> list = anonymousDAO.AnoselectList(paging.getStartNo(), paging.getEndNo());
			if(list !=null && list.size()>0) {
				
				for(AnonymousVO vo :list) {
					int commentCount = anonymousDAO.commentCount(vo.getIdx());
					vo.setCommentCount(commentCount);
				}
			}
			paging.setLists(list);
		}
		return paging;		
	}

	
	// 검색용!!!
	public AnonymousPaging<AnonymousVO> searchAResult(int currentPage,int pageSize,int blockSize,String field, String text){
		AnonymousPaging<AnonymousVO> paging =null;
		int totalCount = anonymousDAO.searchACount(field, text);
		paging = new AnonymousPaging<AnonymousVO>(totalCount, currentPage, pageSize, blockSize);
		if(paging.getTotalCount()>0) {	
			List<AnonymousVO> list = anonymousDAO.searchAResult(paging.getStartNo(), paging.getEndNo(), field, text);
			paging.setLists(list);
		}
		return paging;
	}
	
	
	//익명게시판 쿠키메서드
	public void AnonymousCookie( HttpServletRequest request,HttpServletResponse response ,int idx, int mode) {
		Cookie[] cookies = request.getCookies();
		boolean isRead = false;
		
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie : cookies){
				
				System.out.println(cookie.getName());
				if(("Anonymous"+idx).equals(cookie.getName())){
					isRead = true;
					break;
				}
			}
		}
		if(isRead){
			// 저장되어 있으면 이미 글을 본적이 있다 --- 조회수 증가시키지 않는다
			mode = 0;		
		}else{
			// 저장되어있지 않으면 조회수를 증가시키고 글번호를 쿠키로 저장해준다.
			mode = 1;
			Cookie cookie = new Cookie("Anonymous" + idx, "Anonymous" + idx);
			cookie.setMaxAge(7*60*60*24); // 7일동안 저장
			response.addCookie(cookie);
		}
		if(mode==1) {
			anonymousDAO.anohitupdate(idx);
		}
	}
	
	//view보이기
	public AnonymousVO anonymousView(int idx) {
		AnonymousVO vo  = anonymousDAO.Anoselectbyidx(idx);
		List<AnonymousCommentVO> list = anonymousDAO.commentList(idx);
		vo.setCommentList(list);;
		vo.setCommentCount(list.size());
		return vo;
	}
	
	// 내용추가 : 데이터에 추가/ BoardCehck에 추가 되었음을 추가해준다.
	public void anonymousinsert(AnonymousVO vo, int Id_emp, HttpServletRequest request) {
		anonymousDAO.insert(vo);
		int idx = anonymousDAO.maxidx();
		anonymousDAO.CheckInsert(Id_emp, request.getRemoteAddr(), idx);
	}
	// 내용삭제  : if(비밀번호가 일치한다면(idx 활용도 중요!))데이터에 type을 2로 변경/ BoardCehck에 삭제 되었음을 추가해준다. 
	public void anonymousdelete( int idx, int Id_emp ,HttpServletRequest request) {
		
			anonymousDAO.delete(idx);
			anonymousDAO.CheckDelete(Id_emp, request.getRemoteAddr(), idx);
		
	}
	// 비밀번호 체크!
	public int passwordCheck (String password , int idx) {
		String log_password = anonymousDAO.getPassword(idx);
		if(password.equals(log_password)) {
			return 1;
		}else {
			return 2;
		}
	}
	
	// 내용수정  : if(비밀번호가 일치한다면(idx 활용도 중요!))데이터에 내용, 제목을 변경/ BoardCehck에 수정 되었음을 추가해준다.
	public void anonymousupdate(int idx, int Id_emp, String subject,String content ,HttpServletRequest request) {
			anonymousDAO.update(subject, content, idx);
			anonymousDAO.CheckUpdate(Id_emp, request.getRemoteAddr(), idx);
	}

	// 댓글 추가 : 댓글의 데이터에 추가 해준다.
	public void anonymouscommentinsert(AnonymousCommentVO vo,int Id_emp,HttpServletRequest request) {
		anonymousDAO.commentInsert(vo);
		anonymousDAO.CheckCommentInsert(Id_emp, request.getRemoteAddr(), vo.getAnonymous_idx());
	}
	// 댓글 삭제 : if(비밀번호가 일치한다면(idx 활용도 중요!)댓글의 타입을 2로 변경해준다.
	
	public int commentgetpassword(String password, int idx) {
		String realpw = anonymousDAO.commentgetpassword(idx);
		
		if(!password.equals(realpw)) {
			
			return 1;
		}else {
			return 2;
		}
		
	}
	
	public void commentdelete(
				int idx,
				String password,
				int Id_emp,
				HttpServletRequest request
			) {
		anonymousDAO.commentdelete(idx, password);
		anonymousDAO.CheckCommentDelete(Id_emp, request.getRemoteAddr(), idx);
	}
	
}