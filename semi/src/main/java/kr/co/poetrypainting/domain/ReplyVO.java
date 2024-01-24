package kr.co.poetrypainting.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import lombok.Data;
/**
 * \
 * @author 오상현
 * @Since 2023.04.05 ReplyVO최초생성
 * = 생성목록
 * - 후기번호, 후기작성자 회원번호, 후기작성대상 물품번호, 후기내용, 후기작성날짜, 후기수정날짜
 *
 * @Since 2023.04.05 ReplyVO최초생성
 * = 평점, 동주소, 닉네임 추가
 *  
 */

@Data
@Alias("reply")
public class ReplyVO {
	private Long rno; //후기번호
	private Long memberNo; //후기작성자 회원번호
	private Long bno; //후기 작성 대상 물품번호
	
	private String comment; //후기 내용
	private Date regdate; // 후기 작성날짜
	private Date updatedate; // 후기 수정 날짜
	private Integer rating;
	private String dong;
	private String nickname;
	private Long progress;
	
	private String title;
	private Long seller;

	private int price;
}
