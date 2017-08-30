package io.spring4mvc.spring4mvc.profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PictureUploadController {

	public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");
	
	@RequestMapping("upload")
	public String uploadPage() {
		return "profile/uploadPage";
	}
	
	@RequestMapping(value = "/uploadedPicture")
	public void getUploadedPicture(HttpServletResponse response) throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/images/anonymous.png");
		response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
		IOUtils.copy(classPathResource.getInputStream(), response.getOutputStream());
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		
		if (file.isEmpty() || !isImage(file)) {
			redirectAttributes.addFlashAttribute("error", "Incorrect file. plz upload a picture.");
			return "redirect:/upload";
		}
		
		copyFileToPictures(file);
		
		return "profile/uploadPage";
	}

	private boolean isImage(MultipartFile file) {
		return file.getContentType().startsWith("image");
	}

	private void copyFileToPictures(MultipartFile file) throws IOException, FileNotFoundException {
		String filename = file.getOriginalFilename();
		File tempFile = File.createTempFile("pic", getFileExtension(filename), PICTURES_DIR.getFile());
		
		try (InputStream inputStream = file.getInputStream(); 
			OutputStream outputStream = new FileOutputStream(tempFile);) {
			IOUtils.copy(inputStream, outputStream);
		}
	}

	private static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}
	
}
