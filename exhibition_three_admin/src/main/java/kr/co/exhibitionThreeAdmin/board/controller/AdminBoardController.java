package kr.co.exhibitionThreeAdmin.board.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.exhibitionThreeAdmin.additional.vo.LoginVO;
import kr.co.exhibitionThreeAdmin.board.domain.AdminBoardDomain;
import kr.co.exhibitionThreeAdmin.board.service.AdminBoardServie;
import kr.co.exhibitionThreeAdmin.board.vo.AdminBoardVO;
import kr.co.exhibitionThreeAdmin.search.vo.BHSearchVO;

@Controller
public class AdminBoardController {
	
	@Autowired(required =  false)
	private AdminBoardServie abs;
	
	//게시판 조회
	@RequestMapping(value = "/admin/board.do", method = GET)
	public String board(Model model, BHSearchVO sVO) throws Exception {
		//전체 레코드의 수
		int totalCnt = abs.countData(sVO);
		//한 화면에 보여줄 게시물의 수
		int pageScale = abs.pageScale();
		//현재 페이지
		int currentPage=sVO.getCurrentPage();
		//페이지의 수
		int pageCnt = abs.pageCnt(sVO);
		//시작 번호
		int startNum = abs.startNum(currentPage, pageScale);
		//끝 번호 
		int endNum = abs.endNum(startNum, pageScale);
		//페이지블럭 시작 번호
		int startPage = abs.startPage(currentPage, pageScale); 
		//페이지 블럭 끝 번호
		int endPage = abs.endPage(startPage, pageScale,sVO);
		//이전 페이지 존재 유무
		boolean prev = abs.prev(currentPage, pageScale);
		//다음 페이지 존재 유무
		boolean next = abs.next(sVO, startPage, pageScale);
		//이전 페이지 시작 번호
		int prevNum = abs.prevNum(currentPage, pageScale);
		//다음페이지 시작번호
		int nextNum = abs.nextNum(currentPage, pageScale);

		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageScale", pageScale);
		model.addAttribute("pageCnt", pageCnt);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("prevNum", prevNum);
		model.addAttribute("nextNum", nextNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRows", abs.countData(sVO));
		model.addAttribute("boardList", abs.boardList(sVO));
		
		//카테고리 리스트
		List<AdminBoardDomain> list= abs.categoryList();
		model.addAttribute("categoryList", list );
		return "board/board";
	}
	
	
	//게시글 추가 폼
	@RequestMapping(value = "/admin/formBoardAdd.do", method = GET)
	public String formAddPost(HttpSession session, Model model) throws Exception {
		List<AdminBoardDomain> list= abs.categoryList();
		String msg ="no error";
		
		LoginVO lvo = new LoginVO();
		lvo.setAdmin_id((String) session.getAttribute("id"));
		
		model.addAttribute("id", lvo.getAdmin_id());
		model.addAttribute("categoryList", list );
		model.addAttribute("msg", msg);
		return "board/addBoard";
	}
	
	//게시글 추가 
	@RequestMapping(value = "/admin/boardAdd.do", method = POST)
	public String addPost(AdminBoardVO abVO, HttpServletRequest request, HttpSession session, Model model)
			throws Exception{
		
		String url="redirect:board.do";
		String msg ="no error";
		LoginVO lvo = new LoginVO();
		lvo.setAdmin_id((String) session.getAttribute("id"));
		
		abVO.setAdminid(lvo.getAdmin_id());
		abVO.setUserid("admin");
		abVO.setDescription(abVO.getDescription().replace("<p>", ""));
		abVO.setDescription(abVO.getDescription().replace("</p>", ""));
		abVO.setCat_num(abVO.getCat_num());
		abVO.setTitle(abVO.getTitle());
		abVO.setImg_file(abVO.getImg_file());
		
		if(!abs.addBoard(abVO)) {
			url = "board/addBoard";
			msg = "게시글을 업로드하지 못하였습니다. 잠시 후 다시 시도해주세요.";
		}
		model.addAttribute("msg", msg);
		
		return url;
	}
	
	//게시글 상세
	@RequestMapping(value = "/admin/boardDetail.do", method = GET, produces = "applicaion/json; charset=UTF-8")
	@ResponseBody
	public String boardDetail(int bdId, HttpServletRequest request, Model model) throws Exception{
		//클릭된 게시글 번호
		bdId = Integer.parseInt(request.getParameter("bdId")) ;
		//게시판 상세
		AdminBoardDomain abDomain = new AdminBoardDomain();
		abDomain = abs.boardDetail(bdId);
		//게시글 상세 jsonObj 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bdId", abDomain.getBd_id());
		jsonObj.put("inputDate",new SimpleDateFormat("yyyy-MM-dd").format( abDomain.getInput_date()));
		jsonObj.put("title", abDomain.getTitle());
		jsonObj.put("userId", abDomain.getUserid());
		jsonObj.put("adminId", abDomain.getAdminid());
		jsonObj.put("catNum", abDomain.getCat_num());
		jsonObj.put("catName", abDomain.getCat_name());
		jsonObj.put("description", abDomain.getDescription());
		jsonObj.put("imgFile", abDomain.getImg_file());
		
		return jsonObj.toJSONString();
	}
	
	@RequestMapping(value = "/admin/postRemove.do", method = POST, produces = "applicaion/json; charset=UTF-8")
	@ResponseBody
	public String removePost(int bdId, HttpServletRequest request)throws Exception {
		JSONObject jsonObj = null;
		
		try{
			bdId = Integer.parseInt(request.getParameter("bdId")) ;
			jsonObj = new JSONObject();
			jsonObj.put("flag", abs.removeBoard(bdId));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
			return jsonObj.toJSONString();
		}
	
	@RequestMapping(value = "/admin/boardModify.do", method =POST, produces = "applicaion/jso; charset=UTF-8")
	@ResponseBody
	public String modifyBoard(AdminBoardVO abVO, HttpServletRequest request)throws Exception {
		boolean flag=false;
		
		int catNum =Integer.parseInt(request.getParameter("catNum"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		int bdId = Integer.parseInt(request.getParameter("bdId"));
		
		JSONObject jsonObj = null;
		
		try{
			abVO.setCat_num(catNum);
			abVO.setTitle(title);
			abVO.setDescription(description);
			abVO.setBd_id(bdId);
			
			jsonObj = new JSONObject();
			jsonObj.put("flag", abs.modifyBoard(abVO));
			
		}catch(NumberFormatException e){
			e.printStackTrace();
		} 
			return jsonObj.toJSONString();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionMethod(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:board.do"); 
		mav.addObject("error", "정상적으로 처리되지 않았습니다."); 

		return mav; 
	}
	
}
