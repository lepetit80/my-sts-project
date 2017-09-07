package io.spring4mvc.spring4mvc.profile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import io.spring4mvc.spring4mvc.config.PicturesUploadProperties;

@Controller
@SessionAttributes("picturePath")
public class PictureUploadController {

	private final MessageSource messageSource;
	private final Resource pictureDir;
	private final Resource anonymousPicture;
	
	//TODO - #1-issue not working why?
//	@RequestMapping("/upload-error")
//	public ModelAndView onUploadError(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("profile/uploadPage");
//		mav.addObject("error", request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE));
//		return mav;
//	}
	
	@RequestMapping("/upload-error")
	public ModelAndView onUploadError(Locale locale) {
		ModelAndView mav = new ModelAndView("profile/uploadPage");
		mav.addObject("error", messageSource.getMessage("upload.file.too.big", null, locale));
		return mav;
	}
	
//	@ExceptionHandler(IOException.class)
//	public ModelAndView handleIOException(IOException exception) {
//		ModelAndView mav = new ModelAndView("profile/uploadPage");
//		mav.addObject("error", exception.getMessage());
//		return mav;
//	}
	
	@ExceptionHandler(IOException.class)
	public ModelAndView handleIOException(Locale locale) {
		ModelAndView mav = new ModelAndView("profile/uploadPage");
		mav.addObject("error", messageSource.getMessage("upload.io.exception", null, locale));
		return mav;
	}
	
	@Autowired
	public PictureUploadController(PicturesUploadProperties picturesUploadProperties, MessageSource messageSource) {
		this.pictureDir = picturesUploadProperties.getUploadPath();
		this.anonymousPicture = picturesUploadProperties.getAnonymousPicture();
		this.messageSource = messageSource;
	}
	
//	public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");
	
	@RequestMapping("upload")
	public String uploadPage() {
		return "profile/uploadPage";
	}
	
	@ModelAttribute("picturePath")
	public Resource picturePath() {
		return anonymousPicture;
	}
	
	@RequestMapping(value = "/uploadedPicture")
	public void getUploadedPicture(HttpServletResponse response, 
		@ModelAttribute("picturePath") Resource picturePath) throws IOException {
//		ClassPathResource classPathResource = new ClassPathResource("/images/anonymous.png");
//		response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
//		IOUtils.copy(classPathResource.getInputStream(), response.getOutputStream());
		response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(picturePath.getFilename()));
		IOUtils.copy(picturePath.getInputStream(), response.getOutputStream());
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String onUpload(MultipartFile file, RedirectAttributes redirectAttributes, Model model) throws IOException {
		
		if (file.isEmpty() || !isImage(file)) {
//			redirectAttributes.addFlashAttribute("error", "Incorrect file. plz upload a picture.");
//			return "redirect:/upload";
			throw new IOException("Incorrect file. plz upload a picture.");
		}
		
		Resource picturePath = copyFileToPictures(file);
		model.addAttribute("picturePath", picturePath);
		
		return "profile/uploadPage";
	}

	private boolean isImage(MultipartFile file) {
		return file.getContentType().startsWith("image");
	}

	private Resource copyFileToPictures(MultipartFile file) throws IOException {
		String fileExtenstion = getFileExtension(file.getOriginalFilename());
		File tempFile = File.createTempFile("pic", fileExtenstion, pictureDir.getFile());
		
		try (InputStream inputStream = file.getInputStream(); 
				OutputStream outputStream = new FileOutputStream(tempFile);) {
			IOUtils.copy(inputStream, outputStream);
		}
		
		return new FileSystemResource(tempFile);
	}
	
//	private void copyFileToPictures(MultipartFile file) throws IOException, FileNotFoundException {
//		String filename = file.getOriginalFilename();
//		File tempFile = File.createTempFile("pic", getFileExtension(filename), PICTURES_DIR.getFile());
//		
//		try (InputStream inputStream = file.getInputStream(); 
//			OutputStream outputStream = new FileOutputStream(tempFile);) {
//			IOUtils.copy(inputStream, outputStream);
//		}
//	}

	private static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."));
	}
	
}
