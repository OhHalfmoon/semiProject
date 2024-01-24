package kr.co.poetrypainting.domain.dto;

import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 12;
	
	private String keyword;
	
	
	public String getQueryString(){
		return UriComponentsBuilder.fromPath("")
//				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("keyword", keyword)
				.build().toUriString();
	}
	public String getFullQueryString(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("keyword", keyword)
				.build().toUriString();
	}
	

	public Criteria(int pageNum, int amount){
		this.amount = amount;
		this.pageNum = pageNum;
	}
	public int getOffset(){
		int offset = (pageNum - 1) * amount; 
		return offset;
		
	}
	
}
