package com.example.bbgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bbgram.entity.Me;
import com.example.bbgram.form.MbForm;
import com.example.bbgram.repository.MeRepository;

@Controller
public class MeController {
	@Autowired
	private MeRepository merepository;
	
	@GetMapping(path = "/matchboard/new")
	public String newMe(Model model) {
		model.addAttribute("form", new MbForm());
		return "matchboard/new";
	}
	
	@RequestMapping(value = "/matchboard/new", method = RequestMethod.POST)
	public String createMe(@ModelAttribute("form") MbForm form, Model model) {
		model.addAttribute("form", new MbForm());
		String dateandtime = form.getDateandtime();
		String prefecture = form.getPrefecture();
		String ground = form.getGround();
		String title = form.getTitle();
		String referee = form.getReferee();
		String cost = form.getCost();
		String helpmember = form.getHelpmember();
		String comments = form.getComments();
		String apply_end = form.getApply_end();
		
		Me entity = new Me(dateandtime, prefecture, ground, title, referee, cost, helpmember, comments, apply_end);
		
		merepository.saveAndFlush(entity);
		return "redirect:/matchboard";
		
		
	}
}