package com.example.bbgram.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.bbgram.validation.constraints.PasswordEquals;

import lombok.Data;

@Data
@PasswordEquals
public class UserForm {

	@NotEmpty
	@Size(max = 100)
	private String name;

	@NotEmpty
	@Size(max = 25)
	private String age;
	
	
	
	@NotEmpty
	@Size(max = 25)
	private String prefecture;
	
	@NotEmpty
	@Size(max = 25)
	private String city;
	
	@NotEmpty
	@Size(max = 50)
	private String experience;
	
	
	@NotEmpty
	@Size(max = 25)
	private String throwing;
	
	@NotEmpty
	@Size(max = 25)
	private String batting;
	
	@NotEmpty
	@Size(max = 50)
	private String position;
	
	@NotEmpty
	@Email
	private String username;

	@NotEmpty
	@Size(max = 20)
	private String password;

	@NotEmpty
	@Size(max = 20)
	private String passwordConfirmation;
	
	@NotEmpty
	@Size(max = 20)
	private String birthDate;
	
	@NotEmpty
	@Size(max = 20)
	private String tel;
	
	@NotEmpty
	@Size(max = 20)
	private String introduction;

}