package kr.co.poetrypainting.mapper;

import java.util.List;

import kr.co.poetrypainting.domain.dto.AttachDTO;

public interface AttachMapper {
	void insert(AttachDTO vo);
	
	void delete(String uuid);
	
	List<AttachDTO> findBy(Long bno);
	
	void deleteAll(Long bno);
}
