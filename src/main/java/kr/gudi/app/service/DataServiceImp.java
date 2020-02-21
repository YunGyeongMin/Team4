package kr.gudi.app.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.gudi.app.dao.DataDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DataServiceImp implements DataService {
	
	@Autowired private DataDao dd;
	private final String ROOT = "D:/GDJ21/IDE/workspace/Team4/src/main/webapp";
	private Map<String, Object> resultMap;

	@Override
	public Map<String, Object> signup(Map<String, Object> paramMap) {
		return dd.signup(paramMap);
	}

	@Override
	public Map<String, Object> userInfo(String num) {
		return dd.userInfo(num);
	}

	@Override
	public Map<String, Object> userInterests(Map<String, Object> paramMap) {
		return dd.userInterests(paramMap);
	}

	@Override
	public Map<String, Object> userSelect() {
		return dd.userSelect();
	}

	@Override
	public Map<String, Object> messageList(String num) {
		return dd.messageList(num);
	}

	@Override
	public Map<String, Object> messageInsert(Map<String, Object> paramMap) {
		return dd.messageInsert(paramMap);
	}

	@Override
	public Map<String, Object> EditImg(HttpSession session, MultipartFile file) {
		resultMap = new HashMap<String, Object>();
		try {
			Object user = session.getAttribute("info");
			if(user != null) {
				Map<String, Object> userMap = (Map<String, Object>) user;
				String url = "res/files/" + userMap.get("num") + "/";
//				String path = ROOT + url;
				String path = session.getServletContext().getRealPath(url);
				File dir = new File(path);
				if(!dir.isDirectory()) {dir.mkdirs();}
				path += file.getOriginalFilename();
				url += "/" + file.getOriginalFilename();
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
				userMap.put("img", url);
				System.out.println(userMap);
				resultMap.put("data", dd.EditImg(userMap));
				resultMap.put("userMap", dd.userInfo(userMap.get("num").toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", 0);
			resultMap.put("data", map);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> myEdit(Map<String, Object> paramMap) {
		resultMap = new HashMap<String, Object>();
		try {
			Map<String, Object> intersetsMap = new HashMap<String, Object>();
			Map<String, Object> rowMap;
			int size = 0;
			intersetsMap = new ObjectMapper().readValue(paramMap.get("intersets").toString(), Map.class);
			Iterator<String> iterator = intersetsMap.keySet().iterator();
			while(iterator.hasNext()) {
				rowMap = new HashMap<String, Object>();
				String num = iterator.next();
				rowMap.put("num", num);
				rowMap.put("state", intersetsMap.get(num));
				rowMap.put("reg_num", paramMap.get("reg_num"));
				size += dd.setUserInterests(rowMap);
			}
			resultMap.put("user", dd.setUserInfo(paramMap));
			resultMap.put("status", size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

}
