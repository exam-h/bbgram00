package com.example.bbgram.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User extends AbstractEntity implements UserDetails, UserInf {
	private static final long serialVersionUID = 1L;

	public enum Authority {
		ROLE_USER, ROLE_ADMIN
	};

	public User() {
		super();
	}

	public User(String email, String password, Authority authority, String tel, String name,
			String age, String birthDate, String prefecture, String city, String experience,
			String position, String throwing, String batting, String introduction) {

		this.username = email;
		this.password = password;
		this.authority = authority;
		this.tel = tel;
		this.name = name;
		this.age = age;
		this.birthDate = birthDate;
		this.prefecture = prefecture;
		this.city = city;
		this.experience = experience;
		this.position = position;
		this.throwing = throwing;
		this.batting = batting;
		this.introduction = introduction;

	}

	@Id
	@SequenceGenerator(name = "users_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Authority authority;

	@Column(nullable = false)
	private String tel;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String age;

	@Column(nullable = false)
	private String birthDate;

	@Column(nullable = false)
	private String prefecture;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String experience;
	@Column(nullable = false)

	private String position;

	@Column(nullable = false)
	private String throwing;

	@Column(nullable = false)
	private String batting;

	@Column(nullable = false)
	private String introduction;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(authority.toString()));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// UserとTeamの双方向の関係
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Team team;//↑のusertea.javaの水色の変数と一致しないといけない

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mypage> mypages;

}