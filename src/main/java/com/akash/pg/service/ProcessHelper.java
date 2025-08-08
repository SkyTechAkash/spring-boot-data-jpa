package com.akash.pg.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProcessHelper {

	public boolean uploadFile(MultipartFile file) throws IOException {
		//final String UPLOAD_DIR = "C:\\Users\\tomer\\Documents\\workspace-spring-tool-suite-4-4.22.1.RELEASE\\SpringFirstApp-1\\src\\main\\resources\\static\\image";
		final String UPLOAD_DIR = new ClassPathResource("static/image").getFile().getAbsolutePath();
		InputStream inputStream = file.getInputStream();
//		int available = inputStream.available();
//		byte[] b = new byte[available];
//		inputStream.read(b);
//
//		FileOutputStream outputStream = new FileOutputStream(UPLOAD_DIR + File.separator + file.getOriginalFilename());
//		outputStream.write(b);
//
//		outputStream.close();
//		outputStream.flush();
		
		Files.copy(inputStream, Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);

		return true;
	}

}
