package kr.hoon.project;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ibatis.common.logging.Log;

import kr.hoon.project.service.BoardService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.anonymous.AnonymousCommentVO;
import kr.hoon.project.vo.anonymous.AnonymousPaging;
import kr.hoon.project.vo.anonymous.AnonymousVO;
import kr.hoon.project.vo.file.FileVO;
import kr.hoon.project.vo.file.MultiFileBucket;
import kr.hoon.project.vo.helpdesk.HelpPaging;
import kr.hoon.project.vo.helpdesk.HelpcommentVO;
import kr.hoon.project.vo.helpdesk.HelpdeskVO;
import kr.hoon.project.vo.notice.NoticePaging;
import kr.hoon.project.vo.notice.NoticeVO;
import lombok.extern.log4j.Log4j;

@Controller
public class BoardCotroller {
	@Autowired
	private BoardService boardService;
	@Autowired
	private InfoService infoservice;
	
	
	@RequestMapping(value="/notice")
	public String notice(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) String field,
			@RequestParam(required=false) String text,
			Model model,
			HttpSession session,
			HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m = (Map<String, Object>)map.get("map");
			p = (Integer) m.get("p");
			s = (Integer) m.get("s");
			b = (Integer) m.get("b");
		}
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		if(p!=null && p>0)  currentPage=p;
		if(s!=null && s>0)  pageSize=s;
		if(b!=null && b>0)  blockSize=b;
		
		
		NoticePaging<NoticeVO> paging = null;
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		
		if(text == null || text.trim().length()==0) {			
			paging = boardService.noticelist(currentPage, pageSize, blockSize);
		}else {
			paging = boardService.searchNoticeResult(currentPage, pageSize, blockSize, field, text);			
		}
		
		model.addAttribute("field",field);
		model.addAttribute("paging",paging);
		model.addAttribute("list",list);
		return "notice";
	}
	
	
	
	@RequestMapping(value="/noticeview")
	public String viewnotice(			
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer idx,
			@RequestParam(required=false) Integer m,			
			Model model,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			){
		// 컨트롤러에서 포스트 전송값 받기
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m2 = (Map<String, Object>) map.get("map");
			p = (Integer) m2.get("p");
			b = (Integer) m2.get("b");
			s = (Integer) m2.get("s");
			idx = (Integer) m2.get("idx");
			m = (Integer) m2.get("m");
		}		
		// 기본값
		int index = 0;
		int mode = 0; 
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		// 값이 넘어왔을 경우에만 값 변경
		if(idx!=null) index = idx;
		if(m!=null) mode = m;
		if(p!=null) currentPage = p;
		if(s!=null) pageSize= s;
		if(b!=null) blockSize = b;
		//===========================================================
		// mode설정
		boardService.noticeCookie(request, response, idx, mode);
		//뷰 보기!
		NoticeVO vo = boardService.noticeView(index);
		if(vo==null) {
			Map<String, Object> map2= new HashMap<String, Object>();
			map2.put("p", currentPage);
			map2.put("b", blockSize);
			redirectAttributes.addFlashAttribute("map", map2);
			redirectAttributes.addFlashAttribute("s",pageSize);
			return "redirect:/notice";
		}
		InfoVO vo2 = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
		model.addAttribute("list",list);
		
		//Ip 넣기
		String ip = request.getRemoteAddr();
		model.addAttribute("ip",ip);
		// 파일 목록 가져오기
		FileVO flist = boardService.noticeDownList(idx);
		model.addAttribute("flist",flist);
		
		// 해당 글번호에 있는 거 가지고 넘어가기!
		model.addAttribute("vo",vo);
		// 페이지 이동에 대비하여 저장
		model.addAttribute("p", currentPage);
		model.addAttribute("s", pageSize);
		model.addAttribute("b", blockSize);
		return "noticeview";
	}
	@RequestMapping(value="/noticewrite", method= RequestMethod.POST)
	public String write(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			HttpSession session,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		return "noticewrite";
	}
	@RequestMapping(value="/noticewriteOk" ,method=RequestMethod.POST)
	public String noticewriteOk(@ModelAttribute NoticeVO vo,
								@ModelAttribute MultiFileBucket multiFileBucket,
								@RequestParam(required=false) Integer p,
								@RequestParam(required=false) Integer s,
								@RequestParam(required=false) Integer b,
								HttpServletRequest request,
								RedirectAttributes redirectAttributes								
								) {
		boardService.noticeInsert(vo, multiFileBucket, request);
	
		int idx = boardService.NoticemaxgetIdx();
		Map<String,Object> map = new HashMap<String, Object>();

		map.put("p", p);
		map.put("b", b);
		map.put("s", s);
		map.put("idx",idx);
		map.put("m", 0);
		redirectAttributes.addFlashAttribute("map",map);
		
		return "redirect:/noticeview";
	}
	@RequestMapping(value="/noticedown")
	public void noticeDown(HttpServletRequest request, HttpServletResponse response) {
		boardService.down(request, response);
	}
	
	
	@RequestMapping(value="/noticeupdate")
	public String noticeupdate(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer idx,
			@RequestParam(required=false) Integer m,
			HttpSession session,
			Model model) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		// 수정하기전 내용
		NoticeVO dto =  boardService.noticeView(idx);  //boardService.helpdeskView(idx);
		model.addAttribute("vo",dto);
		// 수정하기전 파일내역
		FileVO flist = boardService.noticeDownList(idx);
		model.addAttribute("flist",flist);
		
		model.addAttribute("idx", idx);
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		model.addAttribute("m",m);
		
		return "noticeupdate";
	}
	
	
	@RequestMapping(value="/noticeupdateOk" ,method=RequestMethod.POST)
	public String noticeupdateOk(
									@ModelAttribute MultiFileBucket multiFileBucket, 
									HttpServletRequest request,
									@RequestParam int idx,
									@RequestParam(required = false) String beforefile,
									@RequestParam String subject,
									@RequestParam String content,
									@RequestParam int Id_emp,
									@RequestParam int m,
									@RequestParam(required=false) Integer p,
									@RequestParam(required=false) Integer s,
									@RequestParam(required=false) Integer b,
									@RequestParam(required = false) String ofile,
									@RequestParam(required = false) String sfile,
									RedirectAttributes redirectAttributes
			) {
		boardService.noticeUpdate(idx, multiFileBucket, beforefile, subject, content, Id_emp, request, ofile, sfile);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p", p);
		map.put("b", b);
		map.put("s", s);
		map.put("idx",idx);
		map.put("m", 0);
		redirectAttributes.addFlashAttribute("map",map);
		
		return "redirect:/noticeview";
	}

	@RequestMapping(value="noticedeleteOk")
	public String noticedeleteOk(@RequestParam int idx, @RequestParam int Id_emp, @RequestParam String ip) {
		
		boardService.noticeDelete(idx, ip, Id_emp);
		
		return "redirect:/notice";
	}
	//===========================================================
	//여기서 부터는 업무도움방
	//===========================================================
	
	
	@RequestMapping(value="/helpdesk")
	public String helpdesk(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) String field,
			@RequestParam(required=false) String text,
			Model model,
			HttpSession session,
			HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m = (Map<String, Object>)map.get("map");
			p = (Integer) m.get("p");
			s = (Integer) m.get("s");
			b = (Integer) m.get("b");
		}
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		if(p!=null && p>0)  currentPage=p;
		if(s!=null && s>0)  pageSize=s;
		if(b!=null && b>0)  blockSize=b;
		
		
		HelpPaging<HelpdeskVO> paging = null;
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());;
		
		if(text == null || text.trim().length()==0) {			
			paging = boardService.helplist(currentPage, pageSize, blockSize);
		}else {
			paging = boardService.searchHDResult(currentPage, pageSize, blockSize, field, text);			
		}
		
		model.addAttribute("field",field);
		model.addAttribute("paging",paging);
		model.addAttribute("list",list);
		return "helpdesk";
	}	
	@RequestMapping(value="/helpdeskwrite", method= RequestMethod.POST)
	public String helpdeskwrite(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			HttpSession session,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		return "helpdeskwrite";
	}
	@RequestMapping(value="/helpdeskwriteOk" ,method=RequestMethod.POST)
	public String helpdeskewriteOk(
				@ModelAttribute HelpdeskVO vo ,
				@ModelAttribute MultiFileBucket multiFileBucket, 
				HttpServletRequest request,
				@RequestParam(required=false) Integer p,
				@RequestParam(required=false) Integer s,
				@RequestParam(required=false) Integer b,
				RedirectAttributes redirectAttributes								
				) {
		boardService.helpdeskInsert(vo, multiFileBucket, request);
		
		
		int idx = boardService.HelpdeskmaxgetIdx();
		Map<String,Object> map = new HashMap<String, Object>();

		map.put("p", p);
		map.put("b", b);
		map.put("s", s);
		map.put("idx",idx);
		map.put("m", 0);
		redirectAttributes.addFlashAttribute("map",map);

		
		
		return "redirect:/helpdeskview";
	}
	
	@RequestMapping(value="/helpdeskview")
	public String viewhelpdesk(			
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer idx,
			@RequestParam(required=false) Integer m,			
			Model model,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			){
		// 컨트롤러에서 포스트 전송값 받기
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m2 = (Map<String, Object>) map.get("map");
			p = (Integer) m2.get("p");
			b = (Integer) m2.get("b");
			s = (Integer) m2.get("s");
			idx = (Integer) m2.get("idx");
			m = (Integer) m2.get("m");
		}		
		// 기본값
		int index = 0;
		int mode = 0; 
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		// 값이 넘어왔을 경우에만 값 변경
		if(idx!=null) index = idx;
		if(m!=null) mode = m;
		if(p!=null) currentPage = p;
		if(s!=null) pageSize= s;
		if(b!=null) blockSize = b;
		//===========================================================
		// mode설정
		boardService.helpdeskCookie(request, response, idx, mode);
		//뷰 보기!
		HelpdeskVO vo = boardService.helpdeskView(idx);
		if(vo==null) {
			Map<String, Object> map2= new HashMap<String, Object>();
			map2.put("p", currentPage);
			map2.put("b", blockSize);
			redirectAttributes.addFlashAttribute("map", map2);
			redirectAttributes.addFlashAttribute("s",pageSize);
			return "redirect:/helpdesk";
		}
		InfoVO vo2 = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
		model.addAttribute("list",list);
		//Ip 넣기
		String ip = request.getRemoteAddr();
		model.addAttribute("ip",ip);
		// 파일 목록 가져오기
		FileVO flist = boardService.helpdeskDownList(idx);
		model.addAttribute("flist",flist);
		
		// 댓글 목록 가져오기
		
		
		// 해당 글번호에 있는 거 가지고 넘어가기!
		model.addAttribute("vo",vo);
		// 페이지 이동에 대비하여 저장
		model.addAttribute("p", currentPage);
		model.addAttribute("s", pageSize);
		model.addAttribute("b", blockSize);
		return "helpdeskview";
	}

	@RequestMapping(value="/helpdeskdown")
	public void helpdeskDown(HttpServletRequest request, HttpServletResponse response) {
		boardService.down(request, response);
	}
	
	@RequestMapping(value="/helpdeskupdate")
	public String helpdeskupdate(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer idx,
			
			HttpSession session,
			Model model) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		// 수정하기전 내용
		HelpdeskVO dto = boardService.helpdeskView(idx);
		model.addAttribute("vo",dto);
		// 수정하기전 파일내역
		FileVO flist = boardService.helpdeskDownList(idx);
		model.addAttribute("flist",flist);
		
		model.addAttribute("idx", idx);
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		
		return "helpdeskupdate";
	}
	
	
	@RequestMapping(value="/helpdeskupdateOk" ,method=RequestMethod.POST)
	public String helpdeskupdateOk(
									@ModelAttribute MultiFileBucket multiFileBucket, 
									HttpServletRequest request,
									@RequestParam int idx,
									@RequestParam(required = false) String beforefile,
									@RequestParam String subject,
									@RequestParam String content,
									@RequestParam int Id_emp,									
									@RequestParam(required=false) Integer p,
									@RequestParam(required=false) Integer s,
									@RequestParam(required=false) Integer b,
									@RequestParam(required = false) String ofile,
									@RequestParam(required = false) String sfile,
									RedirectAttributes redirectAttributes
			) {
		System.out.println();
		System.out.println( ofile + sfile) ;
		boardService.helpdeskUpdate(idx, multiFileBucket, beforefile, subject, content, Id_emp, request, ofile, sfile);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p", p);
		map.put("b", b);
		map.put("s", s);
		map.put("idx",idx);
		map.put("m", 0);
		redirectAttributes.addFlashAttribute("map",map);
		return "redirect:/helpdeskview";
	}
	
	
	
	@RequestMapping(value="/helpdeskdelete")
	public String helpdeskDelete(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam int idx,
			HttpSession session,
			HttpServletRequest request,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		HelpdeskVO dto = boardService.helpdeskView(idx);
		
		model.addAttribute("dto",dto);
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		System.out.println(idx);
		model.addAttribute("idx",idx);
		return "helpdeskdelete";
	}
	@RequestMapping(value="helpdeskdeleteOk")
	public String helpdeskdeleteOk(@RequestParam int idx, @RequestParam int Id_emp, @RequestParam String ip) {
		boardService.helpdeskDelete(idx, ip, Id_emp);
		return "redirect:/helpdesk";
	}
	
	// 댓글 저장!!
	@RequestMapping(value="/helpcommentInsertOk", method=RequestMethod.POST)
	public String commentPostOk(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer m,
			@ModelAttribute HelpcommentVO vo,
			Model model,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			) {
		// 저장
		boardService.helpcommentInsertOk(vo);
		// POST 전송
		Map<String, Integer> map = new HashMap<String,Integer>();
	    map.put("p", p);
	    map.put("b", b);
	    map.put("s", s);  
	    map.put("idx", vo.getHelpdesk_idx());
	    map.put("m", 0);
	    redirectAttributes.addFlashAttribute("map", map);
		return "redirect:/helpdeskview";
	}
	
	// 댓글 삭제
	@RequestMapping(value="helpcommentDeleteOk")
	public String helpcommentDeleteOk(
										@RequestParam int idx,
										@RequestParam(required=false) Integer p,
										@RequestParam(required=false) Integer s,
										@RequestParam(required=false) Integer b,
										@RequestParam(required=false) Integer c,
										@RequestParam(required=false) Integer m,
										@ModelAttribute HelpcommentVO vo,
										Model model,
										RedirectAttributes redirectAttributes	
			) {
		boardService.helpcommentdelete(idx);
		
		Map<String, Integer> map = new HashMap<String,Integer>();
	    map.put("p", p);
	    map.put("b", b);
	    map.put("s", s);    
	    map.put("idx", c);
	    map.put("m", 0);
	    redirectAttributes.addFlashAttribute("map", map);
		
		return "redirect:/helpdeskview";
	}
	
	//===================================================================================
	//여기서부터는 익명게시판
	//===================================================================================
	
	
	@RequestMapping(value="/anonymous")
	public String Anonymous(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) String field,
			@RequestParam(required=false) String text,
			Model model,
			HttpSession session,
			HttpServletRequest request) {
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m = (Map<String, Object>)map.get("map");
			p = (Integer) m.get("p");
			s = (Integer) m.get("s");
			b = (Integer) m.get("b");
		}
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		if(p!=null && p>0)  currentPage=p;
		if(s!=null && s>0)  pageSize=s;
		if(b!=null && b>0)  blockSize=b;
		AnonymousPaging<AnonymousVO> paging = null;
		InfoVO vo = (InfoVO) session.getAttribute("vo");		
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());

		if(text == null || text.trim().length()==0) {			
			paging = boardService.anonymouslist(currentPage, pageSize, blockSize);
		}else {
			paging = boardService.searchAResult(currentPage, pageSize, blockSize, field, text);			
		}
		
		model.addAttribute("field",field);
		model.addAttribute("paging",paging);
		model.addAttribute("list",list);

		return "anonymous";
	}	
	
	
	@RequestMapping(value="/anonymousview")
	public String anonymousview(			
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer idx,
			@RequestParam(required=false) Integer m,			
			Model model,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			){
		// 컨트롤러에서 포스트 전송값 받기
		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> m2 = (Map<String, Object>) map.get("map");
			p = (Integer) m2.get("p");
			b = (Integer) m2.get("b");
			s = (Integer) m2.get("s");
			idx = (Integer) m2.get("idx");
			m = (Integer) m2.get("m");
		}		
		// 기본값
		int index = 0;
		int mode = 0; 
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		// 값이 넘어왔을 경우에만 값 변경
		if(idx!=null) index = idx;
		if(m!=null) mode = m;
		if(p!=null) currentPage = p;
		if(s!=null) pageSize= s;
		if(b!=null) blockSize = b;
		//===========================================================
		// mode설정
		boardService.AnonymousCookie(request, response, idx, mode);
		//뷰 보기!
		AnonymousVO vo = boardService.anonymousView(idx);
		
		if(vo==null) {
			Map<String, Object> map2= new HashMap<String, Object>();
			map2.put("p", currentPage);
			map2.put("b", blockSize);
			redirectAttributes.addFlashAttribute("map", map2);
			redirectAttributes.addFlashAttribute("s",pageSize);
			return "redirect:/anonymous";
		}
		InfoVO vo2 = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
		model.addAttribute("list",list);		

		
		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));
		
		
		
		// 해당 글번호에 있는 거 가지고 넘어가기!
		model.addAttribute("vo",vo);
		// 페이지 이동에 대비하여 저장
		model.addAttribute("p", currentPage);
		model.addAttribute("s", pageSize);
		model.addAttribute("b", blockSize);
		return "anonymousview";
	}
	
	// 내용 추가
	@RequestMapping(value="/anonymouswrite", method= RequestMethod.POST)
	public String anonymouswrite(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			HttpSession session,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		return "anonymouswrite";
	}
	@RequestMapping(value="/anonymouswriteOk" ,method=RequestMethod.POST)
	public String anonymouswriteOk(@ModelAttribute AnonymousVO vo ,@RequestParam int Id_emp ,HttpServletRequest request) {
		boardService.anonymousinsert(vo, Id_emp, request);
		return "redirect:/anonymous";
	}

	 
	// 내용 삭제
	@RequestMapping(value="/anonymousdelete")
	public String anonymousDelete(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam int idx,
			HttpSession session,
			HttpServletRequest request,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		AnonymousVO dto = boardService.anonymousView(idx);
		
		
		String referer = "";

		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		
		
		
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));

		model.addAttribute("dto",dto);
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		model.addAttribute("idx",idx);
		return "anonymousdelete";
	}
	// 내용 삭제 ok
	@RequestMapping(value="anonymousdeleteOk")
	public String anonymousdeleteOk(@RequestParam String password, @RequestParam int idx, @RequestParam int Id_emp, HttpServletRequest request) {
		int result = boardService.passwordCheck(password, idx);
		
		if(result ==1 ) {
			boardService.anonymousdelete(idx, Id_emp, request);
			return "redirect:/anonymous";
		}else {
			return "redirect:/anonymousdelete?result="+result+"&idx="+idx;
		}
		
		
	}	

	 
	// 내용 수정
	@RequestMapping(value="/anonymousupdate")
	public String anonymousUpdate(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam int idx,
			HttpSession session,
			HttpServletRequest request,
			Model model
			) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		String referer = "";

		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		
		
		
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));
		AnonymousVO dto = boardService.anonymousView(idx);
		model.addAttribute("dto",dto);
		model.addAttribute("p", p);
		model.addAttribute("b", b);
		model.addAttribute("s", s);
		model.addAttribute("idx",idx);
		return "anonymousupdate";
	}
	// 내용 변경 ok
	@RequestMapping(value="anonymousupdateOk")
	public String anonymousupdateOk(@RequestParam String password, 
									@RequestParam int idx, 
									@RequestParam int Id_emp, 
									@RequestParam String subject, 
									@RequestParam String content, 
									HttpServletRequest request) {
		
		int result = boardService.passwordCheck(password, idx);
		
		if(result ==1 ) {
			boardService.anonymousupdate(idx, Id_emp, subject, content, request);
			return "redirect:/anonymousview";
		}else {
			return "redirect:/anonymousupdate?result="+result+"&idx="+idx;
		}
	}		

	// 댓글 추가
	@RequestMapping(value="/anonymouscommentInsertOk", method=RequestMethod.POST)
	public String anonymouscommentInsertOk(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer m,
			@RequestParam int Id_emp,
			@ModelAttribute AnonymousCommentVO vo,
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			) {
		// 저장
		boardService.anonymouscommentinsert(vo, Id_emp, request);
		// POST 전송
		Map<String, Integer> map = new HashMap<String,Integer>();
	    map.put("p", p);
	    map.put("b", b);
	    map.put("s", s);  
	    map.put("idx", vo.getAnonymous_idx());
	    map.put("m", 0);
	    redirectAttributes.addFlashAttribute("map", map);
		return "redirect:/anonymousview";
	}
	
	// 댓글 삭제
	@RequestMapping(value="/anonymousdelete", method=RequestMethod.POST)
	public String anonymousdelete(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer m,
			@RequestParam int idx,
			@RequestParam int anonymous_idx,
			@RequestParam int Id_emp,
			@RequestParam String password,
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes // 컨트롤러에서 POST전송하기
			) {
		
		int result = boardService.commentgetpassword(password, idx);

		
		
		// POST 전송
		Map<String, Integer> map = new HashMap<String,Integer>();
	    map.put("p", p);
	    map.put("b", b);
	    map.put("s", s);  
	    map.put("idx", anonymous_idx);
	    map.put("m", 0);
	    redirectAttributes.addFlashAttribute("map", map);
    
	    if(result ==2) {
			boardService.commentdelete(idx, password, Id_emp, request);
			return "redirect:/anonymousview";
		}else {
			return "redirect:/anonymousview?result="+result;
		}
	}

}
