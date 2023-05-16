package com.onerivet.deskbook.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.DatatypeConverter;

@Component
public class ImageUtils {
	
	@Value("${image.upload.path}")
	String urlPath;

	public String encodeImage(String path) throws Exception {
		File file = new File(path);
		byte[] data = FileUtils.readFileToByteArray(file);

		String imageString = Base64.getEncoder().encodeToString(data);

		return imageString;
	}

	public String base64ToFile(String base64String, String firstName, String id) throws IOException {
		
		

		List<String> validBase64 = validate(base64String);
		  
		File f = new File(urlPath);
		if(!f.exists()) {
			Files.createDirectories(Paths.get(urlPath, firstName + "_" + id + validBase64.get(1)).getParent());
			
		}

		byte[] decodedBytes = DatatypeConverter.parseBase64Binary(validBase64.get(0));

		// byte[] data = Base64.getDecoder().decode(image);

		ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
		BufferedImage image1 = ImageIO.read(bis);

		int size = decodedBytes.length;
		if (size > (2 * 1024 * 1024)) {
			throw new IllegalArgumentException(
					"File size exceeds the maximum limit of 2MB. Please choose a smaller file");
		}

		if (image1 != null && image1.getWidth() > 0 && image1.getHeight() > 0) {
			FileOutputStream img = new FileOutputStream(urlPath + firstName + "_" + id + validBase64.get(1));
			img.write(decodedBytes);
			img.close();
		}

		return validBase64.get(1);
	}

	private List<String> validate(String base64String) {

		if (base64String.startsWith("data:image/jpeg;base64,")) {

			return new ArrayList<>(Arrays.asList(base64String.substring(23), ".jpeg"));

		} else if (base64String.startsWith("data:image/jpg;base64,")) {

			return new ArrayList<>(Arrays.asList(base64String.substring(22), ".jpg"));

		} else if (base64String.startsWith("data:image/png;base64,")) {

			return new ArrayList<>(Arrays.asList(base64String.substring(22), ".png"));

		}
		if (base64String.startsWith("/9j/")) {

			return new ArrayList<>(Arrays.asList(base64String, ".jpg"));
		}

		throw new IllegalArgumentException("Upload valid image, only .jpg, .jpeg and .png are allowed");

	}
}
