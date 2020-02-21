package kr.gudi.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.gudi.app.service.HomeService;

@Controller
@RequestMapping("/Test")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired private HomeService hService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		logger.info("결과값 : {}", hService.getString());
		return "security/home";
	}
	
	@RequestMapping(value = "/login")
	public String login() {return "security/login";}
	
	@RequestMapping(value = "/error", method=RequestMethod.POST)
	public String error() {return "redirect:/Test/error";}
	
	@RequestMapping(value = "/error", method=RequestMethod.GET)
	public String errorView() {return "security/error";}
	
	@RequestMapping(value = "/denied")
	public String denied() {return "security/authorizeError";}
	
	@RequestMapping(value = "/success", method=RequestMethod.POST)
	public String success() {return "redirect:/Test/wellcom";}
	
	@RequestMapping(value = "/wellcom", method=RequestMethod.GET)
	public String wellcom(@AuthenticationPrincipal UserDetails userDetails, HttpSession session) {
		logger.info("사용자 번호 : {}", userDetails.getUsername());
		session.setAttribute("info", hService.getUserInfo(userDetails.getUsername()));
		return "security/home";
	}
	
	@RequestMapping(value = "/admin")
	public String hello() {return "security/admin";}
	
}
