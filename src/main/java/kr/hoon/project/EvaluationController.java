package kr.hoon.project;

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

import kr.hoon.project.service.DevelopService;
import kr.hoon.project.service.EvaluationService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.evaluation.EvaluationSaveVO;
import kr.hoon.project.vo.evaluation.EvaluationVO;

@Controller
public class EvaluationController {
	
	@Autowired
	private EvaluationService evaluationservice;
	@Autowired
	private DevelopService developService;
	@Autowired
	private InfoService infoservice;
	
	@RequestMapping(value="/evaluation")
	public String evaluation(HttpSession session, Model model,HttpServletRequest request) {
		InfoVO vo = (InfoVO) session.getAttribute("vo");		
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
		List<InfoVO> dlist = evaluationservice.selectDep(depart);
		
		// 모델에 추가!
		model.addAttribute("list",vo);
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));
		model.addAttribute("name",name);
		model.addAttribute("Id_emp",Id_emp);
		model.addAttribute("dlist",dlist);
		model.addAttribute("dname",departName);
		if(vo.getDuty_du().equals("파트장")) {
			
			return "evaluation";
		}else {
			return "redirect:/index";
		}
	}
	

	
	@RequestMapping(value="/evaluationInput", method=RequestMethod.POST)
	public String evaluationInput(  
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
		int result = evaluationservice.submitEvaluationCheck(Id_emp_v);
		EvaluationSaveVO vo = evaluationservice.selectId_emp_v(Id_emp_v);			
		if(result ==0){
			InfoVO vo2 = (InfoVO) session.getAttribute("vo");
			InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
			model.addAttribute("list",list);
			
		if(vo!=null) {
			String reason = vo.getReason();
			int score =vo.getScore();
			model.addAttribute("reason",reason);
			model.addAttribute("score",score);
		}
		model.addAttribute("duty_d",duty_d);
		model.addAttribute("dname",dname);
		model.addAttribute("lev_l",lev_l);
		model.addAttribute("name_p",name_p);
		model.addAttribute("name_v",name_v);
		model.addAttribute("Id_emp_p",Id_emp_p);
		model.addAttribute("Id_emp_v",Id_emp_v);
		model.addAttribute("duty",duty);
		model.addAttribute("lev",lev);
		return "evaluationInput";
		}else {
			return"redirect:/evaluation?result="+result;
		}
	}
	@RequestMapping(value="/evaluationFinalInput")
	public String evaluationFinalInput(  
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
		int result = evaluationservice.submitEvaluationCheck(Id_emp_v);
		EvaluationSaveVO vo = evaluationservice.selectId_emp_v(Id_emp_v);			
		if(result ==0){
			InfoVO vo2 = (InfoVO) session.getAttribute("vo");
			InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
			model.addAttribute("list",list);
			
			if(vo!=null) {
				String reason = vo.getReason();
				int score =vo.getScore();
				model.addAttribute("reason",reason);
				model.addAttribute("score",score);
			}
			model.addAttribute("duty_d",duty_d);
			model.addAttribute("dname",dname);
			model.addAttribute("lev_l",lev_l);
			model.addAttribute("name_p",name_p);
			model.addAttribute("name_v",name_v);
			model.addAttribute("Id_emp_p",Id_emp_p);
			model.addAttribute("Id_emp_v",Id_emp_v);
			model.addAttribute("duty",duty);
			model.addAttribute("lev",lev);
			return "evaluationFinalInput";
		}else {
			return"redirect:/evaluation?result="+result;
		}
	}
	
	
	@RequestMapping(value="/evaluationSave", method=RequestMethod.POST)
	public String evaluationSave(@ModelAttribute("vo") EvaluationSaveVO vo) {
		evaluationservice.evaluationsave(vo);
		return "redirect:/evaluation";
	}
	
	
	@RequestMapping(value="/evaluationSubmit", method=RequestMethod.POST)
	public String evaluationsubmit(@ModelAttribute("vo") EvaluationVO vo) {
		evaluationservice.submitEvaluation(vo);
		return "redirect:/evaluation";
	}
	
	@RequestMapping(value="/findevaluation")
	public String findevaluation(@RequestParam(required=false)Integer depart2,Model model,HttpSession session) {
		List<InfoVO> flist = evaluationservice.findevaluation(depart2);
		List<DepartVO> dlist = developService.selectAllDept();
		InfoVO vo = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo.getId_emp());

		model.addAttribute("depart",depart2);
				
		model.addAttribute("list",list);
		model.addAttribute("flist",flist);
		model.addAttribute("dlist",dlist);
		if(list.getDepart_d().equals("인사")) {
			return "findevaluation";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="excelfindevaluation")
	public void excelfindevaluation(@RequestParam(required=false) Integer depart, HttpServletResponse response) {
		evaluationservice.excelfindevaluation(depart, response);
	}
}
