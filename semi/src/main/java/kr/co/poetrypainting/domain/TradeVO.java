package kr.co.poetrypainting.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : TradeVO
 * author         :	오상현
 * date           : 2023/04/13
 * description    : TradeVO 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        오상현           거래 VO 생성
 * 
 * 
 */
@Data
@Alias("trade")
/*@AllArgsConstructor
@NoArgsConstructor*/
public class TradeVO {
	//거래 인덱스
	private Long tradeNo;
	//요청자(구매자)
	private Long memberNo;
	//대상 물품번호
	private Long bno;
	// 거래과정 (판매중, 예약중, 판매완료)
	private Long progress;
	private Date regdate;
	private Date updatedate;
	private String pgName;
	
	private Long seller;
	private Long buyer;
	private String nickname;
	private String title;
	
	
}
