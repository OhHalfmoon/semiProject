package kr.co.poetrypainting.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.co.poetrypainting.domain.dto.AttachDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 이영미
 * 2023-04-05 board vo 최초 생성
 * = 생성목록
 * - 글번호, 작성자 회원번호, 내용, 제목, 작성 날짜, 수정 날짜, 끌올 날짜, 비공개 여부, 카테고리 번호, 가격
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("board")
public class BoardVo {
	private Long bno; // 글번호
	private Long memberNo; // 작성자 회원번호

	private String content; // 내용
	private String title; // 제목
	private Date regdate; // 작성 날짜
	private Date updateDate; // 수정 날짜
	private Date uptime; // 끌올 날짜
	private boolean blind; // 비공개 여부
	private int cateNo; // 카테고리 번호
	private Long price; // 가격
	private Long addrNo;//현재 글로 설정된 주소번호
	
	
	private String cateName;
	private String memberName;
	private String addrName;
	
	private List<AttachDTO> attachs;
	
	public Date getUptimeWeek(Date uptime){
		Calendar cal = Calendar.getInstance();
		cal.setTime(uptime);
		Date uptimeWeek = new Date(cal.getTimeInMillis() + (60*60*24*1000*7));
		
		return uptimeWeek;
	}
}
