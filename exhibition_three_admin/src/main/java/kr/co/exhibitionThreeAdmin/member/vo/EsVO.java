package kr.co.exhibitionThreeAdmin.member.vo;

import org.springframework.web.bind.annotation.RequestParam;

public class EsVO {
	private String field,keyword,manager;

	public EsVO() {
		
	}
	
	public EsVO(String field, String keyword, String manager) {
		this.field = field;
		this.keyword = keyword;
		this.manager = manager;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(@RequestParam(defaultValue = "y")String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "EsVO [field=" + field + ", keyword=" + keyword + ", manager=" + manager + "]";
	}
	
}
