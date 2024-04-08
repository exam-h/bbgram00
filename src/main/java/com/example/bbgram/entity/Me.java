package com.example.bbgram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Matchboads")
@Data
public class Me extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public Me() {
		super();
	}

	public Me(String dateandtime, String prefecture, String ground, String title, String referee, 
			String cost, String helpmember, String comments, String apply_end) {
		this.dateandtime = dateandtime;
		this.prefecture = prefecture;
		this.ground = ground;
		this.title = title;
		this.referee = referee;
		this.cost = cost;
		this.helpmember = helpmember;
		this.comments = comments;
		this.apply_end = apply_end;
	}

	@Id
	@SequenceGenerator(name = "matchboads_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String dateandtime;
	
	@Column(nullable = false)
	private String read;

	@Column(nullable = false)
	private String prefecture;

	@Column(nullable = false)
	private String ground;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String referee;

	@Column(nullable = false)
	private String cost;

	@Column(nullable = false)
	private String helpmember;
	
	@Column(nullable = false)
	private String comments;

	@Column(nullable = false)
	private String apply_end;
}
