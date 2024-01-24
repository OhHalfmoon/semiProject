package kr.co.poetrypainting.domain.dto;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.apache.ibatis.type.Alias;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
@Alias("attach")
public class AttachDTO {
	private Long attNo;
	private String attUuid;
//	private String attOrigin;
	private String attName;
	private String attPath;
	
	private Long bno;
	private int uuidIdx;
	
	public String getUrl() throws UnsupportedEncodingException{
		if(attName == null){
			String noImage = UriComponentsBuilder.fromPath("")
					.queryParam("attName", URLEncoder.encode("","utf-8"))
					.queryParam("attPath", "")
					.queryParam("attUuid", "")
					.build().toUriString();
			return noImage;
		}
		String url = UriComponentsBuilder.fromPath("")
				.queryParam("attName", URLEncoder.encode(attName,"utf-8"))
				.queryParam("attPath", attPath)
				.queryParam("attUuid", attUuid)
				.build().toUriString();
		return url;
	}

}
