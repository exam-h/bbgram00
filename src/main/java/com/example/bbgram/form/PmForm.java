package com.example.bbgram.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PmForm {
	
	@NotEmpty
	private Long id;

	@NotEmpty
	@Size(max = 30)
	private String title;
	
	@NotEmpty
	@Size(max = 30)
	private String newbieposition;
	
	@NotEmpty
	@Size(max = 25)
	private String prefecture;
	
	@NotEmpty
	@Size(max = 25)
	private String city;
	
	@NotEmpty
	@Size(max = 30)
	private String age_min;
	
	@NotEmpty
	@Size(max = 30)
	private String age_max;
	
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
	@Size(max = 30)
	private String newplayer;
	
	@NotEmpty
	@Size(max = 100)
	private String team_pr;
}
