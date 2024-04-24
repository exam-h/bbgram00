package com.example.bbgram.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Mypage;
import com.example.bbgram.entity.User;

@Repository
public interface MypageRepository extends JpaRepository<Mypage, Long> {

    //Iterable<Mypage> findAllByOrderByUpdatedAtDesc();
    Iterable<Mypage> findByUserOrderByUpdatedAtDesc(User user);
}