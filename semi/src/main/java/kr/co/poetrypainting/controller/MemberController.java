package kr.co.poetrypainting.controller;


import com.mashape.unirest.http.exceptions.UnirestException;
import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ShopVO;
import kr.co.poetrypainting.domain.dto.*;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.ShopMapper;
import kr.co.poetrypainting.service.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : kr.co.poetrypainting.controller
 * fileName       : MemberController
 * author         : 방한솔
 * date           : 2023/04/06
 * description    : 회원 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/06        방한솔            최초 생성
 * 2023/04/07        방한솔            로그인, 로그아웃 기능 추가
 * 2023/04/13        방한솔            회원가입 추가
 * 2023/04/14        방한솔            이메일 인증 추가
 * 2023/04/21        방한솔            회원 변경 추가
 */
@Controller
@RequestMapping("/member/*")
@Data
@AllArgsConstructor
@Slf4j
public class MemberController {
    private MemberService memberService;
    private EmailAuthService emailAuthService;
    private BoardService boardService;
    private TradeService tradeService;
    private ReplyService replyService;

    private BoardMapper boardMapper;
    private ShopMapper shopMapper;

    /**
     * 로그인 화면 이동
     *
     * @return member /login jsp
     */
    @GetMapping("/login")
    public String loginGet(){

        return "member/login";
    }

    /**
     * 회원가입 화면 이동
     *
     * @param session the session
     * @return member /join jsp
     */
    @GetMapping("/join")
    public String joinGet(HttpSession session){
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if(loginMember != null){
            return "redirect:/";
        }

        return "member/join";
    }

    /**
     * post form 요청으로 로그인한다. (세션부여)
     *
     * @param loginForm 가입폼
     * @param request   the request
     * @param response  the response
     * @return 메인화면으로 리다이렉트
     */
    @PostMapping("/login")
    public String loginPost(LoginDTO loginForm, HttpServletRequest request, HttpServletResponse response){
        log.info("login Post");
        log.info("{}", loginForm);

        MemberVO loginMember = memberService.login(loginForm);
        Cookie idCookie;

        //if(loginForm.getRememberId())

        if(loginMember == null){
            String errorMsg = "아이디가 없거나 비밀번호가 틀렸습니다";
            request.setAttribute("errorMsg", errorMsg);

            return "member/login";
        } else {
            if(loginForm.getRememberId()){
                idCookie = new Cookie("memberId", loginForm.getMemberId());
                // 초 단위, 90일(3개월)
                idCookie.setMaxAge(90 * 24 * 60 * 60);


            } else {
                idCookie = new Cookie("memberId", null);
                idCookie.setMaxAge(0);
            }
            idCookie.setPath("/");
            response.addCookie(idCookie);

            request.getSession().setAttribute("loginMember", loginMember);
        }

        return "redirect:/";
    }

    /**
     * 현재 로그인 한 사용자를 로그아웃 한다.(세션삭제)
     *
     * @param request the request
     * @return 메인화면으로 리다이렉트
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();

        return "redirect:/";
    }

    /**
     * Id check response entity.
     *
     * @param stringMap the string map
     * @return the response entity
     */
    @ResponseBody
    @PostMapping(value = "/idCheck", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> idCheck(@RequestBody Map<String, Object> stringMap){
        String memberId = (String) stringMap.get("memberId");
        log.info("{}", memberId);
        Boolean idCheck  = memberService.idCheck(memberId);
        log.info("{}", idCheck);
/*
        Map<String, Object> map = new HashMap<>();

        map.put("result", idCheck);

        ApiResponse<Map<String, Object>> result = ApiResponse
                .<Map<String, Object>>builder()
                .resultCode("200")
                .resultMsg("중복 조회에 성공하였습니다.")
                .data(map)
                .build();*/

        return ResponseEntity.ok(idCheck);
    }

    /**
     * Nick check response entity.
     *
     * @param stringMap the string map
     * @return the response entity
     */
    @ResponseBody
    @PostMapping(value = "/nickCheck", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> nickCheck(@RequestBody Map<String, Object> stringMap){
        String nickname = (String) stringMap.get("nickname");
        log.info("{}", nickname);

        MemberVO memberVO = new MemberVO();
        memberVO.setNickname(nickname);

        Boolean nickCheck  = memberService.nickCheck(memberVO);
        log.info("{}", nickCheck);

        return ResponseEntity.ok(nickCheck);
    }

    /**
     * Email send response entity.
     *
     * @param stringMap the string map
     * @return the response entity
     */
    @ResponseBody
    @PostMapping(value = "/emailSend", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> emailSend(@RequestBody Map<String, Object> stringMap){
        String email = (String) stringMap.get("email");

        log.info("email : {}", email);
        try {
            emailAuthService.emailSend(email);
        } catch (UnirestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

        return ResponseEntity.ok(true);
    }

    /**
     * Email check response entity.
     *
     * @param stringMap the string map
     * @return the response entity
     */
    @ResponseBody
    @PostMapping(value = "/emailAuthCheck", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> emailCheck(@RequestBody Map<String, Object> stringMap){
        String email = (String) stringMap.get("email");
        String code = (String) stringMap.get("code");
        log.info("{}", email);
        Boolean emailAuthCheck  = memberService.emailAuthCheck(email, code);
        log.info("{}", emailAuthCheck);

        return ResponseEntity.ok(emailAuthCheck);
    }

    /**
     * Join post int.
     *
     * @param joinForm the join form
     * @return the int
     */
    @PostMapping("/join")
    @ResponseBody
    public int joinPost(JoinDTO joinForm){
        log.info("{}", joinForm);

        MemberVO member = new MemberVO();
        member.setMemberId(joinForm.getMemberId());
        member.setPassword(joinForm.getPassword());
        member.setEmail(joinForm.getEmail());
        member.setNickname(joinForm.getNickname());

        AddressVO address = new AddressVO();

        address.setAddr(joinForm.getAddr());
        address.setAddrDetail(joinForm.getAddrDetail());
        address.setX(joinForm.getX());
        address.setY(joinForm.getY());
        address.setDong(joinForm.getDong());
        address.setMainAddr(true);


        int register = memberService.register(member, address);

        //return "redirect:/";
        //return "member/join";

        return register;
    }

    /**
     * Pass check form string.
     *
     * @return the string
     */
    @GetMapping("passCheck")
    public String passCheckForm(){
        return "member/passCheck";
    }

    /**
     * Pass change form string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("passChange")
    public String passChangeForm(HttpSession session, Model model){
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        if(loginMember == null){
            return "redirect:/member/login";
        }

        MemberVO searchMember = memberService.searchMember(loginMember.getMemberNo());

        model.addAttribute("memberNo", searchMember.getMemberNo());

        return "member/passChange";
    }

    /**
     * Pass change string.
     *
     * @param passChangeForm the pass change form
     * @param model          the model
     * @param rttr           the rttr
     * @return the string
     */
    @PostMapping("passChange")
    public String passChange(PassChangeDTO passChangeForm, Model model, RedirectAttributes rttr) {
        log.warn("passChangeForm : {}", passChangeForm);
        String updateMsg;
        // null, 이전 비밀번호 확인

        Map<String, Object> errors = passValidate(passChangeForm);

        if(errors.size() > 0){
            updateMsg = "비밀번호 변경을 실패하였습니다!";
            model.addAttribute("result", false);
            model.addAttribute("updateMsg", updateMsg);
            model.addAttribute("errors", errors);

            return "member/passChange";
        }

        int result = memberService.passwordChange(passChangeForm);

        if(result == -1){
            updateMsg = "비밀번호 변경을 실패하였습니다!";
            errors.put("newPasswordConfirm", "변경할 비밀번호가 일치하지 않습니다!");

            model.addAttribute("result", false);
            model.addAttribute("updateMsg", updateMsg);
            model.addAttribute("errors", errors);

            return "member/passChange";
        }

        updateMsg = "비밀번호를 변경하였습니다!";

        rttr.addFlashAttribute("result", true);
        rttr.addFlashAttribute("updateMsg", updateMsg);

        return "redirect:/member/passChange";
    }

    /**
     * Modify form string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("modify")
    public String modifyForm(HttpSession session, Model model){
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

        if(loginMember == null){
            return "redirect:/member/login";
        }

        MemberVO searchMember = memberService.searchMember(loginMember.getMemberNo());
        MemberModifyDto loginMemberInfo = new MemberModifyDto(searchMember);

        log.warn("loginMemberInfo : {}", loginMemberInfo);

        model.addAttribute("memberInfo", loginMemberInfo);

        return "member/modify";
    }

    /**
     * Modify string.
     *
     * @param memModifyForm the mem modify form
     * @param model         the model
     * @param rttr          the rttr
     * @return the string
     */
    @PostMapping("modify")
    public String modify(MemberModifyDto memModifyForm, Model model, RedirectAttributes rttr){
        log.warn("{}", memModifyForm);
        // validation fail : error 결과 넘기기

        Map<String, Object> errors = validate(memModifyForm);

        String updateMsg;

        if(errors.size() > 0){
            updateMsg = "회원정보를 수정하는데 문제가 발생하였습니다!";

            model.addAttribute("memberInfo", memModifyForm);
            model.addAttribute("result", false);
            model.addAttribute("updateMsg", updateMsg);
            model.addAttribute("errors", errors);

            return "/member/modify";
        }

        int modify = memberService.modify(memModifyForm);
        MemberVO updateMemberInfo = memberService.searchMember(memModifyForm.getMemberNo());

        updateMsg = "회원정보를 수정하였습니다!";


        rttr.addFlashAttribute("result", true);
        rttr.addFlashAttribute("updateMsg", updateMsg);
        // model.addAttribute("memberInfo", updateMemberInfo);
        // model.addAttribute("updateMsg", updateMsg);

        return "redirect:/member/modify";

    }

    /**
     * My page string.
     *
     * @param session the session
     * @param model   the model
     * @return the string
     */
    @GetMapping("mypage")
    public String myPage(HttpSession session, Model model) {
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if(loginMember == null){
            return "redirect:/member/login";
        }

        model.addAttribute("myInfo", memberService.searchMemberMypage(loginMember.getMemberNo()));
        model.addAttribute("pgCount", tradeService.getPgCount(loginMember.getMemberNo()));

        model.addAttribute("myBoardList", boardMapper.getMyList(loginMember.getMemberNo()));
        List<ReplyMypageDTO> replyList = replyService.getListMypage(loginMember.getMemberNo());
        log.warn("{}", replyList);
        model.addAttribute("replyList", replyList);
        model.addAttribute("mytr", tradeService.getList(loginMember.getMemberNo()));
        model.addAttribute("pg", tradeService.getListBySeller(loginMember.getMemberNo()));
        model.addAttribute("rs", tradeService.getListByReserve(loginMember.getMemberNo()));
        model.addAttribute("fi", tradeService.getListByFinish(loginMember.getMemberNo()));
        return "member/mypage";

    }

    /**
     * Validate map.
     *
     * @param memModifyForm the mem modify form
     * @return the map
     */
    public Map<String, Object> validate(MemberVO memModifyForm){
        Map<String, Object> errors = new HashMap<>();
        // Null값 비교
        if(memModifyForm.getNickname() == null || memModifyForm.getNickname().isEmpty()){
            errors.put("nickname", "닉네임을 입력해주세요");
        }

        MemberVO searchMember = memberService.searchMember(memModifyForm.getMemberNo());

        // 중복체크 통과못함 : 중복체크 로직
        // &&
        // 닉네임이 본인꺼
        if(searchMember != null && !memberService.nickCheck(memModifyForm)){
            errors.put("nickname", "이미 존재하는 닉네임입니다!");
        }

        MemberModifyDto memModifyForm1 = (MemberModifyDto) memModifyForm;

        log.warn("{}", memModifyForm1);

        Boolean emailCheck = !memModifyForm1.getNewEmail().isEmpty() && memModifyForm1.getCode() == null;

        if(emailCheck){
            errors.put("email", "이메일 인증을 해주세요!!");
        }

        if(emailCheck && !memberService.emailAuthCheck(memModifyForm1.getNewEmail(), memModifyForm1.getCode())){
            errors.put("code", "인증코드가 일치하지 않습니다!");
        }

        return errors;
    }

    /**
     * Pass validate map.
     *
     * @param passChangeForm the pass change form
     * @return the map
     */
    public Map<String, Object> passValidate(PassChangeDTO passChangeForm){
        Map<String, Object> errors = new HashMap<>();

        if(passChangeForm.getPassword().isEmpty()){
            errors.put("password", "비밀번호를 입력해주세요");
        }

        boolean isNewPass = passChangeForm.getNewPassword().isEmpty();
        if(isNewPass){
            errors.put("newPassword", "새 비밀번호를 입력해주세요");
        }

        boolean isNewPassConfirm = passChangeForm.getNewPassConfirm().isEmpty();
        if(isNewPassConfirm){
            errors.put("newPasswordConfirm", "새 비밀번호를 다시 입력해주세요");
        }

        if(!isNewPass && !isNewPassConfirm && !passChangeForm.getNewPassword().equals(passChangeForm.getNewPassConfirm())){
            errors.put("newPasswordConfirm", "변경할 비밀번호가 일치하지 않습니다!");
        }

        return errors;
    }

    @GetMapping("withdrawal")
    public String withdrawalForm(HttpSession session, Model model){
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if(loginMember == null){
            return "redirect:/member/login";
        }

        model.addAttribute("memberNo", loginMember.getMemberNo());

        return "member/withdrawal";
    }

    @PostMapping("withdrawal")
    public String withdrawal(WithDrawDTO withdrawalForm, HttpSession session){

        MemberVO memberVO = memberService.searchMember(withdrawalForm.getMemberNo());
        withdrawalForm.setMemberId(memberVO.getMemberId());

        log.warn("{}", withdrawalForm);
        memberService.withdraw(withdrawalForm);

        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("shopName")
    public String updateShopName(ShopVO shopNameForm, HttpSession session, Model model, RedirectAttributes rttr){
        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if(loginMember == null){
            return "redirect:/member/login";
        }

        if(shopNameForm.getShopName().isEmpty()){
            model.addAttribute("result", false);
            model.addAttribute("updateMsg", "상점명을 입력해주세요!");

            model.addAttribute("myInfo", memberService.searchMemberMypage(loginMember.getMemberNo()));
            model.addAttribute("pgCount", tradeService.getPgCount(loginMember.getMemberNo()));

            model.addAttribute("myBoardList", boardMapper.getMyList(loginMember.getMemberNo()));
            model.addAttribute("replyList", replyService.getListMypage(loginMember.getMemberNo()));

            model.addAttribute("mytr", tradeService.getList(loginMember.getMemberNo()));
            model.addAttribute("pg", tradeService.getListBySeller(loginMember.getMemberNo()));
            model.addAttribute("rs", tradeService.getListByReserve(loginMember.getMemberNo()));
            model.addAttribute("fi", tradeService.getListByFinish(loginMember.getMemberNo()));

            return "/member/mypage";
        }

        shopMapper.updateName(shopNameForm.getShopName(), shopNameForm.getShopNo());

        rttr.addFlashAttribute("result", true);
        rttr.addFlashAttribute("updateMsg", "상점명을 수정하였습니다!");

        return "redirect:/member/mypage";
    }

    @PostMapping("shopIntro")
    public String updateShopIntro(ShopVO shopIntroForm, HttpSession session, Model model, RedirectAttributes rttr){

        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if(loginMember == null){
            return "redirect:/member/login";
        }

        if(shopIntroForm.getIntro().isEmpty()){
            model.addAttribute("result", false);
            model.addAttribute("updateMsg", "상점 소개글 내용을 입력해주세요!");

            model.addAttribute("myInfo", memberService.searchMemberMypage(loginMember.getMemberNo()));
            model.addAttribute("pgCount", tradeService.getPgCount(loginMember.getMemberNo()));

            model.addAttribute("myBoardList", boardMapper.getMyList(loginMember.getMemberNo()));
            model.addAttribute("replyList", replyService.getListMypage(loginMember.getMemberNo()));

            model.addAttribute("mytr", tradeService.getList(loginMember.getMemberNo()));
            model.addAttribute("pg", tradeService.getListBySeller(loginMember.getMemberNo()));
            model.addAttribute("rs", tradeService.getListByReserve(loginMember.getMemberNo()));
            model.addAttribute("fi", tradeService.getListByFinish(loginMember.getMemberNo()));

            return "/member/mypage";
        }

        shopMapper.updateIntro(shopIntroForm.getIntro(), shopIntroForm.getShopNo());

        rttr.addFlashAttribute("result", true);
        rttr.addFlashAttribute("updateMsg", "상점 소개글을 수정하였습니다!");

        return "redirect:/member/mypage";
    }

    @GetMapping("terms")
    public String terms(){
        return "/member/terms";
    }
}
