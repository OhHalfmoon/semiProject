package kr.co.poetrypainting.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * \
 * @author 오상현
 * @Since 2023.04.05 
 * AddressVO최초생성
 * = 생성목록
 * - 주소번호, 회원번호, 주소, 세부주소, 위도, 경도, 등록일시, 업데이트일시, 대표주소
 * 
 * @Since 2023.04.06 
 * Alias 변경 : addr -> address
 * 
 * @Since 2023.04.12 
 * 동주소 추가
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("address")
public class AddressVO {
	private Long addrNo; //주소번호
	private Long memberNo; //회원번호
	private String addr; // 주소 (시,군,구,동,면 또는 도로명주소)
	private String addrDetail;  //세부주소 (건물명)
	private Double y; // 위도
	private Double x; // 경도
	private Date regdate; // 등록일시
	private Date updateDate; // 업데이트 일시
	private boolean mainAddr; // 대표주소
	private String dong; // 동주소\
	private Double distance; // 거리
}
