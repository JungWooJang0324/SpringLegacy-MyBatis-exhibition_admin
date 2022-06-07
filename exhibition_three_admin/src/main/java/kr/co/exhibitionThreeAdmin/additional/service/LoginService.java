package kr.co.exhibitionThreeAdmin.additional.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.additional.dao.LoginDAO;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;

@Service
public class LoginService {
	
	@Autowired(required = false)
	private LoginDAO lDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public int loginChk(LoginVO lvo) {
		int cnt=0;
		try {
			String check= lDao.loginCheck(lvo.getAdmin_id());

			if(encoder.matches(lvo.getPassword(), check)) cnt=1;
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
}
