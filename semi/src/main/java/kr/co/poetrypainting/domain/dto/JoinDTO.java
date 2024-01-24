package kr.co.poetrypainting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : JoinDTO
 * author         : banghansol
 * date           : 2023/04/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/10        banghansol       최초 생성
 */
@Data
@Builder
@AllArgsConstructor
public class JoinDTO
{
    private String memberId;
    private String nickname;
    private String password;
    private String confirmPassword;
    private String email;
    private String addr;
    private String addrDetail;
    private double x;
    private double y;
    private String dong;
}
