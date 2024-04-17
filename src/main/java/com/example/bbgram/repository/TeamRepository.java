package com.example.bbgram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Team;
import com.example.bbgram.entity.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	 List<Team> findByUser(User user);
}