package kr.co.poetrypainting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.poetrypainting.domain.dto.AttachDTO;
import kr.co.poetrypainting.service.BoardService;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@Log4j
public class FileController {
	@Getter
	private static final String PATH = "c:/ssangchu";
	@Autowired
	private BoardService boardService;
	
	@PostMapping(value="/uploadA", produces=MediaType.APPLICATION_JSON_VALUE)@ResponseBody
	public List<AttachDTO> uploadAjax(MultipartFile[] files){
		List<AttachDTO> list = new ArrayList<>();
		File uploadPath = new File(PATH, getFolder());
		if(!uploadPath.exists()){
			uploadPath.mkdirs();
		}
		
		for (MultipartFile m : files) {
			//실제 스트림 전송
			
			String uuidStr = UUID.randomUUID().toString();
			String tName = uuidStr + "_" + m.getOriginalFilename();
			File f = new File(uploadPath, tName);
			try {
				m.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File f2 = new File(uploadPath, "s1_" + tName);
			try {
				Thumbnails.of(f).crop(Positions.CENTER).size(540, 580).toFile(f2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File f3 = new File(uploadPath, "s2_" + tName);
			try {
				Thumbnails.of(f2).size(100, 100).toFile(f3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			AttachDTO dto = new AttachDTO();
			dto.setAttUuid(uuidStr);
			dto.setAttPath(getFolder());
			dto.setAttName(m.getOriginalFilename());
			list.add(dto);
		}
		return list;
	}
	@GetMapping("/display") @ResponseBody
	public ResponseEntity<byte[]> display(AttachDTO dto){//소문자면 파라미터 무조건 있어야하기때문에 매퍼클래스 사용
		//fileName : path + uuid + name

		String s = PATH + "/" + dto.getAttPath() + "/s1_" + dto.getAttUuid() + "_" + dto.getAttName();;
		File file = new File(s);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);//에러 없애주껭
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	@GetMapping("/displayOrigin") @ResponseBody
	public ResponseEntity<byte[]> displayOrigin(AttachDTO dto){//소문자면 파라미터 무조건 있어야하기때문에 매퍼클래스 사용
		//fileName : path + uuid + name
		String s = PATH + "/" + dto.getAttPath() + "/" + dto.getAttUuid() + "_" + dto.getAttName();;
		File file = new File(s);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);//에러 없애주껭
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	@GetMapping("/displaySmall") @ResponseBody
	public ResponseEntity<byte[]> displaySmall(AttachDTO dto){//소문자면 파라미터 무조건 있어야하기때문에 매퍼클래스 사용
		//fileName : path + uuid + name
		String s = PATH + "/" + dto.getAttPath() + "/s2_" + dto.getAttUuid() + "_" + dto.getAttName();;
		File file = new File(s);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);//에러 없애주껭
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	@GetMapping("/deleteFile") @ResponseBody
	public String deleteFile(AttachDTO dto){
		log.warn(dto);
		return boardService.deleteFile(dto);
	}

	private String getFolder(){
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

}
