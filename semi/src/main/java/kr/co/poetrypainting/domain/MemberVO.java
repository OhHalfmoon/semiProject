package kr.co.poetrypainting.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : MemberVO
 * author         : 방한솔
 * date           : 2023/04/05
 * description    : 회원 VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/05        방한솔            최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Alias("memberVO")
public class MemberVO {
    /**
     * 회원번호
     */
    private Long memberNo;

    /**
     * 회원Id
     */
    private String memberId;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 이메일
     */
    private String email;

    /**
     * 닉네임
     */
    private String nickname;

    /**
     * 등록날짜
     */
    private Date regDate;

    /**
     * 수정날짜
     */
    private Date updateDate;

    /**
     * 신용점수
     */
    private int credit;

    /**
     * 관리자 권한 여부
     */
    private Boolean admin;
}
