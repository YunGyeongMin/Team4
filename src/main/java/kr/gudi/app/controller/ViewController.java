package kr.gudi.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.gudi.app.service.DataService;

@Controller
@RequestMapping("/")
public class ViewController {
	
	@Autowired private DataService ds;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String index() {return "index";}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginView() {return "login";}
	
	@RequestMapping(value="message/{num}", method=RequestMethod.GET)
	public String message(@PathVariable("num") String num, Model model) {
		model.addAttribute("targetUser", ds.userInfo(num));
		return "message";
	}
	
	@RequestMapping(value="myEdit", method=RequestMethod.GET)
	public String myEdit() {return "myEdit";}
	
	@RequestMapping(value="myList/{num}", method=RequestMethod.GET)
	public String myList(@PathVariable("num") String num, Model model) {
		model.addAttribute("targetUser", ds.userInfo(num));
		return "myList";
	}
	
	@RequestMapping(value="profile/{num}", method=RequestMethod.GET)
	public String profile(@PathVariable("num") String num, Model model) {
		model.addAttribute("targetUser", ds.userInfo(num));
		return "profile";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String signup() {return "signup";}
	
}
