package kr.co.exhibitionThreeAdmin.additional.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;

@Component
public class LoginDAO {
		
	
	public String loginCheck(String id) throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();

		String pass= ss.selectOne("kr.co.exhibitionThreeAdmin.additional.loginChk", id);
		if(ss != null) {ss.close();}//end if

		return pass;
	}
	
	
	
}
