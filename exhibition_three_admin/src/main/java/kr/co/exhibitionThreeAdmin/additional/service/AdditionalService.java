package kr.co.exhibitionThreeAdmin.additional.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.additional.dao.AdditionalDAO;
import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;

@Service
public class AdditionalService {
	
	@Autowired(required = false)
	private AdditionalDAO aDao;
	
	//로그인
	public int checkPw(LoginVO lvo) {
		int cnt=0;
		try {
			BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
			if(encoder.matches(lvo.getPassword(), aDao.passwordChk(lvo.getAdmin_id()))) cnt=1;
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}

	public int updatePw(LoginVO lvo) {
		int cnt=0;
		try {
			cnt = aDao.pwUpdate(lvo);
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	
	
	// index용
	public int countAllMember() {
		int cnt=0;
		try {
			cnt = aDao.countAllMember();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int cntTodayMember() {
		int cnt=0;
		try {
			cnt = aDao.cntTodayMember();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}

	public int cntShownRez() {
		int cnt=0;
		try {
			cnt = aDao.cntShownRez();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int cntAllRez() {
		int cnt=0;
		try {
			cnt = aDao.cntAllRez();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int cntTodayRez() {
		int cnt=0;
		try {
			cnt = aDao.cntTodayRez();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}

	public int cntTodayBoard() {
		int cnt=0;
		try {
			cnt = aDao.cntTodaysBoard();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int cntAllExhibition() {
		int cnt=0;
		try {
			cnt = aDao.cntAllExhibition();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int endedExhibition() {
		int cnt=0;
		try {
			cnt = aDao.endedExhibition();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
	public int endsTomorrow() {
		int cnt=0;
		try {
			cnt = aDao.endsTomorrow();
		}catch (PersistenceException pe) {
			pe.printStackTrace();
		}
		return cnt;
	}
}
