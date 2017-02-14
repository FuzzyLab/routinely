package com.apexprsolutions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
		MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
		mustacheViewResolver.setPrefix("classpath:/templates/html/");
		mustacheViewResolver.setSuffix(".html");
		mustacheViewResolver.setCache(false);
		mustacheViewResolver.setContentType("text/html;charset=utf-8");

		MustacheTemplateLoader mustacheTemplateLoader = new MustacheTemplateLoader();
		mustacheTemplateLoader.setResourceLoader(resourceLoader);

		mustacheViewResolver.setTemplateLoader(mustacheTemplateLoader);
		return mustacheViewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/csv/*").addResourceLocations("classpath:/templates/csv/");
		registry.addResourceHandler("/js/*").addResourceLocations("classpath:/templates/js/");
		registry.addResourceHandler("/img/*").addResourceLocations("classpath:/templates/img/");
		registry.addResourceHandler("/less/*").addResourceLocations("classpath:/templates/less/");
		registry.addResourceHandler("/vendor/jquery/*").addResourceLocations("classpath:/templates/vendor/jquery/");
		registry.addResourceHandler("/vendor/device-mockups/*").addResourceLocations("classpath:/templates/vendor/device-mockups/");
		registry.addResourceHandler("/vendor/***").addResourceLocations("classpath:/templates/vendor/**");
	}

}
