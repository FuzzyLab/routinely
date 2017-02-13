package com.apexprsolutions.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.apexprsolutions.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

}