package com.example.bbgram.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MbForm {
	
	@NotEmpty
	private Long id;

	@NotEmpty
	@Size(max = 30)
	private String dateandtime;
	
	@NotEmpty
	@Size(max = 25)
	private String prefecture;
	
	@NotEmpty
	@Size(max = 30)
	private String ground;
	
	@NotEmpty
	@Size(max = 30)
	private String title;

	@NotEmpty
	@Size(max = 25)
	private String referee;

	@NotEmpty
	@Size(max = 30)
	private String cost;

	@NotEmpty
	@Size(max = 10)
	private String helpmember;

	@NotEmpty
	@Size(max = 1000)
	private String comments;

	@NotEmpty
	@Size(max = 10)
	private String apply_end;

}
