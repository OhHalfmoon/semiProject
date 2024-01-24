package kr.co.poetrypainting.domain.dto;

import kr.co.poetrypainting.domain.MemberVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : PassChangeDTO
 * author         : banghansol
 * date           : 2023/04/21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/21        banghansol       최초 생성
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("passChange")
public class PassChangeDTO extends MemberVO {
    private String newPassword;
    private String newPassConfirm;
}
