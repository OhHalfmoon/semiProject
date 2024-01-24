package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.EmailAuthVO;

/**
 * packageName    : kr.co.poetrypainting.mapper
 * fileName       : EmailAuthMapper
 * author         : 방한솔
 * date           : 2023/04/13
 * description    : 이메일 인증 Mapper
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        banghansol       최초 생성
 */
public interface EmailAuthMapper {
    void insert(EmailAuthVO vo);

    EmailAuthVO getRecentOne(String email);
}
