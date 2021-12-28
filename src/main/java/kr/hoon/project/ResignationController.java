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
import kr.hoon.project.service.ResignationService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.resignation.ResignationVO;

@Controller
public class ResignationController {


	@Autowired
	private ResignationService resignationService;
	@Autowired
	private DevelopService developService;
	@Autowired
	private InfoService infoservice;
	
	
	@RequestMapping(value="/resignation")
	public String resignation(HttpSession session, Model model,HttpServletRequest request) {
	
		
		
		InfoVO vo = (InfoVO) session.getAttribute("vo");	
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);	
		
		String name = vo.getName();
		int Id_emp = vo.getId_emp();
		int depart = vo.getDepart();
		String departName = vo.getDepart_d();

		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		// 부서별 사람 가져오기!
		List<InfoVO> alist = resignationService.selectDep(depart);

		// 모델에 추가!
		if(list.getDuty_du().equals("파트장")) {
			
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));
		model.addAttribute("name",name);
		model.addAttribute("Id_emp",Id_emp);
		model.addAttribute("alist",alist);
		model.addAttribute("dname",departName);
		return "resignation";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/resignationInput", method=RequestMethod.POST)
	public String resignationInput(  
									@RequestParam int duty,									
									@RequestParam int lev,										
									@RequestParam String dname,										
									@RequestParam String name_p,
									@RequestParam String name_v,
									@RequestParam int Id_emp_p,
									@RequestParam int Id_emp_v,
									@RequestParam String duty_d,
									@RequestParam String lev_l,
									Model model,HttpSession session
									) {
		int result = resignationService.getCountSubmit(Id_emp_v);
		System.out.println(Id_emp_v);
		ResignationVO vo = resignationService.select_temp(Id_emp_v);
		if(result ==0){
			
		if(vo!=null) {
			String reason = vo.getReason();
			model.addAttribute("reason",reason);
		}
		InfoVO vo1 = (InfoVO) session.getAttribute("vo");	
		InfoVO list = infoservice.selectByUserid(vo1.getId_emp());
		model.addAttribute("list",list);
		model.addAttribute("duty_d",duty_d);
		model.addAttribute("dname",dname);
		model.addAttribute("lev_l",lev_l);
		model.addAttribute("name_p",name_p);
		model.addAttribute("name_v",name_v);
		model.addAttribute("Id_emp_p",Id_emp_p);
		model.addAttribute("Id_emp_v",Id_emp_v);
		model.addAttribute("duty",duty);
		model.addAttribute("lev",lev);
		return "resignationInput";
		}else {
			return"redirect:/resignation?result="+result;
		}
	}
	@RequestMapping(value="/resignationFinalInput", method=RequestMethod.POST)
	public String resignationfinalInput(  
			@RequestParam int duty,									
			@RequestParam int lev,										
			@RequestParam String dname,										
			@RequestParam String name_p,
			@RequestParam String name_v,
			@RequestParam int Id_emp_p,
			@RequestParam int Id_emp_v,
			@RequestParam String duty_d,
			@RequestParam String lev_l,
			Model model,
			HttpSession session
			) {
		ResignationVO vo = resignationService.select_temp(Id_emp_v);
	
			if(vo!=null) {
				String reason = vo.getReason();
				model.addAttribute("reason",reason);
			}
			InfoVO vo1 = (InfoVO) session.getAttribute("vo");	
			InfoVO list = infoservice.selectByUserid(vo1.getId_emp());
			model.addAttribute("list",list);
			model.addAttribute("duty_d",duty_d);
			model.addAttribute("dname",dname);
			model.addAttribute("lev_l",lev_l);
			model.addAttribute("name_p",name_p);
			model.addAttribute("name_v",name_v);
			model.addAttribute("Id_emp_p",Id_emp_p);
			model.addAttribute("Id_emp_v",Id_emp_v);
			model.addAttribute("duty",duty);
			model.addAttribute("lev",lev);
			return "resignationFinalInput";
		
	}
	
	@RequestMapping(value="/resignationSave", method=RequestMethod.POST)
	public String resignationSave(@ModelAttribute ResignationVO vo) {
		resignationService.insertTemp(vo.getId_emp_v(), vo);
		return "redirect:/resignation";
	}
	
	@RequestMapping(value="/resignationSubmit", method=RequestMethod.POST)
	public String resignationsubmit(@ModelAttribute("vo") ResignationVO vo) {
		resignationService.submitResignation(vo.getId_emp_v(), vo);
		return "redirect:/resignation";
	}

	
	@RequestMapping(value="/resigner")
	public String resigner(Model model, @RequestParam(required=false) Integer depart2, HttpSession session) {
		
		List<ResignationVO> alist = resignationService.selectList(depart2);
		List<DepartVO> dlist = developService.selectAllDept();
		InfoVO vo = (InfoVO)session.getAttribute("vo");
		String duty = vo.getDuty_du();
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		
		model.addAttribute("alist",alist);
		model.addAttribute("dlist",dlist);
		model.addAttribute("duty",duty);
		
		if(list.getDepart_d().equals("인사")) {			
			return "resigner";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping( value = "/resignerOk", method=RequestMethod.POST)
	public String resignerOk(HttpServletRequest request) {
		
		resignationService.resignerOk(request);
		return "redirect:/resigner";
	}
	
	@RequestMapping(value="/resignerNo", method=RequestMethod.POST)
	public String resignerNo(HttpServletRequest request) {
		
		resignationService.resingerNo(request);
		return "redirect:/resigner";
	}
	
	
	
	
	@RequestMapping(value="/resigneapproval")
	public String resigneapproval(Model model,@RequestParam(required=false) Integer depart2,HttpSession session) {
		InfoVO vo = (InfoVO)session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());
		model.addAttribute("list",list);
		List<ResignationVO> alist = resignationService.selectListApp(depart2);
		List<DepartVO> dlist = developService.selectAllDept();
		
		
		model.addAttribute("alist",alist);
		model.addAttribute("dlist",dlist);
		
		if(list.getLev_l().equals("사장")) {
			return "resigneapproval";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="/resigneapprovalOk", method=RequestMethod.POST)
	public String resigneapprovalOk(HttpServletRequest request ) {
 		resignationService.resigneapprovalOk(request);
		return "redirect:/index";
	}
	@RequestMapping(value="/resigneapprovalNo", method=RequestMethod.POST)
	public String resigneapprovalNo(HttpServletRequest request ) {
		resignationService.resigneapprovalNo(request);
		return "redirect:/index";
	}
}
