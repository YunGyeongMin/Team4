package kr.gudi.app.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDaoImp implements HomeDao {

	@Autowired
	SqlSession session;
	
	@Override
	public String getString() {
		return session.selectOne("sql.test");
	}

	@Override
	public Map<String, Object> getUserInfo(String num) {
		return session.selectOne("sql.userInfo", num);
	}

}
