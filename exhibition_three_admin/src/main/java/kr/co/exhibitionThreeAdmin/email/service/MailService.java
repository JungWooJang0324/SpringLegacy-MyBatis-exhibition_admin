package kr.co.exhibitionThreeAdmin.email.service;

import java.util.List;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import kr.co.exhibitionThreeAdmin.email.vo.EmailVO;
import kr.co.exhibitionThreeAdmin.member.dao.AdminMemberDAO;
import kr.co.exhibitionThreeAdmin.member.domain.MemberDomain;
import kr.co.exhibitionThreeAdmin.member.vo.EsVO;

@Service
public class MailService {
	
	@Inject
	private JavaMailSender mailSender;
	@Autowired
	private AdminMemberDAO aDAO;
	
	/**
	 *  
	 * @param field
	 * @return
	 */
	public List<MemberDomain> selectId(EsVO eVO) {
		List<MemberDomain> list = null;
		try {
			list = aDAO.selectId(eVO);
		}catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		return list;
	}//selectId
	
	/**
	 * 이메일 전송 서비스
	 * @param eVO
	 */
	public String sendMail(EmailVO eVO) {
		String msg ="전송 실패했습니다";
		String[] parseAddr = eVO.getToAddress().split(" ");
		InternetAddress[] toAddr = new InternetAddress[parseAddr.length];
		try {
				
				for(int i =0; i < parseAddr.length; i++) {
					toAddr[i] = new InternetAddress(parseAddr[i]);
				}//end for
				MimeMessage message = mailSender.createMimeMessage();
				message.setRecipients(Message.RecipientType.TO, toAddr);
				//MimeMessageHelper messageHelper = new MimeMessageHelper(message,true, "UTF-8");
				message.setSubject(eVO.getSubject());
				message.setText(eVO.getMessage());
			
			mailSender.send(message);
			msg="전송 성공했습니다.";
		} catch (MessagingException e) {
			e.printStackTrace();
		}//end catch
		return msg;
	}//sendMail
}//class
