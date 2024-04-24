package com.example.bbgram.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bbgram.entity.Mb;


@Repository
public interface MbRepository extends JpaRepository<Mb, Long> {
	/**
	* IDで検索する *
	* @param id ID
	* @return 投稿
	*/
	public Optional<Mb> findById(Long id);
	
	/**
	 *　更新日時の降順ですべての投稿を検索する
	 *
	 *@return 投稿のリスト
	 */
	List<Mb> findAllByOrderByUpdatedAtDesc();
	
	List<Mb> findByPrefecture(String prefecture);
	
}