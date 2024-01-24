package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ShopVO;

import java.util.List;

/**
 * @author 함준혁
 * 상점페이지 서비스
 * @since 2023-04-05
 */
public interface ShopService {

    int insertShop(Long memberNo, String nickname);
    List<BoardVo> getList(Long memberNo); // 회원번호를 통하여 가지고 있는 게시글 목록 가져오기

    int modifyName(String shopName, Long memberNo); // 상점명 수정
    int modifyIntro(String intro, Long memberNo); // 상점소개 수정

//    int sellCnt(); // 회원 번호를 통하여 가지고 있는 게시글 목록중 판매완료 게시글 수 계산

    int updateVisitCnt(Long memberNo); // 상점 방문횟수 증가


}
