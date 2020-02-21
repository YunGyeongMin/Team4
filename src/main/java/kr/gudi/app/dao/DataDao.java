package kr.gudi.app.dao;

import java.util.Map;

public interface DataDao {
	
	public Map<String, Object> signup(Map<String, Object> paramMap);
	public Map<String, Object> userInfo(String num);
	public Map<String, Object> userInterests(Map<String, Object> paramMap);
	public Map<String, Object> userSelect();
	public Map<String, Object> messageList(String num);
	public Map<String, Object> messageInsert(Map<String, Object> paramMap);
	public Map<String, Object> EditImg(Map<String, Object> paramMap);
	public Map<String, Object> setUserInfo(Map<String, Object> paramMap);
	public int setUserInterests(Map<String, Object> paramMap);
}
