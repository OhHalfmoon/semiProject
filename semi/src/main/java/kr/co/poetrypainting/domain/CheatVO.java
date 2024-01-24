package kr.co.poetrypainting.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.co.poetrypainting.domain.dto.AttachDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author 이영미
 * = 생성목록
 * - 신고번호, 신고자, 신고일, 신고처리여부, 카테고리번호, 처벌결과, 신고글번호, 기타사유, 카테고리분류 
 * - 처벌번호, 처벌명, 기타사유
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("cheat")
public class CheatVO {
    //cheat
    private Long chNo; // 신고번호
    private Long memberNo; // 신고자
    private Long cateNo; // 카테고리번호

    private Date chDate; // 신고일
    private boolean chProgress; // 신고 처리 여부

    private String chEtc; //기타사유
    private int penaltyNo; // 처벌번호
    private Long bno; // 신고글번호
    private String penaltyEtc; //기타사유
    private Date penaltyDate; // 신고일
    
    private String cheatName;
    private String chReport;
    private Long penaltyUserNo;
    private String penaltyName;
    private String penaltyReport;
    
    private String title;
    private Long price;
    private String dong;
    
    private List<AttachDTO> attachs;
}