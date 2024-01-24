package kr.co.poetrypainting.mapper;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.ShopVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 함준혁
 * ShopMapper
 * @since 2023-04-05
 */
public interface ShopMapper {

    ShopVO get(@Param("memberNo")Long memberNo); // ShopVO 정보 가져오기
    int insertShop(@Param("memberNo")Long memberNo, @Param("nickname")String nickname);
    int updateName(@Param("shopName")String shopName, @Param("memberNo")Long memberNo); //상점명 수정
    int updateIntro(@Param("intro")String intro, @Param("memberNo")Long memberNo); //상점소개 수정

    List<BoardVo> getBoardList(@Param("memberNo")Long memberNo); // 회원번호를 통하여 가지고 있는 게시글 목록 가져오기

//    int getSellCnt();

    int updateVisitCnt(@Param("memberNo")Long memberNo);


}
