package kr.gudi.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.gudi.app.service.DataService;

@Controller
@RequestMapping("/")
public class DataController {
	
	@Autowired private DataService ds;
	private Map<String, Object> resultMap;

	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String signup(@RequestParam Map<String, Object> paramMap) {
		System.out.println(paramMap);
		System.out.println(ds.signup(paramMap));
		return "redirect:/login";
	}
	
	@RequestMapping(value="page/index", method=RequestMethod.POST)
	public String userSelect(Model model) {
		model.addAttribute("data", ds.userSelect());
		return "page/index";
	}
	
	@RequestMapping(value="message", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> message(@RequestBody Map<String, Object> paramMap, @AuthenticationPrincipal UserDetails userDetails) {
		paramMap.put("reg_num", userDetails.getUsername());
		System.out.println(paramMap);
		return ds.messageInsert(paramMap);
	}
	
	@RequestMapping(value="page/myList/{num}", method=RequestMethod.POST)
	public String message_list(@PathVariable("num") String num, Model model) {
		model.addAttribute("data", ds.messageList(num));
		return "page/myList";
	}
	
	@RequestMapping(value="EditImg", method=RequestMethod.POST)
	@ResponseBody
	public Object EditImg(@RequestParam("file") MultipartFile file, HttpSession session) {
		resultMap = ds.EditImg(session, file);
		Map<String, Object> data = (Map<String, Object>) resultMap.get("data");
		if(Integer.parseInt(data.get("result").toString()) > 0) {
			session.setAttribute("info", resultMap.get("userMap"));
		}
		return resultMap.get("data");
	}
	
	@RequestMapping(value="page/myEdit", method=RequestMethod.POST)
	public String myEdit(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("num", userDetails.getUsername());
		model.addAttribute("userInfo", 		ds.userInfo(userDetails.getUsername().toString()));
		model.addAttribute("userInterests", ds.userInterests(paramMap));
		return "page/myEdit";
	}
	
	@RequestMapping(value="myEdit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> myEdit(@RequestBody Map<String, Object> paramMap, @AuthenticationPrincipal UserDetails userDetails) {
		paramMap.put("reg_num", userDetails.getUsername());
		System.out.println(paramMap);
		return ds.myEdit(paramMap);
	}
	
	@RequestMapping(value="page/profile", method=RequestMethod.POST)
	public String profile(@RequestBody Map<String, Object> paramMap, Model model) {
		paramMap.put("type", 1);
		model.addAttribute("userInfo", 		ds.userInfo(paramMap.get("num").toString()));
		model.addAttribute("userInterests", ds.userInterests(paramMap));
		return "page/profile";
	}
	
}
