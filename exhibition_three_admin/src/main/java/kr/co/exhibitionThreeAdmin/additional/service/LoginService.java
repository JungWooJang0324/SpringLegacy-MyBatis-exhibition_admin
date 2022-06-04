package kr.co.exhibitionThreeAdmin.additional.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.additional.dao.LoginDAO;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;

@Service
public class LoginService {
	
	@Autowired(required = false)
	private LoginDAO lDao;
	
	public String loginChk(LoginVO lVO) {
		String createdDate= "";
		try {
			createdDate = lDao.loginCheck(lVO);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return createdDate;
	}
}
