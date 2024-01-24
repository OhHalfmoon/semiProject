package kr.co.poetrypainting.controller;

import com.google.gson.Gson;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.dto.LoginDTO;
import kr.co.poetrypainting.service.MemberService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * packageName    : kr.co.poetrypainting.controller
 * fileName       : MemberControllerTests
 * author         : 방한솔
 * date           : 2023/04/08
 * description    : Member 컨트롤러 테스트코드
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/08        방한솔            최초 생성
 * 2023/04/09        방한솔            아이디 중복체크 추가
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
@Log4j
public class MemberControllerTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    /**
     * MockMvc 초기화
     */
    @Before
    public void mockInit() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    /**
     * Bean 주입 테스트
     */
    @Test
    public void existTests() {
        assertNotNull(memberService);
        assertNotNull(context);
        assertNotNull(mockMvc);
    }

    /**
     * 로그인 테스트
     *
     * @throws Exception the exception
     */
    @Test
    public void loginTests() throws Exception {
        LoginDTO dto = LoginDTO.builder()
                .memberId("test3")
                .password("1234")
                .rememberId(false)
                .build();

        MockHttpServletRequestBuilder mvcRequest = MockMvcRequestBuilders
                .post("/member/login")
                .param("memberId", dto.getMemberId())
                .param("password", dto.getPassword())
                .param("rememberId", dto.getRememberId().toString());

        MvcResult mvcResult = mockMvc.perform(mvcRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andReturn();

        MemberVO loginMember = (MemberVO) mvcResult
                .getRequest()
                .getSession()
                .getAttribute("loginMember");


        assertNotNull(loginMember);

    }

    /**
     * mockSession 테스트
     *
     * @throws Exception the exception
     */
    @Test
    public void sessionTest() throws Exception {
        MockHttpServletRequestBuilder mvcRequest = MockMvcRequestBuilders
                .get("/");

        MvcResult mvcResult = mockMvc.perform(mvcRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * idCheck Test 추가
     *
     * @throws Exception the exception
     */
    @Test
    public void idCheckTest() throws Exception {
        String memberId = "test3";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(map);

        log.info(jsonStr);

        MockHttpServletRequestBuilder mvcRequest = MockMvcRequestBuilders
                .post("/member/idCheck")
                .content(jsonStr)
                .contentType(MediaType.APPLICATION_JSON_VALUE);


        mockMvc.perform(mvcRequest)
                .andDo(print())
                .andExpect(status().isOk());

    }
}