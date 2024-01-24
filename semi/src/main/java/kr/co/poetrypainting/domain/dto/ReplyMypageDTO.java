package kr.co.poetrypainting.domain.dto;

import kr.co.poetrypainting.domain.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : ReplyMypageDTO
 * author         : banghansol
 * date           : 2023/04/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/24        banghansol       최초 생성
 */
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@Alias("replyMypage")
public class ReplyMypageDTO extends ReplyVO {
    private AttachDTO attachDTO;
}
