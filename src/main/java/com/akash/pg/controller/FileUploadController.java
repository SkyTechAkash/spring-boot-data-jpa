package com.akash.pg.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.akash.pg.service.ProcessHelper;

@RestController
public class FileUploadController {

	@Autowired
	private ProcessHelper processHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uplodaFile(@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getName());
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("No such file are found! Please Try again.Thank you!");
		} else if (!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Only jpeg files are allowed. Please try again ");
		} else {
			processHelper.uploadFile(file);
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
		}

	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename) throws IOException{
		File file = new File(new ClassPathResource("static/image").getFile().getAbsolutePath() + File.separator + filename);
		System.out.println(file);
		byte[] allBytes = Files.readAllBytes(file.toPath());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		httpHeaders.setContentDispositionFormData("attachment", filename);
		return new ResponseEntity<>(allBytes, httpHeaders, HttpStatus.OK);
	}

}
