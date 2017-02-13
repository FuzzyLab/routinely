package com.apexprsolutions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apexprsolutions.domain.Article;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {

	public Article findById(int id);

	@Query("select a from Article a order by a.id desc")
	public List<Article> findLastFiveArticles();

}