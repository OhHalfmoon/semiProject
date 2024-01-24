package kr.co.poetrypainting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.time.LocalTime.now;
/**
 * packageName    : kr.co.poetrypainting.domain
 * fileName       : ShopVO
 * author         : 함준혁
 * date           : 2023/04/05
 * description    : 상점 VO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/04/05        함준혁            최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopVO {
    /*
    상점페이지에서 필요한 것들 목록 ( * 되어 있는것들은 이곳에서 구현, 그 외의 것들은 참조 )
    shop_no *
    member_id - member 참조
    shop_name *
    regdate - 조회일 - member_regdate
    visits *
    progress - trade 참조
    addr - address 참조
    reportcnt - cheat 참조
    credit - member 참조
    main_addr - address 참조
    introduction *
     */
    private Long visits; // 상점방문수
    private Long shopNo; // 상점번호
    private String intro; // 상점소개
    private String shopName; // 상점명







}
