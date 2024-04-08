package com.example.bbgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bbgram.entity.Team;
import com.example.bbgram.form.TeamForm;
import com.example.bbgram.repository.TeamRepository;

@Controller
public class TeamController {
	
	@Autowired
	TeamRepository teamrepository;
	
	@RequestMapping(value = "/teampage/new", method = RequestMethod.GET)
	public String newTeam(Model model) {
		model.addAttribute("form", new TeamForm());
		return "teampage/new";
	}
	
	@RequestMapping(value = "/teampage/create", method = RequestMethod.POST)
	public String createTeam(@ModelAttribute("form") TeamForm form,Model model) {
		model.addAttribute("form", new TeamForm());
		String name = form.getName();
		String read = form.getRead();
		String prefecture = form.getPrefecture();
		String city = form.getCity();
		String experience = form.getExperience();
		String formation = form.getFormation();
		String frequency = form.getFrequency();
		String activityDays = form.getActivityDays();
		String matchDays = form.getMatchDays();
		String teamIntroduction = form.getTeamIntroduction();
		
		Team entity = new Team(name, read,prefecture, city, experience, formation, frequency, activityDays, matchDays, teamIntroduction);
		
		teamrepository.saveAndFlush(entity);
		return "redirect:/teampage/" + entity.getTeamId();
		
	}
	
}
	