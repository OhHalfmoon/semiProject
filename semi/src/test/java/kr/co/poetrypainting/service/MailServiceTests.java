package kr.co.poetrypainting.service;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotEquals;


/**
 * packageName    : kr.co.poetrypainting.service
 * fileName       : MailServiceTests
 * author         : 방한솔
 * date           : 2023/04/11
 * description    : 방한솔
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/11        banghansol       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MailServiceTests {
    @Value("${mail.domain}")
    private String DOMAIN;

    @Value("${mail.apikey}")
    private String PRIVATE_API_KEY;

    @Value("${mail.domainName}")
    private String DOMAIN_NAME;

    @Autowired
    private EmailAuthService emailAuthService;


    @Test
    public void propertiesTest(){

        log.info(DOMAIN);
        log.info(PRIVATE_API_KEY);
        log.info(DOMAIN_NAME);
    }

    @Test
    public void mainTest() throws UnirestException {
        String USER_EMAIL = "sangoh7395@gmail.com";
        String SUBJECT = "상추마켓 이메일 인증 메일입니다!";
        String TEXT = "회원 인증번호는 <b>" + emailAuthService.createAuthCode() + "</b> 입니다!<br/><br/>감사합니다!";


        HttpResponse<JsonNode> request = Unirest
                .post("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
			    .basicAuth("api", PRIVATE_API_KEY)
                //.queryString("from", "Excited User hsnachos@gmail.com")
                .queryString("from", DOMAIN_NAME)
                .queryString("to", USER_EMAIL)
                .queryString("subject", SUBJECT)
                //.queryString("text", TEXT)
                .queryString("html", TEXT)
                .asJson();

        log.info(request.getStatus());

        log.info(request.getBody());


       // DomainListResponse domainListResponse = mailgunDomainsApi.getDomainsList();

        //domainListResponse.getItems().forEach(log::info);

        /*
        Message message = Message.builder()
                .from(DOMAIN_NAME)
                .to(USER_EMAIL)
                .subject(SUBJECT)
                .text(TEXT)
                .build();

         MailgunDomainsApi mailgunDomainsApi = MailgunClient.config(PRIVATE_API_KEY).createApi(MailgunDomainsApi.class);

        MailgunMessagesApi mailgunMessagesApi =  MailgunClient.config(PRIVATE_API_KEY)
                .logLevel(Logger.Level.NONE)
                .retryer(new Retryer.Default())
                .logger(new Logger.NoOpLogger())
                .errorDecoder(new ErrorDecoder.Default())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .createApi(MailgunMessagesApi.class);

        MessageResponse messageResponse = mailgunMessagesApi.sendMessage(DOMAIN, message);

        log.info(messageResponse);
        */
    }

    @Test
    public void testRandomValueGenerated(){
        int v = (int) (Math.random() * 1000000);

        String rngValue = String.format("%06d", v);

        log.info("회원 인증번호는 " + rngValue + "입니다!");
    }

    @Test
    public void testEmailSend() throws UnirestException {
        String email = "sangoh7395@gmail.com";

        emailAuthService.emailSend(email);

        assertNotEquals((long)emailAuthService.emailSend(email), 0L);
    }


}
