package com.example.bbgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Me;


@Repository
public interface MeRepository extends JpaRepository<Me, String> {
	
}
