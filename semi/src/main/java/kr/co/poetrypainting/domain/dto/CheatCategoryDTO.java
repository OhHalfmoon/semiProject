package kr.co.poetrypainting.domain.dto;

import lombok.Data;

@Data
public class CheatCategoryDTO {
	  private Long cateNo; // 카테고리번호
	  private String chReport; //카테고리분류 (1.판매금지 물품, 2.전문판매업자, 3.사기글, 4.기타)
}
