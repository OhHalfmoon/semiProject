package kr.co.poetrypainting.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import kr.co.poetrypainting.domain.EmailAuthVO;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : EmailAuthService
 * author         : 방한솔
 * date           : 2023/04/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        방한솔            최초 생성
 */
public interface EmailAuthService {
    Long emailSend(String email) throws UnirestException;

    boolean codeCheck(EmailAuthVO inputAuth);

    String createAuthCode();
}
