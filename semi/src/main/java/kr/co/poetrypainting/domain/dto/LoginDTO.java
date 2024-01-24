package kr.co.poetrypainting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : LoginDTO
 * author         : banghansol
 * date           : 2023/04/07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/07        banghansol       최초 생성
 */
@Data
@Builder
@AllArgsConstructor
public class LoginDTO {
    /**
     * 회원Id
     */
    private String memberId;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 아이디 저장 여부
     */
    private Boolean rememberId;
}
