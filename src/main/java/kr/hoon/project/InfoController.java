package kr.hoon.project;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.hoon.project.service.DevelopService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.hirestate.HirestateVO;
import kr.hoon.project.vo.logcheck.LogCheckVO;
import kr.hoon.project.vo.logcheck.Paging;
import kr.hoon.project.vo.promotionresult.HistoryVO;

@Controller
public class InfoController {

	@Autowired
	private InfoService infoService;
	
	@Autowired
	private DevelopService developService;
	

	
	@RequestMapping(value="/signIn")
	public String signIn(HttpServletRequest request, Model model,HttpSession session) {
		List<DepartVO> dlist = developService.selectAllDept();
		List<HirestateVO> hlist = developService.selectAllHirestate();
		
		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		model.addAttribute("prevUrl", referer);
		
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		model.addAttribute("dlist",dlist);
		model.addAttribute("hlist",hlist);
		model.addAttribute("result", request.getParameter("result"));

		if(list.getDepart_d().equals("인사")) {
			return "signIn";
		}else {
			return "redirect:/index";
		}
	}
	
	// 아이디의 저장 개수 : 가입폼에서 Ajax로 호출할 것이다.0이면 사용가능, 0이 아니면 사용중!!!!
	@RequestMapping(value="/useridCount", produces={"tex/html;charset=utf-8"})
	@ResponseBody
	public String useridCount(@RequestParam int Id_emp){
			return infoService.getUseridCount(Id_emp)+"";		
	}

	
	// 회원 정보 저장하기
	@RequestMapping(value="/signInOk")
	public String signInOk(@ModelAttribute("InfoVO") InfoVO vo) {
		// 이곳에서 vo의 유효성을 다시 한번 확인해준다. 
		int id = vo.getId_emp();
		int result = infoService.getUseridCount(id);
		if(result==0) {			
			if(vo != null) {
				infoService.insert(vo); // 저장
			}
			return "redirect:/index";
		}else {
			return "redirect:/signIn?result="+result;
		}
	}
	 
	@RequestMapping(value="/loginOk")
	public String loginOk(@RequestParam int Id_emp, @RequestParam String password, @RequestParam String url,
			HttpSession session,HttpServletRequest request) {
		
		
		int result = infoService.loginCheck(Id_emp, password);
		String ip = request.getRemoteAddr();
		// 1 : 아이디 존재하지 않는다.
		// 2 : 아이디는 있는데 비번이 틀리다.
		// 3 : 휴직 or 퇴사한 사원
		// 4 : 아이디 비번 모두 일치
		
		if(result==4) {
			InfoVO vo = infoService.selectByUserid(Id_emp);
			infoService.logInR(Id_emp, ip);
			session.setAttribute("vo", vo);
			
			return "redirect:/index";
		}else {
			return "redirect:/login?result=" +result;
		}
	}
	
	@RequestMapping(value="/index")
	public String home(HttpSession session, Model model) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		String temp =vo.getTemp1();
		
		//String duty = infoService.
		
		model.addAttribute("temp",temp);

		return "index";
	}
	
	@RequestMapping(value="/updateInfo")
	public String updateInf(HttpServletRequest request ,Model model, HttpSession session) {
		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		model.addAttribute("prevUrl", referer);

		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		
		String dep = vo.getLev_l();
		String job = vo.getDepart_d();
		
		String temp =vo.getTemp1();
		
		//String duty = infoService.
		
		model.addAttribute("list",list);
		model.addAttribute("temp",temp);
		
		model.addAttribute("job",job);
		model.addAttribute("dep",dep);
		model.addAttribute("result", request.getParameter("result"));

		return "updateInfo";
	}
	//String first_phone,String mid_phone,String last_phone, String email, String zipcode, String addr1, String addr2, String bank, String account
	@RequestMapping(value="/updateInfoOk", method=RequestMethod.POST)
	public String updateInfoOk(@RequestParam String first_phone,
								@RequestParam String mid_phone,
								@RequestParam String last_phone,
								@RequestParam String email,
								@RequestParam String zipcode,
								@RequestParam String addr1,
								@RequestParam String addr2,
								@RequestParam String bank,
								@RequestParam String account,
								@RequestParam int Id_emp,
								@RequestParam String password,
								@RequestParam Date birth
								) {
			
		 infoService.update(first_phone, mid_phone, last_phone, email, zipcode, addr1, addr2, bank, account, Id_emp, birth);
		return "redirect:/index";
		
	}
	@RequestMapping(value="/logOut")
	public String logOut(HttpSession session, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		int Id_emp = vo.getId_emp();
		infoService.logoutR(Id_emp, ip);
		session.invalidate();
		
		return "redirect:/login";
	}
	
	/*
	public String updateInfoOk(@RequestParam){
		
	}
	 */
	@RequestMapping(value="/logCheck")
	// 1. 리스트
	public String listlog(
			@RequestParam(required=false) Integer p,
			@RequestParam(required=false) Integer s,
			@RequestParam(required=false) Integer b,
			@RequestParam(required=false) Integer i,
			Model model,
			HttpSession session
			) {
		
		int currentPage = 1;
		int pageSize = 10;
		int blockSize = 10;
		Integer id_emp =null;
		if(p!=null && p>0)  currentPage=p;
		if(s!=null && s>0)  pageSize=s;
		if(b!=null && b>0)  blockSize=b;
		
		if(i !=null && i.toString().trim().length()>0) {
			id_emp=i;
			model.addAttribute("ie",id_emp);
		}
		
		
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);

		
			Paging<LogCheckVO> paging = infoService.selectListLog(currentPage, pageSize, blockSize, id_emp);
			model.addAttribute("paging", paging);
	
		if(list.getDepart_d().equals("개발")) {
			return "logcheck";
		}else {
			return "redirect:/index";
		}
	}

	@RequestMapping(value="/excellogCheck2")
	public void excelDown(HttpServletResponse response, @RequestParam(required = false) Integer Id_emp){
		infoService.excelogCheck(response, Id_emp);
	}

	
	
	
	
	
	// 임직원 조회 뷰
	@RequestMapping(value = "/findemp")
	public String findemp(HttpSession session,
							@RequestParam(required=false) Integer depart2,
							Model model) {

		int depart = 0;
		List<DepartVO> dlist = developService.selectAllDept();
		if(depart2 ==null) {
			List<InfoVO> list = infoService.selectListfindAll();
			model.addAttribute("paging",list);			
		}else {
			depart = depart2;
			List<InfoVO> paging2 = infoService.selectListfindDept(depart);
			model.addAttribute("depart",depart); // 부서번호
			model.addAttribute("paging",paging2); // 페이징
		}
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		model.addAttribute("dlist",dlist);
		return "findemp";
	}
	@RequestMapping(value = "/excelfindemp")
	public void exlefindemp(
								@RequestParam(required = false) Integer depart2,
								HttpServletResponse response) {
		infoService.exlefindemp(depart2, response);			
	}

	
	@RequestMapping(value="myhistory")
	public String myhistory(HttpSession session, Model model,@RequestParam(required=false)Integer type) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		String name = vo.getName();
		int Id_emp= vo.getId_emp();
	
			List<HistoryVO> hlist = infoService.myhistory(Id_emp, type);
			model.addAttribute("hlist",hlist);

		
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("list",list);
		
		return "myhistory";
	}
	@RequestMapping(value="excelmyhistory")
	public String excelmyhistory(HttpSession session, Model model,@RequestParam(required=false)Integer type) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		String name = vo.getName();
		int Id_emp= vo.getId_emp();
		System.out.println(type);
		List<HistoryVO> hlist = infoService.myhistory(Id_emp, type);
		model.addAttribute("hlist",hlist);
		
		
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("list",list);
		
		return "excelmyhistory";
	}
	@RequestMapping(value="history")
	public String history(HttpSession session, Model model,@RequestParam(required=false)Integer type) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		String name = vo.getName();
		
	
			List<HistoryVO> hlist = infoService.history(type);
			model.addAttribute("hlist",hlist);

		
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("list",list);
		
		return "history";
	}
	@RequestMapping(value="excelhistory")
	public String excelhistory(HttpSession session, Model model,@RequestParam(required=false)Integer type) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		String name = vo.getName();
		
		
		List<HistoryVO> hlist = infoService.history(type);
		model.addAttribute("hlist",hlist);
		
		
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("list",list);
		
		return "excelhistory";
	}
	
	@RequestMapping(value="changepassword")
	public String changepassword(HttpSession session, Model model) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoService.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		return "changepassword";
	}
	
	@RequestMapping(value="changepasswordOk")
	public String changepasswordOk(@RequestParam String password, @RequestParam int Id_emp) {
		infoService.changepassword(password, Id_emp);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/resources/findid")
	public String findid() {
		return "findid";
	}
	
	@RequestMapping(value="/resources/findidresult")
	public String findidresult(
								@RequestParam String name,
								@RequestParam(required = false) String email,
								@RequestParam(required = false) String mid_phone,
								@RequestParam(required = false) String last_phone,
								Model model
								) {
		
		int id = infoService.findid(name, email, mid_phone, last_phone);		
		model.addAttribute("id",id);
		return "findidresult";
	}
	@RequestMapping(value="/resources/findpw")
	public String findpw(HttpServletRequest request, Model model) {
		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));

		return "findpw";
	}
	@RequestMapping(value="/resources/findpwresult")
	public String findpwresult(
								@RequestParam int Id_emp, 
								@RequestParam String email, 
								@RequestParam String name,
								Model model) {
		System.out.println(Id_emp);
		System.out.println("이메일 : "+email);
		System.out.println("이 름: "+name);
		int result= infoService.pwcheck(Id_emp, name, email);
		System.out.println(result);
		if(result==3) {
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			infoService.mail(Id_emp, email, name);
			return "findpwresult";
		}else {
			return "redirect:/resources/findpw?result="+result;
		}
	}
	//비밀번호 변경 메일 후 인증시 갈 페이지!
	@RequestMapping(value="/resources/updatepassword")
	public String updatepassword(@RequestParam String key,@RequestParam int Id_emp, Model model) {
		String dbKey = infoService.getKey(Id_emp);
		if(dbKey!=null && dbKey.equals(key)) { // 저장된 키와 같다면 인증 아니면 인증실패!!! 
			infoService.deletekey(Id_emp);
			String realpw = infoService.realpw(Id_emp);
			model.addAttribute("rpw",realpw);
			model.addAttribute("Id_emp",Id_emp);
			return "updatepassword";
			}else {
				return "error500";
			}
	}
	
	@RequestMapping(value="/resources/updatepasswordOk")
	public String updatepwOk(@RequestParam int Id_emp, @RequestParam String password) {
		infoService.changepassword(password, Id_emp);
		return "redirect:/";
	}
}
