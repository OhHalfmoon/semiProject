package kr.co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.AddressVO;
/**
 * \
 * @author 오상현
 * @Since 2023.04.05 AddressMapper최초생성
 * = 생성목록
 * - 내 주소 목록 조회, 주소단일조회, 주소등록, 주소삭제, 회원탈퇴시 주소 삭제, 주소수정
 * 
 * @Since 2023.04.10 
 * memberNo를 통한 addrNo 조회 테스트 확인
 * 
 * @Since 2023.04.11 
 * memberNo에 해당하는 전체 주소조회 테스트 확인  
 *
 */


public interface AddressMapper {
	// 전체 주소 목록 조회
	List<AddressVO> getList();
	
	// 내 주소 목록 조회
	List<AddressVO> getMyList(Long memberNo);
	
	List<AddressVO> getAddressList(@Param("memberNo") Long memberNo);
	
	// 주소 단일 조회
	AddressVO read(Long addrNo);
	
	// memberNo를 통해 addrNo 반환
	Long readByMember(Long memberNo);
	
	// 메인 주소 가져오기
	AddressVO findMain(@Param("memberNo") Long memberNo);
	
	// 주소 등록
	int insert(AddressVO vo);
	
	int insertSelectkey(AddressVO vo);
	
	// 삭제
	int delete(@Param("addrNo")Long addrNo);
	
	//회원 탈퇴시 주소값 삭제
	int deleteById(Long memberNo);
	
	// 주소 수정
	int update(AddressVO vo);
	
	// 메인주소 변경
	int downMain(AddressVO vo);
	int updateMain(AddressVO vo);
	
	List<Double>getDistance(@Param("memberNo") Long memberNo, @Param("option") int option);

	int updateMemberNo(@Param("memberNo") Long memberNo);
}
