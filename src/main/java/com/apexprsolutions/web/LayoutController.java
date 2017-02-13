package com.apexprsolutions.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apexprsolutions.domain.Article;
import com.apexprsolutions.domain.Image;
import com.apexprsolutions.repository.ArticleRepository;

@RestController
public class LayoutController {

	private static String APP_NAME = "Apex PR Solutions";

	@Autowired
	private ArticleRepository artRepo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("name", APP_NAME);
		List<Article> articles = artRepo.findLastFiveArticles();
		for (int i = 0; i < articles.size(); i++) {
			if (i == 5) {
				break;
			}
			Article article = articles.get(i);
			article.setIndex(i);
			List<Image> images = article.getImages();
			//articles.size();
			article.setMainImage(images.get(0).getId());
			if (i == 0) {
				article.setCssClass("active");
			}
		}
		mv.addObject("articles", articles);
		return mv;
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("about");
		mv.addObject("name", APP_NAME);
		return mv;
	}

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public ModelAndView services() {
		ModelAndView mv = new ModelAndView("services");
		mv.addObject("name", APP_NAME);
		return mv;
	}

	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public ModelAndView faq() {
		ModelAndView mv = new ModelAndView("faq");
		mv.addObject("name", APP_NAME);
		return mv;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("contact");
		mv.addObject("name", APP_NAME);
		return mv;
	}

}
