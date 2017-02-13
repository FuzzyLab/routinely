package com.apexprsolutions.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.apexprsolutions.domain.Article;
import com.apexprsolutions.domain.Image;
import com.apexprsolutions.domain.User;
import com.apexprsolutions.repository.ArticleRepository;
import com.apexprsolutions.repository.UserRepository;

@RestController
public class ArticleController {

	private static String APP_NAME = "Apex PR Solutions";

	@Autowired
	private ArticleRepository artRepo;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/postarticle", method = RequestMethod.GET)
	public ModelAndView getPostArticle(@RequestParam("email") String email,
			@RequestParam("signature") String signature) {
		ModelAndView mv = new ModelAndView("postarticle");
		//mv.addObject("message", "WELCOME");
		mv.addObject("email", email);
		mv.addObject("signature", signature);
		return mv;
	}

	@RequestMapping(value = "/postarticle", method = RequestMethod.POST)
	public ModelAndView postArticle(@RequestParam("title") String title, @RequestParam("article") String content,
			@RequestParam("images") MultipartFile[] images, @RequestParam("email") String email,
			@RequestParam("signature") String signature) throws IOException {
		ModelAndView mv = new ModelAndView("postarticle");
		mv.addObject("email", email);
		mv.addObject("signature", signature);
		User user = userRepo.findByEmailAndPassword(email, signature);
		String msg = "FAILURE";
		if (user != null && title != null && !title.isEmpty() && content != null && !content.isEmpty()
				&& images.length > 0) {
			Article article = new Article(title, content.replaceAll("\r\n", "<br/>"));
			List<Image> imageList = new ArrayList<Image>();
			for (MultipartFile image : images) {
				Image img = new Image();
				img.setContent(image.getBytes());
				img.setArticle(article);
				img.setContentType(image.getContentType());
				imageList.add(img);
			}
			article.setImages(imageList);
			artRepo.save(article);
			msg = "SUCCESS";
		}
		//mv.addObject("message", msg);
		return mv;
	}
	
	@RequestMapping("/article/{articleId}")
	public ModelAndView getArticle(@PathVariable("articleId") int articleId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("name", APP_NAME);
		Article article = artRepo.findById(articleId);
		article.setContent(article.getContent().replaceAll("\r\n", "<br/>"));
		if (article != null) {
			List<Image> images = article.getImages();
			for (int i = 0; i < images.size(); i++) {
				images.get(i).setIndex(i);
				if (i == 0)
					images.get(i).setCssClass("active");
			}
			mv.setViewName("article");
			mv.addObject("article", article);
		}
		return mv;
	}

}
