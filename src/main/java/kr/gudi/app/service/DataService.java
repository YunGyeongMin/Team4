package kr.gudi.app.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface DataService {
	
	public Map<String, Object> signup(Map<String, Object> paramMap);
	public Map<String, Object> userInfo(String num);
	public Map<String, Object> userInterests(Map<String, Object> paramMap);
	public Map<String, Object> userSelect();
	public Map<String, Object> messageList(String num);
	public Map<String, Object> messageInsert(Map<String, Object> paramMap);
	public Map<String, Object> EditImg(HttpSession session, MultipartFile file);
	public Map<String, Object> myEdit(Map<String, Object> paramMap);
}
