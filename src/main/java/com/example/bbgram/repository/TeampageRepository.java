package com.example.bbgram.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Teampage;

@Repository
public interface TeampageRepository extends JpaRepository<Teampage, Long> {

    Iterable<Teampage> findAllByOrderByUpdatedAtDesc();
    List<Teampage> findByTeamIdOrderByUpdatedAtDesc(Long id);
}