package kr.co.poetrypainting.mapper;

import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration("../../../../../../../src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CrawlTests {

    @Autowired
    private CrawlMapper crawlMapper;

    @Test
    public void testExist() {
        assertNotNull(crawlMapper);
    }

    @Test
    public void testParse() throws IOException {
        // 크롤링할 페이지 URL
        String url = "https://www.daangn.com/hot_articles";

        // Jsoup을 사용하여 페이지를 가져오기
        Document doc = Jsoup.connect(url).get();

        // 상품 정보가 있는 요소를 찾기
        Elements items = doc.select(".cards-wrap .card-top");

        // 각 상품 정보에 대해 반복문 실행
        for (Element item : items) {

            // 상품 상세 정보 주소 찾기
            String detail = item.selectFirst("a.card-link").attr("href");

            // 상품 상세 정보 주소 URL
            String detUrl = "https://www.daangn.com" + detail;

            // Jsoup을 사용하여 상세페이지 가져오기
            Document doc2 = Jsoup.connect(detUrl).get();

            // 제목 추출
            String title = doc2.select("#article-title").text();

            // 카테고리, 등록일 추출
            String catReg = doc2.select("#article-category").text();
            // 구분자를 이용하여 분리
            String[] val = catReg.split("\\s*∙\\s*");
            // 카테고리 추출
            String category = val[0];
            // 등록일 추출
            String regDate = val[1];

            // 닉네임 추출
            String nickname = doc2.select("#nickname").text();
            // 주소 추출
            String address = doc2.select("#region-name").text();

            // 이미지 추출
            String img = item.selectFirst("img").attr("src");
            // 상품 가격 추출
            String price = doc2.select("#article-price").text().replaceAll("[^0-9]", "");

            // 상품 설명 추출
            String content = doc2.select("#article-detail").text();

            // 관심,채팅,조회 추출
            String counts = doc2.select("#article-counts").text();

            // 구분자를 이용하여 분리
            String[] val2 = counts.split("\\s*∙\\s*");

            // 관심수 추출
            String like = val2[0].replaceAll("[^0-9]", "");
            // 댓글수 추출
            String reply = val2[1].replaceAll("[^0-9]", "");
            // 조회수 추출
            String hit = val2[2].replaceAll("[^0-9]", "");

            Map<String, Object> map = new HashMap<String, Object>();

            // 출력
            map.put("title", title);
            map.put("category", category);
            map.put("content", content);
            map.put("regdate ", regDate);
            map.put("price", price);
            map.put("img", img);
            map.put("like_cnt", like);
            map.put("reply_cnt", reply);
            map.put("hit_cnt", hit);
            map.put("nickname", nickname);
            map.put("address", address);

            crawlMapper.insert(map);
        }
    }
}
