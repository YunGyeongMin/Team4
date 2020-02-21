package kr.gudi.app.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataDaoImp implements DataDao {
	
	@Autowired private SqlSession session;
	private Map<String, Object> resultMap;

	@Override
	public Map<String, Object> signup(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result1", session.insert("team.signup_user", paramMap));
		resultMap.put("result2", session.insert("team.signup_auth", paramMap));
		resultMap.put("result3", session.insert("team.signup_interests", paramMap));
		return resultMap;
	}

	@Override
	public Map<String, Object> userInfo(String num) {
		return session.selectOne("team.userInfo", num);
	}

	@Override
	public Map<String, Object> userInterests(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", session.selectList("team.userInterests", paramMap));
		return resultMap;
	}

	@Override
	public Map<String, Object> userSelect() {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", session.selectList("team.userInfo"));
		return resultMap;
	}

	@Override
	public Map<String, Object> messageList(String num) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", session.selectList("team.getMessage", num));
		return resultMap;
	}

	@Override
	public Map<String, Object> messageInsert(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", session.insert("team.setMessage", paramMap));
		return resultMap;
	}

	@Override
	public Map<String, Object> EditImg(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result", session.update("team.setImage", paramMap));
		return resultMap;
	}

	@Override
	public Map<String, Object> setUserInfo(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		resultMap.put("result1", session.update("team.setUserInfo", paramMap));
		return resultMap;
	}

	@Override
	public int setUserInterests(Map<String, Object> paramMap) {
		return session.update("team.setUserInterests", paramMap);
	}

}
