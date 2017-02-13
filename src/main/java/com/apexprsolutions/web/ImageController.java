package com.apexprsolutions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apexprsolutions.domain.Image;
import com.apexprsolutions.repository.ImageRepository;

@RestController
public class ImageController {

	@Autowired
	private ImageRepository imgRepo;

	@RequestMapping("/getimage/{imageId}")
	public HttpEntity<byte[]> getImage(@PathVariable("imageId") int imageId) {
		Image image = imgRepo.findById(imageId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", image.getContentType());
		return new HttpEntity<byte[]>(image.getContent(), headers);
	}

}
