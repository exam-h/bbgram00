package com.example.bbgram.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class TeamForm {
	
	@NotEmpty
	@Size(max = 20)
	private String TeamId;
	
	@NotEmpty
	@Size(max = 20)
	private String name;

	@NotEmpty
	@Size(max = 20)
	private String read;
	
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
	@Size(max = 20)
	private String formation;

	@NotEmpty
	@Size(max = 50)
	private String frequency;
	
	@NotEmpty
	@Size(max = 25)
	private String activityDays;
	
	@NotEmpty
	@Size(max = 25)
	private String matchDays;
		
	@NotEmpty
	@Size(max = 100)
	private String teamIntroduction;

}
