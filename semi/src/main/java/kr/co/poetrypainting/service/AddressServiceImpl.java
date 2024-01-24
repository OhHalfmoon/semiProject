package kr.co.poetrypainting.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.poetrypainting.domain.AddressVO;
import kr.co.poetrypainting.domain.BoardVo;
import kr.co.poetrypainting.mapper.AddressMapper;
import kr.co.poetrypainting.mapper.BoardMapper;
import kr.co.poetrypainting.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@ToString
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {		
	/**
	 * @author 오상현
	 * @Since 2023.04.05 AddressServiceImpl최초생성
	 * - 카카오 REST API 사용 (테스트 아직 미실시)
	 * 
	 * @Since 2023.04.07
	 * - remove transactional 테스트 
	 * - modify 테스트 
	 * 
	 * @Since 2023.04.10
	 * - 회원 id와 bno를 받아와 거리 측정 테스트
	 *	 
	 * @since 2023.04.11
	 * 회원 id를 받아와 회원의 주소록을 확인하는 메서드 생성
	 * 
	 * @since 2023.04.12
	 * distance 수정 
	 */
	@Autowired
	private final AddressMapper addressMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private BoardMapper boardMapper;
		
	@Override
	public int register(AddressVO vo) {
		return addressMapper.insertSelectkey(vo);
		
	}

	@Override
	public AddressVO get(Long addrNo) {
		return addressMapper.read(addrNo);
	}

	@Override
	public boolean modify(AddressVO vo) {
		return addressMapper.update(vo) > 0;
	}
	@Transactional
	@Override	
	public int remove(Long addrNo) {
		return addressMapper.delete(addrNo);
	}
	
	
	
//	@Override
//	public Double distance(String id, Long bno) {
//
////		BoardVo vo = new BoardVo();
////		Long num = 15L;		
////		vo.setMemberNo(num);
//		Long myAcount = memberMapper.getByMemberId(id).getMemberNo();
//		Long myAddrchk = addressMapper.readByMember(myAcount);
//		Long shopAcount = addressMapper.readByMember(boardMapper.readByMember(bno));
////		Long shopAcount = addressMapper.readByMember(vo.getMemberNo());
//		Double myX1 = addressMapper.read(myAddrchk).getX(); 
//		Double myY1 = addressMapper.read(myAddrchk).getY(); 
//
//		Double shopX1 = addressMapper.read(shopAcount).getX(); 
//		Double shopY1 = addressMapper.read(shopAcount).getY();
//		
//		Double x = myX1 - shopX1;
//		Double y = myY1 - shopY1;
//		Double distance = Math.sqrt((x*x) + (y*y))*100;
//		
//		
//		return distance;
//	}
	@Override
	public double distance(Long myNo, Long bno) {
		Long myAddr = addressMapper.readByMember(myNo);
		Long shopAddr = addressMapper.readByMember(boardMapper.readByMember(bno));		
		Double myX = addressMapper.read(myAddr).getX();
		Double shopX = addressMapper.read(shopAddr).getX();
		
		Double myY = addressMapper.read(myAddr).getY();
		Double shopY = addressMapper.read(shopAddr).getY();
		
		Double theta = myX - shopX;
		
		Double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
				Math.sin(myY * (Math.PI/180)) * Math.sin(shopY * (Math.PI/180)) +
				Math.cos(myY * (Math.PI/180)) * Math.cos(shopY * (Math.PI/180)) * Math.cos(theta*(Math.PI/180))
				
				);

		System.out.println("distance : " + Math.round((distance* 1.609344)*100.000)/100.00);
		
		return Math.round(((distance* 1.609344)*100.000)/100.00);
		
	}

	@Override
	public List<AddressVO> getMyAddress(String id) {
		Long myAcount = memberMapper.getByMemberId(id).getMemberNo();
		List<AddressVO> MyAddress = addressMapper.getMyList(myAcount);
		
		return MyAddress;
	}

	@Override
	public List<Double> getDistance(Long memberNo, int option) {
		List<Double> distanceList = addressMapper.getDistance(memberNo, option);
		return distanceList;
	}

	@Override
	public List<AddressVO> getAddressList(Long memberNo) {
		return addressMapper.getAddressList(memberNo);
	}
	
	@Override
	public AddressVO getMain(Long memberNo) {
		return addressMapper.findMain(memberNo);
	}
/*	
	@Override
	public int changeMain(AddressVO vo) {
		return addressMapper.downMain(vo);
	}*/

	
	@Override
	@Transactional
	public int modifyMain(AddressVO vo) {
		int deleteResult = addressMapper.downMain(vo); // 실행 ok
		int updateResult = addressMapper.updateMain(vo); // 실행 ok
		if ((deleteResult == 1) && (updateResult == 1)) {
			return 1;
		} else {
			return 0;
		}
		
	}



	
	

	
	
	

	// ==============================================================================================
    /**
     * 경위도 정보로 주소를 불러오는 메소드
     * @throws UnsupportedEncodingException 
     */
//     public static String coordToAddr(String longitude, String latitude){
//       String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?x="+longitude+"&y="+latitude;
//       String addr = "";
//       try{
//         addr = getRegionAddress(getJSONData(url));
//         //LOGGER.info(addr);
//       }catch(Exception e){
//         System.out.println("주소 api 요청 에러");
//         e.printStackTrace();
//       }
//         return addr;
//     }
//	
//	
//	
//	//  REST API로 통신하여 받은 JSON형태의 데이터를 String으로 받아오는 메소드
//	private static String getJSONData(String apiUrl) throws Exception {
//		HttpsURLConnection conn = null;
//		StringBuffer response = new StringBuffer();
//		
//		//인증키 
//		String auth = "KakaoAK " + "1cbbb5e35159be304d08cd94adc6fa18";
//		
//		// URL 설정
//		URL url = new URL(apiUrl);
//		
//		conn = (HttpsURLConnection) url.openConnection();
//		
//		// Request 형식 설정
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("X-Requested-With", "curl");
//        conn.setRequestProperty("Authorization", auth);
//
//        //request에 JSON data 준비
//        conn.setDoOutput(true);
//         
//        //보내고 결과값 받기
//        int responseCode = conn.getResponseCode();
//        if (responseCode == 400) {
//            System.out.println("400:: 해당 명령을 실행할 수 없음");
//        } else if (responseCode == 401) {
//            System.out.println("401:: Authorization가 잘못됨");
//        } else if (responseCode == 500) {
//            System.out.println("500:: 서버 에러, 문의 필요");
//        } else { // 성공 후 응답 JSON 데이터받기
//        	 
//        	 Charset charset = Charset.forName("UTF-8");
//             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
//             
//             String inputLine;
//             while ((inputLine = br.readLine()) != null) {
//             	response.append(inputLine); 
//             } 
//         }
//         
//         return response.toString();
//    }
//    
//     /**
//      * JSON형태의 String 데이터에서 주소값(address_name)만 받아오기
//      */
//	private static String getRegionAddress(String jsonString) {
//        String value = "";
//        JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
//        JSONObject meta = (JSONObject) jObj.get("meta");
//        long size = (long) meta.get("total_count");
//        
//        if(size>0){
//            JSONArray jArray = (JSONArray) jObj.get("documents");
//            JSONObject subJobj = (JSONObject) jArray.get(0);
//            JSONObject roadAddress =  (JSONObject) subJobj.get("road_address");
//            
//            if(roadAddress == null){
//                JSONObject subsubJobj = (JSONObject) subJobj.get("address");
//                value = (String) subsubJobj.get("address_name");
//            }else{
//                value = (String) roadAddress.get("address_name");
//            }
//            
//            if(value.equals("") || value==null){
//                subJobj = (JSONObject) jArray.get(1);
//                subJobj = (JSONObject) subJobj.get("address");
//                value =(String) subJobj.get("address_name");    
//            }
//        }
//        return value;
//		
//	}
}
