package kr.hoon.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hoon.project.service.DevelopService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.service.PromotionResultService;
import kr.hoon.project.service.PromotionTestService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.lev.LevVO;
import kr.hoon.project.vo.promotionresult.PromotionResultVO;
import kr.hoon.project.vo.promotiontest.PromotionTestVO;
import kr.hoon.project.vo.specialpromotion.SpecialPromotionVO;

@Controller
public class PromotionController {

	@Autowired
	private PromotionTestService promotionTestService;
	@Autowired
	private DevelopService developService;
	@Autowired
	private PromotionResultService promotionresultservice;
	@Autowired
	private InfoService inforservice;
	
	@RequestMapping(value="/promotionTester")
	public String promotiontestpeople(Model model, @RequestParam(required=false) Integer depart2,HttpSession session) {
		List<DepartVO> dlist = developService.selectAllDept();
		System.out.println(depart2);
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		int id = list.getId_emp();
		
		List<PromotionTestVO> alist = promotionTestService.promotiontestpeople(depart2, id);
		model.addAttribute("alist",alist);
		model.addAttribute("dlist",dlist);
		if(list.getDepart_d().equals("인사")) {			
			return "promotionTester";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/promotionadmin")
	public String promotionadmin(Model model,HttpSession session) {
		//직급이 부장의 이상인 경우의 사람들만 가져오자!!
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		List<InfoVO> alist = promotionTestService.promotionadmin();
		model.addAttribute("alist",alist);
		if(list.getDuty_du().equals("파트장") && list.getDepart_d().equals("인사")) {
			return "promotionadmin";
		}else {
			return "redirect:/index";
		}
		
	}
	
	@RequestMapping(value="/promotionadminOk", method=RequestMethod.POST)
	public String promotionadminOk ( HttpServletRequest request ) {
		promotionresultservice.promotionadminOk(request);
		return "redirect:/promotionadmin";
	}
	
	@RequestMapping(value="/promotionTest")
	public String PromotionTest(Model model, @RequestParam(required = false) Integer depart2, HttpSession session,HttpServletRequest request) {
		List<DepartVO> dlist = developService.selectAllDept();
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
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
		
		int id= list.getId_emp();
		
		List<PromotionTestVO> alist = promotionTestService.promotiontestpeople(depart2, id);
		model.addAttribute("alist",alist);	
		model.addAttribute("dlist",dlist);	
		model.addAttribute("pre",vo);	  //평가자의 정보
		if(vo.getTemp1().equals("평가자")) {
			return "promotionTest";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/promotionTesting", method=RequestMethod.POST)
	public String testyoung(Model model
							,@RequestParam String dname
							,@RequestParam String name_v
							,@RequestParam String duty_d
							,@RequestParam String lev_l
							,@RequestParam int Id_emp_v
							,HttpSession session
							) {
		InfoVO vo = (InfoVO)session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		int EmpId = vo.getId_emp();
		int count = promotionresultservice.getCountsubmit(EmpId,Id_emp_v);
		if(count == 0) {			
			model.addAttribute("name_v",name_v);
			model.addAttribute("dname",dname);
			model.addAttribute("duty_d",duty_d);
			model.addAttribute("lev_l",lev_l);
			model.addAttribute("Id_emp_v",Id_emp_v);
			model.addAttribute("vo",vo);
			return "promotionTesting";
		}else {
			return "redirect:/promotionTest?result="+count;
		}
		
		

	}
	
	@RequestMapping(value="/TestingSubmit", method=RequestMethod.POST)
	public String savingResult(@ModelAttribute PromotionResultVO vo) {
		promotionresultservice.submitResult(vo);
		return "redirect:/promotionTest";
	}
	
	
	@RequestMapping(value="testresultcheck")
	public String testresultcheck(Model model,HttpSession session) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		List<PromotionResultVO> alist = promotionresultservice.selectResultList();
		model.addAttribute("alist",alist);
		if(list.getDepart_d().equals("인사")) {			
			return "testresultcheck";
		}else {
			return "redirect:/index";
		}
	}

	
	@RequestMapping(value="testresultcheckOk", method=RequestMethod.POST)
	public String testresultcheckOk() {
		promotionresultservice.submitResultFin();
		return "redirect:/index";
	}
	
	
	//승진시험결과체크
	@RequestMapping(value="/promotioncheck")
	public String promotioncheck(@RequestParam(required=false)Integer check,Model model,HttpSession session) {
		if(check==null ) {
			int count=1;
			List<PromotionTestVO> alist = promotionresultservice.findTestResult(count);
			model.addAttribute("alist",alist);
		}else {
			int count = check;
			List<PromotionTestVO> alist = promotionresultservice.findTestResult(count);
			model.addAttribute("alist",alist);
		}
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		if(list.getDepart_d().equals("인사")) {
			return "promotioncheck";
		}else{
			return "redirect:/index";
		}
	}
	//정기승진승인
	@RequestMapping(value="/promotionapproval")
	public String promotionaproval(Model model ,HttpSession session, @RequestParam(required = false) Integer depart2) {
		
		System.out.println(depart2);
		List<PromotionTestVO> alist = promotionresultservice.promotionList(depart2);
		
		List<DepartVO> dlist = developService.selectAllDept();
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		model.addAttribute("dlist",dlist);
		model.addAttribute("alist",alist);
		if(list.getLev_l().equals("사장") || list.getDuty_du().equals("CEO")) {
			return "promotionapproval";
		}else {
			return "redirect:/index";
		}
	}
	// 승인 허락
	@RequestMapping(value="promtionapprovalOk", method=RequestMethod.POST)
	public String promtionapprovalOk(HttpServletRequest request) {

		promotionresultservice.promtionapprovalOk(request);
		return "redirect:/index";
	}
	//특진 첫화면
	@RequestMapping(value="/specialpromotion")
	public String specialpromotion(Model model,@RequestParam(required=false) Integer depart2, HttpSession session) {
		List<InfoVO> alist  =  promotionresultservice.selectAllEMP(depart2);
		List<DepartVO> dlist = developService.selectAllDept();
	
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);	
		
		model.addAttribute("dlist",dlist);
		model.addAttribute("alist",alist);
		if(list.getDepart_d().equals("인사") && list.getDuty_du().equals("파트장")) {			
			return "specialpromotion";
		}else {
			return "redirect:/index";
		}
	}
	@RequestMapping(value="/specialpromotionwrite")
	public String specialpromotionwrite(Model model,
										@RequestParam (required = false) String dname,
										@RequestParam (required = false) String name,
										@RequestParam (required = false) String lev_now,
										@RequestParam (required = false) int lev,
										@RequestParam (required = false) String duty,
										@RequestParam (required = false) String Id_emp,
										HttpSession session,
										HttpServletRequest request
										) {
		
		List<LevVO> llist= developService.selectAllLev();
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
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

		
		
		
		model.addAttribute("dname",dname);
		model.addAttribute("name", name);
		model.addAttribute("lev_now",lev_now);
		model.addAttribute("lev",lev);
		model.addAttribute("llist",llist);
		model.addAttribute("duty",duty);
		model.addAttribute("Id_emp",Id_emp);
		
		if(list.getDepart_d().equals("인사") && list.getDuty_du().equals("파트장")) {
		return "specialpromotionwrite";
		}else {
			return "redirect:/index";
		}
	}
	@RequestMapping(value="/specialpromotionOk",method=RequestMethod.POST)
	public String specialpromotionOk(@ModelAttribute SpecialPromotionVO vo,@RequestParam int nowlev, @RequestParam String url) {
		int result = promotionresultservice.specialpromotionCheck(nowlev, vo);
		
		if(result==3) {
			promotionresultservice.insertspecialpromotion(vo);
			return "redirect:/specialpromotion";
		}else {
			return "redirect:/specialpromotionwrite?result="+result;
		}
		
	}

	@RequestMapping(value="/specialpromotionapproval")
	public String specialpromotionapproval(Model model,@RequestParam(required = false) Integer depart2,HttpSession session) {
		List<SpecialPromotionVO> slist = promotionresultservice.specialpromotionList(depart2);
		
		List<LevVO> alist = developService.selectAllLev();
		List<DepartVO> dlist = developService.selectAllDept();
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = inforservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);		
		
		model.addAttribute("dlist",dlist);
		model.addAttribute("alist",alist);
		model.addAttribute("slist",slist);
		
		if( list.getLev_l().equals("사장")) {
			return "specialpromotionapproval";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/specialpromotionapprovalOk")
	public String specialpromotionapprovalOk(HttpServletRequest request) {
		promotionresultservice.specialpromotionadminOk(request);
	
		return "redirect:/index";
	}
	
}
