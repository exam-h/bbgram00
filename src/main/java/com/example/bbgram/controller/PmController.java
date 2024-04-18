package com.example.bbgram.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bbgram.entity.Pm;
import com.example.bbgram.entity.Team;
import com.example.bbgram.entity.User;
import com.example.bbgram.entity.UserInf;
import com.example.bbgram.form.PmForm;
import com.example.bbgram.repository.PmRepository;
import com.example.bbgram.repository.UserRepository;

@Controller
public class PmController {
	@Autowired
	private PmRepository pmrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path = "/plusmemberboard/new")
	public String newMe(Model model) {
		model.addAttribute("form", new PmForm());
		return "plusmemberboard/new";
	}
	
	@RequestMapping(value = "/plusmemberboard/new", method = RequestMethod.POST)
	public String createPm(Principal principal,@ModelAttribute("form") PmForm form, Model model) {
		Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();
		Long userId = user.getUserId();
		User myuser = userrepository.findByUserId(userId);
		Team team = myuser.getTeam();
		model.addAttribute("form", new PmForm());
		String title = form.getTitle();
		String newbieposition = form.getNewbieposition();
		String prefecture = form.getPrefecture();
		String city= form.getCity();
		String age_min = form.getAge_min();
		String age_max = form.getAge_max();
		String frequency = form.getFrequency();
		String activityDays = form.getActivityDays();
		String matchDays = form.getMatchDays();
		String newplayer = form.getNewplayer();
		String team_pr = form.getTeam_pr();
				
		Pm entity = new Pm(title, newbieposition, prefecture, city, age_min, age_max,
				frequency, activityDays, matchDays, newplayer, team_pr, team);
		
		pmrepository.saveAndFlush(entity);
		return "redirect:/plusmemberboard";
	}
	@GetMapping(path = "/plusmemberboard")
	public String index(Principal principal, Model model) throws IOException {
		Authentication authentication = (Authentication) principal;
		List<Pm> pms = (List<Pm>) pmrepository.findAllByOrderByUpdatedAtDesc();
		List<PmForm> forms = new ArrayList<>();
		
		for(Pm pm:pms) {
			PmForm pmform = new PmForm();
			pmform.setId(pm.getId());
			pmform.setTitle(pm.getTitle());
			pmform.setPrefecture(pm.getPrefecture());
			pmform.setCity(pm.getCity());
			pmform.setFrequency(pm.getFrequency());
			pmform.setNewbieposition(pm.getNewbieposition());
			
			forms.add(pmform);
		}
		model.addAttribute("plusmemberboards", forms);
		
		return "plusmemberboard/index";
	}
	
	@RequestMapping(value = "/plusmemberboard/index", method = RequestMethod.GET)

	public String newPm(Model model) {
		model.addAttribute("form", new PmForm());
		return "plusmemberboard/index";
	}
	
	@GetMapping(path = "/plusmemberboard/{Id}")
	public String show(Principal principal, @PathVariable("Id") Long Id,Model model) throws IOException {
		Authentication authentication = (Authentication) principal;
		List<Pm> pms = (List<Pm>) pmrepository.findAllByOrderByUpdatedAtDesc();
		List<PmForm> forms = new ArrayList<>();
		for(Pm pm:pms) {
		PmForm pmform = new PmForm();
		pmform.setTitle(pm.getTitle());
		pmform.setNewbieposition(pm.getNewbieposition());
		pmform.setPrefecture(pm.getPrefecture());
		pmform.setCity(pm.getCity());
		pmform.setAge_min(pm.getAge_min());
		pmform.setAge_max(pm.getAge_max());
		pmform.setFrequency(pm.getFrequency());
		pmform.setActivityDays(pm.getActivityDays());
		pmform.setMatchDays(pm.getMatchDays());
		pmform.setNewplayer(pm.getNewplayer());
		pmform.setTeam_pr(pm.getTeam_pr());
		forms.add(pmform);
	}		
		model.addAttribute("plusmemberboards", forms);
		
		return "plusmemberboard/show";
	}
	
	public String newShow(Model model) {
		model.addAttribute("form", new PmForm());
		return "plusmemberboard/show";
	}
}