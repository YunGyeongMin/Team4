package kr.gudi.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.gudi.app.dao.HomeDao;

@Service
public class HomeServiceImp implements HomeService {

	@Autowired private HomeDao hDao;
	
	@Override public String getString() {return hDao.getString();}
	@Override public Map<String, Object> getUserInfo(String num) {return hDao.getUserInfo(num);}

}
