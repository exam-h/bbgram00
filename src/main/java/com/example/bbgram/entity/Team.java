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
@Table(name = "teams")
@Data
public class Team extends AbstractEntity {
    private static final long serialVersionUID = 1L;

//    public enum Authority {
//        ROLE_USER, ROLE_ADMIN
//    };

    public Team() {
        super();
    }

    public Team(String name, String read, String prefecture, String city,
    			String experience, String formation, String frequency,
    			String activityDays, String matchDays, String teamIntroduction, Long userId) {
        this.name = name;
        this.read = read;
        this.prefecture = prefecture;
        this.city = city;
        this.experience = experience;
        this.formation = formation;
        this.frequency = frequency;
        this.activityDays = activityDays;
        this.matchDays = matchDays;
        this.teamIntroduction = teamIntroduction;
        this.userId = userId;
    }

    @Id
    @SequenceGenerator(name = "teams_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String read;

    @Column(nullable = false)
    private String prefecture;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String experience;
    
    @Column(nullable = false)
    private String formation;
    
    @Column(nullable = false)
    private String frequency;
    
    @Column(nullable = false)
    private String activityDays;
    
    @Column(nullable = false)
    private String matchDays;
    
    @Column(nullable = false)
    private String teamIntroduction;
    
    @Column(nullable = false)
    private Long userId;
    
}