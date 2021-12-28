package kr.hoon.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model,HttpSession session) {
		return "home";
	}
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model,HttpSession session) {
		String referer = "";
		if(request.getParameter("referer")==null)
			referer = request.getHeader("referer");
		else
			referer = request.getParameter("referer");
		
		if(referer==null || referer.trim().length()==0) 
			referer= request.getContextPath() + "/";
		
		model.addAttribute("prevUrl", referer);
		model.addAttribute("result", request.getParameter("result"));
		return "login";
	}

}
