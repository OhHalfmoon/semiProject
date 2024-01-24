package kr.co.poetrypainting.domain;

import java.util.Date;
import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




/**
 * \
 * @author 오상현
 * @Since 2023.04.05 SubscriptionVO최초생성
 * = 생성목록
 * - 구독번호, 회원번호 (구매자, 판매자), 등록일시, 업데이트일시
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("subs")
public class SubscriptionVO {
	private Long subNo; //구독번호 (자동생성 idx번호)
	private Long buyerNo; // 회원번호 (구매자)
	private Long sellerNo; // 회원번호 (판매자)
	private Date regdate; // 등록일시
	private Date updatedate; // 업데이트 일시
}
