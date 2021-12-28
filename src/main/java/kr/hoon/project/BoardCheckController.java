package kr.hoon.project;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.hoon.project.service.BoardCheckService;
import kr.hoon.project.service.BoardService;
import kr.hoon.project.service.InfoService;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.boardcheck.BoardCheckVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class BoardCheckController {
	
	@Autowired
	private BoardCheckService boardCheckService;
	@Autowired
	private InfoService infoservice;
	
	@RequestMapping(value="/boardCheckList")
	public String boardCheckList(Model model, @RequestParam(required = false) String boardtype,@RequestParam(required = false)String type ,HttpSession session) {
		
		List<BoardCheckVO> alist = boardCheckService.boardType();
		model.addAttribute("alist",alist);
		model.addAttribute("bt",boardtype);
		model.addAttribute("type",type);
		
		InfoVO vo2 = (InfoVO) session.getAttribute("vo");
		InfoVO list = infoservice.selectByUserid(vo2.getId_emp());
		model.addAttribute("list",list);	
		
		List<BoardCheckVO> blist = boardCheckService.typeselect(boardtype);
		model.addAttribute("blist",blist);
		
		List<BoardCheckVO> search = boardCheckService.boardchecklist(boardtype,type); 		
		model.addAttribute("search",search);
		
		return "boardCheckList";
	}
	@RequestMapping(value="/jBoardCheckList")
	@ResponseBody
	
	public List<BoardCheckVO> jBoardCheckList(Model model, @RequestParam(required = false) String boardtype,@RequestParam(required = false)String type ,HttpSession session ,HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		List<BoardCheckVO> blist = boardCheckService.typeselect(boardtype);


		return blist;
	}
		
	
	@RequestMapping(value="/excelboardcheck")
	public void excelboardcheck(@RequestParam(required = false) String boardtype, @RequestParam(required = false) String type , HttpServletResponse response) {
			boardCheckService.excelboardcheck(boardtype, type,response);
	}
}
