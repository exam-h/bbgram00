package com.example.bbgram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "matchboards")
@Data
public class Mb extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public Mb() {
		super();
	}

	public Mb(String dateandtime, String prefecture, String ground, String title, String referee, 
			String cost, String helpmember, String comments, Team team) {
		this.dateandtime = dateandtime;
		this.prefecture = prefecture;
		this.ground = ground;
		this.title = title;
		this.referee = referee;
		this.cost = cost;
		this.helpmember = helpmember;
		this.comments = comments;
		this.team = team;
	}

	@Id
	@SequenceGenerator(name = "matchboads_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String dateandtime;

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
	
//	@OneToMany
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private Mb mbs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teamId", referencedColumnName = "teamId", nullable = false)
    private Team team; //水色のuseは好きな表記も可
}
