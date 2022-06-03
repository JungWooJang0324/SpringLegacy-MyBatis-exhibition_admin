package kr.co.exhibitionThreeAdmin.exhibition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.exhibitionThreeAdmin.exhibition.service.AdminExhibitionService;
import kr.co.exhibitionThreeAdmin.exhibition.vo.ExhibitionVO;
import kr.co.exhibitionThreeAdmin.s3.FileManagement;
import kr.co.exhibitionThreeAdmin.search.vo.SearchVO;
@Component
@Controller
public class AdminExhibitionController {
	@Autowired(required = false)
	AdminExhibitionService as;
	@Autowired
	FileManagement filemanagement;
	
	@RequestMapping(value="/admin/exhibitions.do",method=GET)
	public String exhibitionMain(Model model,SearchVO sVO) {
		int pageScale = sVO.getPageScale();
		int totalCnt = as.searchTotalCnt(sVO);
		int pageCnt = as.pageCnt(totalCnt, pageScale);
		int currentPage = sVO.getCurrentPage();
		int startNum = as.startNum(currentPage,sVO.getPageScale());
		int endNum = as.endNum(startNum, pageScale);
		int pageBlock = as.pageBlock();
		int startPage = as.startPage(currentPage, pageBlock);
		int endPage = as.endPage(startPage, pageBlock);
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		
		model.addAttribute("exhibitionList", as.searchExhibition(sVO));
		model.addAttribute("startNum",startNum);
		model.addAttribute("endNum",endNum);
		model.addAttribute("startNum",startNum);
		model.addAttribute("startPage",startPage);
		model.addAttribute("pageCnt",pageCnt);
		model.addAttribute("endPage",endPage);
		model.addAttribute("totalCnt",totalCnt);
		model.addAttribute("pageBlock",pageBlock);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("exHallList",as.searchExHall());
		
		return "exhibitions/exhibitionSchedule";
	}//moveExhibition
	
	@ResponseBody
	@RequestMapping(value="/admin/exDetail.do",method=POST, produces="aplicataion/json;charset=UTF-8")
	public String exDetail(String ex_num) {
	
		return as.searchExDetail(ex_num);
	}//exDetail
	
	@ResponseBody
	@RequestMapping(value="/admin/exUpdate.do",method=POST)
	public String updateExhibition(ExhibitionVO eVO) {
		return as.modifyExhibition(eVO);
	}//updateExhibition
	
	@ResponseBody
	@RequestMapping(value="/admin/exAdd.do",method=POST)
	public String addExhibition(ExhibitionVO eVO)throws SQLException{
		int cnt =0;
		//System.out.println("-------------------------------------------------eVO"+eVO);
		try {
		 eVO.setAdd_img(filemanagement.FileUploader(eVO.getMulAdd()));
		eVO.setExhibition_poster(filemanagement.FileUploader(eVO.getMulPoster()));
		eVO.setAdd_img_url(filemanagement.getFileUrl(eVO.getAdd_img()));
		eVO.setExhibition_poster_url(filemanagement.getFileUrl(eVO.getExhibition_poster()));
		cnt=as.addExhibition(eVO);
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		return String.valueOf(cnt);
	}//addExhibition
}//class
