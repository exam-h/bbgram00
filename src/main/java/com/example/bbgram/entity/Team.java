package com.example.bbgram.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    			String activityDays, String matchDays, String teamIntroduction, User user) {
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
        this.user = user;
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
    
 // TeamとUserの双方向の関係
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user; //水色のuseは好きな表記も可
    
    //試合相手募集掲示板との関係
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mb> mbs;
    
    //メンバー募集掲示板との関係
//    @OneToMany
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private Pm pm;
}