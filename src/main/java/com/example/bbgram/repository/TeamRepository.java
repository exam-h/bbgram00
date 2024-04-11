package com.example.bbgram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	 List<Team> findByUserId(Long userId);
}