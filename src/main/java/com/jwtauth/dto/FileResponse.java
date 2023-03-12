package com.jwtauth.dto;

import org.springframework.core.io.Resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileResponse {

	private Resource resource;
	private String fileName;

}
