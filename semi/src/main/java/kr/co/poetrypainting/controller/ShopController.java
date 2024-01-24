package kr.co.poetrypainting.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.poetrypainting.domain.*;
import kr.co.poetrypainting.mapper.*;
import kr.co.poetrypainting.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.poetrypainting.domain.dto.Criteria;
import kr.co.poetrypainting.domain.dto.PageDto;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * @author : 함준혁
 * @packageName : kr.co.poetrypainting.controller
 * @fileName : ShopController.java
 * @date : 2023.04.19
 * @description : 상점페이지 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023.04.19        함준혁       최초 생성
 */
@Controller
@Log4j
@RequestMapping("shop")
@Data
@AllArgsConstructor
public class ShopController {
    /*
    세미프로젝트 상점페이지
    상점페이지 접속 방법
    1. index -> 내상점
    2. index -> 상품사진에서 상점 보러가기
    3. http://localhost/board/get?bno=4 에서 게시글에 있는 nickname
    4. http://localhost/board/get?bno=4 에서 후기에 있는 nickname
    :::::::::::::::::::::::: To do list ::::::::::::::::::::::::
    - 1번방법으로 이동 시 session의 memberNo 받아오기
    - 2번방법으로 이동 시 게시글 작성자의 memberNo 받아오기
    - 3번방법으로  이동 시 게시글 작성자의 memberNo 받아오기
    - 4번방법으로 이동 시 후기 작성자의 memberNo 받아오기

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
     */
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ReplyMapper replyMapper;

    private TradeService tradeService;

    @GetMapping("")
    public String isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("loginMember") == null) {
            return "redirect:/member/login"; // 로그인되지 않은 경우 로그인 페이지로 redirect
        }
        return "";
    }

    @GetMapping("/{memberNo}")
    public String shop(HttpSession session, Model model, @PathVariable Long memberNo, Criteria cri) {

        // 로그인 상태일 경우

        MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
        if (loginMember != null) {
            if (!Objects.equals(loginMember.getMemberNo(), memberNo)) {
                shopMapper.updateVisitCnt(memberNo); // 접속 시 방문수 상승
            }
        }else{
            shopMapper.updateVisitCnt(memberNo);
        }
        MemberVO memberVO = memberMapper.get(memberNo); // 회원 정보 가져오기

        if (memberVO == null) {
            return "redirect:/index"; // 없는회원의 상점페이지
        }
        if (shopMapper.get(memberNo) == null) { //상점 접속시 최초 접속일 경우
            shopMapper.insertShop(memberNo, memberVO.getNickname()); // 최초 접속일경우 상점페이지를 생성한다.
        }

        ShopVO shopVO = shopMapper.get(memberNo); // 상점 정보 가져오기

        List<Map<String, Object>> myBoardList = boardMapper.getMyList(memberNo); // 나의 게시글 목록 가져오기
        List<AddressVO> addressVOList = addressMapper.getMyList(memberNo); // 회원의 주소 목록 가져오기

        AddressVO addressVO = addressVOList.get(0); // 회원의 첫번째 주소 가져오기

        List<ReplyVO> replyVOList = new ArrayList<>(); // 후기를 담을 List를 선언 및 초기화 합니다.
        List<BoardVo> replyBoardList = new ArrayList<>();
        for (int i = 0; i < myBoardList.size() - 1; i++) {
            BoardVo boardVo = (BoardVo) myBoardList.get(i); // 상점페이지 소유자의 게시글 목록을 가져옵니다

            Long bno = boardVo.getBno(); // 상점페이지 소유자의 게시글의 글번호를 가져옵니다

            ReplyVO replyVO = replyMapper.readBno(bno); // 상점페이지 소유자의 게시글의 글 번호를 통해 후기를 가져옵니다.

            if (replyVO != null && replyVO.getMemberNo() != null) {
                Long replyMemberNo = replyVO.getMemberNo(); // 가져온 후기에서 회원번호를 가져옵니다.

                MemberVO replyMemberVO = memberMapper.get(replyMemberNo); // readBno에서 가져오지 못하는 nickName, Dong을 가져오기 위해 후기 회원번호를 통해 MemberVO를 가져옵니다.

                replyVO.setNickname(replyMemberVO.getNickname()); // 후기 회원정보를 통해 닉네임을 가져와 replyVO에 넣어줍니다.

                replyVO.setDong(addressMapper.getMyList(replyMemberNo).get(0).getDong()); // 후기 회원정보를 통해 '동'을 가져와 replyVO에 넣어줍니다.

                if (Objects.equals(boardVo.getBno(), replyVO.getBno())) { //상점페이지 소유자의 게시글번호와, 후기작성자의 게시글 번호가 일치 할 시
                    replyBoardList.add(boardMapper.read(replyVO.getBno())); // 후기가 가지고 있는 글번호를 통하여 가져온 게시글을 넣어줍니다.
                }

                replyVOList.add(replyVO); // 설정된 replyVO를 List에 넣어줍니다.
            }
        }

        long regTime = memberVO.getRegDate().getTime();
        long now = System.currentTimeMillis();
        long openDays = (now - regTime) / (24 * 60 * 60 * 1000);

        model.addAttribute("list", myBoardList);
        model.addAttribute("page", new PageDto(boardMapper.getTotalCnt(), cri));
        model.addAttribute("memberVO", memberVO);
        model.addAttribute("addressVO", addressVO);
        model.addAttribute("replyVOList", replyVOList);
        model.addAttribute("shopVO", shopVO);
        model.addAttribute("replyBoardList", replyBoardList);
        model.addAttribute("openDays", openDays);
        model.addAttribute("pgCount", tradeService.getPgCount(memberNo));

        return "shop";
    }

}
