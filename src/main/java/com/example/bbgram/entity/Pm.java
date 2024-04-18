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
@Table(name = "memberboards")
@Data
public class Pm extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	public Pm() {
		super();
	}
	
	public Pm(String title, String newbieposition, String prefecture, String city,
			String age_min, String age_max, String frequency, String activityDays, 
			String matchDays, String newplayer,  String team_pr, Team team){

		this.title = title;
		this.newbieposition = newbieposition;
		this.prefecture = prefecture;
		this.city = city;
		this.age_min = age_min;
		this.age_max = age_max;
		this.frequency = frequency;
		this.activityDays = activityDays;
		this.matchDays = matchDays;
		this.newplayer = newplayer;
		this.team_pr = team_pr;
		this.team = team;
	}
	
	@Id
	@SequenceGenerator(name = "memberboards_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String newbieposition;
	
	@Column(nullable = false)
	private String prefecture;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String age_min;
	
	@Column(nullable = false)
	private String age_max;
	
	@Column(nullable = false)
	private String frequency;
	
	@Column(nullable = false)
	private String activityDays;
	
	@Column(nullable = false)
	private String matchDays;
	
	@Column(nullable = false)
	private String newplayer;
	
	@Column(nullable = false)
	private String team_pr;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teamId", referencedColumnName = "teamId", nullable = false)
    private Team team;
}
