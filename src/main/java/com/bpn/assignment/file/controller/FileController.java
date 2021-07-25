package com.bpn.assignment.file.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bpn.assignment.utils.Constants;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {

	@GetMapping(value = "images/{dir}/{fileName}.{ext}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable("dir") String dir, @PathVariable("fileName") String fileName,
			@PathVariable("ext") String ext) throws IOException {
		InputStream in = new FileInputStream(
				new File(String.format("%s/%s/%s.%s", Constants.realPath, dir, fileName, ext)));
		return IOUtils.toByteArray(in);
	}

	@RequestMapping(value = "images/save", method = RequestMethod.POST, produces = "application/json")
	public String saveImage(@RequestParam("image") MultipartFile files) {

		System.out.println(" File Size : " + files.getSize());
		String imageName = "";
		try {
			imageName = uploadImage(files);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imageName;
	}

	public String uploadImage(MultipartFile multipartFile) throws IOException, Exception {
		String realPath = Constants.realPath;

		int count = 0;
		String randomNumber = "";
		String uploadTempPath = realPath + File.separator + "blogs" + File.separator;

		String fileName = multipartFile.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.indexOf(".") + 1);

		File dir = new File(uploadTempPath);
		if (!dir.exists()) {
			System.out.println("realPath => " + uploadTempPath);
			dir.mkdirs();
		}

		do {

			randomNumber = Instant.now().toEpochMilli() + "";

			String uploadTempPaths = uploadTempPath + randomNumber + "." + fileExtension;
			File uploadTempDir = new File(uploadTempPaths);
			if (!uploadTempDir.exists()) {
				break;
			}

			if (count++ > 1000) {
				throw new Exception("Too large number of image exists in the Directory.");
			}

		} while (true);

		// convert to jpg start
		if (fileExtension.equals("png")) {
			InputStream in = new ByteArrayInputStream(multipartFile.getBytes());
			BufferedImage bufferedImage = ImageIO.read(in);

			// create a blank, RGB, same width and height, and a white background
			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			// write to jpeg file
			ImageIO.write(newBufferedImage, fileExtension,
					new File(uploadTempPath + randomNumber+"_" +fileName));
			// convert to jpg end
		} else {
			// Now do something with file...
			FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadTempPath + randomNumber+"_" +fileName));
		}

		return randomNumber + "_" + fileName;
	}

}
