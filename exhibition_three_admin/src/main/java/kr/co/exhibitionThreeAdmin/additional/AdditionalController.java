package kr.co.exhibitionThreeAdmin.additional;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 추가적인 페이지
 */
@Controller
public class AdditionalController {
	
	@RequestMapping(value = "/admin/index.do", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/admin/settings.do", method = RequestMethod.GET)
	public String settings(Model model) {
		return "commons/settings";
	}
	
	
	
}
