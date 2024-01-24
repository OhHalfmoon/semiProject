package kr.co.poetrypainting.domain.dto;

import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ShopVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : MypageDTO
 * author         : banghansol
 * date           : 2023/04/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/24        banghansol       최초 생성
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@Alias("myPage")
public class MypageDTO extends MemberVO {
    private ShopVO shopVO;
    private int openDate;
}
