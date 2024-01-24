package kr.co.poetrypainting.domain.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.ToString;
@Alias("category")
@Data
public class CategoryDTO {
	private int cateNo;
	private String cateName;
	private Date regdate;
	private Date updateDate;
}
