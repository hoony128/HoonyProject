package kr.hoon.project;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hoon.project.service.DevelopService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.depart.DepartVO;
import kr.hoon.project.vo.duty.DutyVO;
import kr.hoon.project.vo.hirestate.HirestateVO;
import kr.hoon.project.vo.lev.LevVO;
import kr.hoon.project.vo.state.StateVO;

@Controller
public class DevelopController {
	@Autowired
	private DevelopService developService;
	@Autowired
	private InfoService infoService;
	
	
	
	@RequestMapping(value="ld")
	public String insertLev(Model model, HttpSession session) {
		 List<LevVO> Llist = developService.selectAllLev();
		 List<DepartVO> Dlist = developService.selectAllDept();
		 List<StateVO> Slist = developService.selectAllState();
		 List<HirestateVO> hlist= developService.selectAllHirestate();
		 List<DutyVO> dulist = developService.selectAllDuty();
			InfoVO vo = (InfoVO) session.getAttribute("vo");
			InfoVO list = infoService.selectByUserid(vo.getId_emp());
			model.addAttribute("list",list);
		 model.addAttribute("Llist",Llist);
		 model.addAttribute("Dlist",Dlist);
		 model.addAttribute("Slist",Slist);
		 model.addAttribute("Hlist",hlist);
		 model.addAttribute("Dulist",dulist);
		if(list.getDepart_d().equals("개발")) {
			return "ld";
		}else {
			return "redirect:/index";
		}
	}
	
	@RequestMapping(value="insertldOk")
	public String insertLevOk(@RequestParam String lev) {
		System.out.println(lev);
		developService.insertLev(lev);
		return "redirect:/ld";
	}
	
	@RequestMapping(value= "insertdeptOk")
	public String insertdeptOk(@RequestParam String depart) {
		developService.insertdept(depart);
		return "redirect:/ld";

	}
	@RequestMapping(value= "insertstateOk")
	public String insertstaOk(@RequestParam String state) {
		developService.insertstate(state);
		return "redirect:/ld";
		
	}
	@RequestMapping(value= "inserthirestateOk")
	public String inserthirestateOk(@RequestParam String hirestate) {
		developService.inserthirestate(hirestate);
		return "redirect:/ld";
		
	}
	
	@RequestMapping(value= "insertdutyOk")
	public String insertdutyOk(@RequestParam String duty) {
		developService.insertDuty(duty);
		return "redirect:/ld";
		
	}
	
	
	
	@RequestMapping(value="updatelev")
	public String updateLev(@RequestParam String lev, @RequestParam int idx) {
		developService.updateLev(lev, idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="deletelev")
	public String deleteLev( @RequestParam int idx) {
		developService.deleteLev(idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="updatedep")
	public String updatedep(@RequestParam String depart, @RequestParam int idx) {
		developService.updatedept(depart, idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="deletedep")
	public String deletedep( @RequestParam int idx) {
		developService.deletedept(idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="updatest")
	public String updatest(@RequestParam String state, @RequestParam int idx) {
		developService.updatestate(state, idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="deletest")
	public String deletest( @RequestParam int idx) {
		developService.deletestate(idx);;
		
		return "redirect:/ld";
	}
	@RequestMapping(value="updateh")
	public String updateh(@RequestParam String hirestate, @RequestParam int idx) {
		developService.updatehirestate(hirestate, idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="deleteh")
	public String deleteh( @RequestParam int idx) {
		developService.deletehirestate(idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="updatedu")
	public String updatdu(@RequestParam String duty, @RequestParam int idx) {
		developService.updateDuty(duty, idx);
		
		return "redirect:/ld";
	}
	@RequestMapping(value="deletedu")
	public String deletdu( @RequestParam int idx) {
		developService.deleteDuty(idx);

		return "redirect:/ld";
	}
	
}
