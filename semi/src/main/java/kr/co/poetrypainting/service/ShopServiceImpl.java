package kr.co.poetrypainting.service;

import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.domain.MemberVO;
import kr.co.poetrypainting.domain.ShopVO;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.ShopMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ShopServiceImpl 클래스
 *
 * @author 함준혁
 * @since 2023-04-05
 */
@Service // Spring에서 Bean으로 등록됨
@AllArgsConstructor // 모든 필드에 대한 생성자를 생성하는 Lombok 어노테이션
public class ShopServiceImpl implements ShopService {

    private ShopMapper shopMapper; // ShopMapper 객체를 주입 받음

    @Override
    public int insertShop(Long memberNo, String nickname) {
        return shopMapper.insertShop(memberNo, nickname);
    }

    @Override
    public List<BoardVo> getList(Long memberNo) {
        return shopMapper.getBoardList(memberNo);
    }

    /**
     * @author 함준혁
     * 상점 이름을 수정하는 메서드
     *
     */
    @Override
    public int modifyName(String shopName, Long memberNo) {
        return shopMapper.updateName(shopName, memberNo); // ShopMapper 인터페이스의 updateName() 메서드 호출하여 데이터베이스에서 상점 이름을 업데이트하고 업데이트된 레코드 수를 반환함
    }

    /**
     * @author 함준혁
     * 상점 소개를 수정하는 메서드
     *
     */
    @Override
    public int modifyIntro(String intro, Long memberNo) {
        return shopMapper.updateIntro(intro, memberNo); // ShopMapper 인터페이스의 updateIntro() 메서드 호출하여 데이터베이스에서 상점 소개를 업데이트하고 업데이트된 레코드 수를 반환함
    }

//    @Override
//    public int sellCnt() {
//        return shopMapper.up();
//    }
    @Override
    public int updateVisitCnt(Long memberNo) {
        return shopMapper.updateVisitCnt(memberNo);
    }
}
