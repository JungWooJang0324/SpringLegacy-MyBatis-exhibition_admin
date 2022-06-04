package kr.co.exhibitionThreeAdmin.additional.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.additional.dao.AdditionalDAO;

@Service
public class AdditionalService {
	
	@Autowired(required = false)
	private AdditionalDAO aDao;
	
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
}
