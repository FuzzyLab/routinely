package com.apexprsolutions.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apexprsolutions.domain.Image;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Integer> {

	public Image findById(int id);

}