package kr.co.poetrypainting.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 함준혁
 * 크롤매퍼
 * @since 2023-04-07
 */
@Mapper
public interface CrawlMapper {

    // 상품 정보 등록
    @Insert("Insert into crawl values (#{title}, #{category}, #{content}, #{regDate}, #{price}, #{img}, #{like_cnt}, #{reply_cnt}, #{hit_cnt}, #{nickname}, #{address})")
    public int insert(Map<String, Object> map);

    // 상품 정보 조회
//    @Insert()
//    List<Map<String, Object>> getList();

}