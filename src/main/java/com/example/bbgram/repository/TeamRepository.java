package com.example.bbgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}