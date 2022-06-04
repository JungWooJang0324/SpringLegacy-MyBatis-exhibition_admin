package kr.co.exhibitionThreeAdmin.additional.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.mybatis.MyBatisFramework;

@Component
public class LoginDAO {
		
	
	public String loginCheck(LoginVO lVO) throws PersistenceException{
		SqlSession ss = MyBatisFramework.getInstance().getMyBatisHandler();

		String createdDate= ss.selectOne("kr.co.exhibitionThreeAdmin.additional.loginChk", lVO);
		if(ss != null) {ss.close();}//end if

		return createdDate;
	}
	
	
	
}
