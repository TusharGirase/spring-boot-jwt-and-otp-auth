package com.jwtauth.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauth.service.EmployeeReportService;

@RestController
@RequestMapping(value = "/auth")
public class EmployeeReportsController {

	@Autowired
	EmployeeReportService service;

	@GetMapping("/empoyees-report")
	public ResponseEntity<Resource> getEmployeesReport(HttpServletResponse response) throws IOException {

		File file = new File(service.generateEmployeeDetailsReports());
		Resource resource = new UrlResource(file.toURI());

		Path path = file.toPath();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName()).body(resource);
	}

}
