package kr.co.poetrypainting.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.poetrypainting.domain.AddressVO;

/**
 * 
 * @author 오상현
 * @since 2023.04.06
 * 기본 CRUD생성
 * 
 * @since 2023.04.10
 * 회원 id와 bno를 받아와 거리 측정하는 메서드 생성
 *
 * @since 2023.04.11
 * 회원 id를 받아와 회원의 주소록을 확인하는 메서드 생성
 * @since 2023.04.12
 * distance 수정
 * 
 *
 */
public interface AddressService {
	
	//주소등록
	int register (AddressVO vo);
	

	
	//전체 주소 확인
	AddressVO get(Long addrNo);
	// 메인주소 확인
	AddressVO getMain(Long memberNo);
	//주소 수정
	boolean modify(AddressVO vo);
	
/*	//메인 주소 삭제
	int changeMain(AddressVO vo);*/
	
	//메인 주소 수정
	int modifyMain(AddressVO vo);
	

	
	//주소 삭제
	int	remove(Long addrNo);
	
	// 거리측정 (보완 필요)
	double distance(Long myNo, Long no);
	
	// 해당 회원의 주소록 확인	
	List<AddressVO> getMyAddress(String id);
	List<AddressVO> getAddressList(Long memberNo);
//	List<AddressVO>getList
	
	List<Double>getDistance(Long memberNo, int option);
}
