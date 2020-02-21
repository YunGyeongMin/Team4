package kr.gudi.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.gudi.app.service.DataService;

@Controller
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired private DataService ds;

	@RequestMapping(value="/error", method=RequestMethod.POST)
	public String error() {return "redirect:/security/error";}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String errorView() {return "security/error";}
	
	@RequestMapping(value="/denied")
	public String denied() {return "security/authorizeError";}
	
	@RequestMapping(value="/success", method=RequestMethod.POST)
	public String success(@AuthenticationPrincipal UserDetails userDetails, HttpSession session) {
		session.setAttribute("info", ds.userInfo(userDetails.getUsername()));
		return "redirect:/";
	}
	
}
