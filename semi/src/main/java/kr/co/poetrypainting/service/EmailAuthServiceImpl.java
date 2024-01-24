package kr.co.poetrypainting.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import kr.co.poetrypainting.domain.EmailAuthVO;
import kr.co.poetrypainting.mapper.EmailAuthMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : AuthEmailServiceImpl
 * author         : 방한솔
 * date           : 2023/04/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/13        방한솔            최초 생성
 */
@Service
@Log4j
public class EmailAuthServiceImpl implements EmailAuthService {
    @Autowired
    private EmailAuthMapper mapper;

    @Value("${mail.domain}")
    private String DOMAIN;

    @Value("${mail.apikey}")
    private String PRIVATE_API_KEY;

    @Value("${mail.domainName}")
    private String DOMAIN_NAME;

    @Override
    @Transactional
    public Long emailSend(String email) {

        EmailAuthVO inputAuth = new EmailAuthVO();
        inputAuth.setEmail(email);
        inputAuth.setCode(createAuthCode());

        mapper.insert(inputAuth);


        try {
            if(sendEmail(inputAuth) != 200){
                throw new RuntimeException("이메일 전송에 문제가 발생하였습니다!");
            }
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        if(inputAuth.getAuthNo() != null){
            return inputAuth.getAuthNo();
        } else {
            return 0L;
        }
    }

    @Override
    public boolean codeCheck(EmailAuthVO inputAuth) {
        EmailAuthVO recentOne = mapper.getRecentOne(inputAuth.getEmail());

        if(recentOne == null){
            return false;
        }

        return recentOne.getCode().equals(inputAuth.getCode());
    }

    public int sendEmail(EmailAuthVO inputAuth) throws UnirestException {
        String SUBJECT = "상추마켓 이메일 인증 메일입니다!";
        String TEXT = "회원 인증번호는 <b>" + inputAuth.getCode() + "</b> 입니다!<br/><br/>감사합니다!";


        HttpResponse<JsonNode> request = Unirest
                .post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .basicAuth("api", PRIVATE_API_KEY)
                .queryString("from", DOMAIN_NAME)
                .queryString("to", inputAuth.getEmail())
                .queryString("subject", SUBJECT)
                .queryString("html", TEXT)
                .asJson();

        JsonNode body = request.getBody();
        body.getObject();
        return request.getStatus();
    }

    public String createAuthCode(){
        int v = (int) (Math.random() * 1000000);

        String rngValue = String.format("%06d", v);

        log.info("회원 인증번호는 " + rngValue + "입니다!");
        return rngValue;
    }
}
