package kr.co.poetrypainting.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * packageName    : kr.co.poetrypainting.domain.dto
 * fileName       : ApiResponse
 * author         : banghansol
 * date           : 2023/04/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/09        banghansol       최초 생성
 */
@Data
@Builder
public class ApiResponse<T> {
    private String resultCode;
    private String resultMsg;
    private T data;
}
