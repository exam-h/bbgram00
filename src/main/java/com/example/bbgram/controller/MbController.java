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

import com.example.bbgram.entity.Mb;
import com.example.bbgram.form.MbForm;
import com.example.bbgram.repository.MbRepository;


@Controller
public class MbController {
	@Autowired
	private ModelMapper modelMapper;

//	@Autowired
//	private HttpServletRequest request;

	@Autowired
	private MbRepository mbrepository;
	

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
		
		Mb entity = new Mb(dateandtime, prefecture, ground, title, referee, cost, helpmember, comments);
		
		mbrepository.saveAndFlush(entity);
		return "redirect:/matchboard";
	}
	
	@GetMapping(path = "/matchboard")
	public String index(Principal principal, Model model) throws IOException {
		Authentication authentication = (Authentication) principal;

		List<Mb> mbs = (List<Mb>) mbrepository.findAllByOrderByUpdatedAtDesc();
		//List<Mb> mbs = (List<Mb>) mbrepository.findByDeletedFalseOrderByUpdatedAtDesc();
		List<MbForm> forms = new ArrayList<>();
		
		for(Mb mb:mbs) {
			MbForm mbform = new MbForm();
			mbform.setId(mb.getId());
			mbform.setDateandtime(mb.getDateandtime());
			mbform.setPrefecture(mb.getPrefecture());
			mbform.setGround(mb.getGround());
			mbform.setTitle(mb.getTitle());
			forms.add(mbform);
		}
		
		
		model.addAttribute("matchboards", forms); //model.addAttributeでmatchboards→forbsで呼び出す

		return "matchboard/index";

	}

	@RequestMapping(value = "/matchbord/index", method = RequestMethod.GET)

	public String newMb(Model model) {
		model.addAttribute("form", new MbForm());
		return "matchboard/index";
	}
	
	@GetMapping(path = "/matchboard/{Id}")
	public String show(Principal principal,@PathVariable("Id") Long Id, Model model) throws IOException {
		Authentication authentication = (Authentication) principal;
		//↓
		List<Mb> mbs = (List<Mb>) mbrepository.findAllByOrderByUpdatedAtDesc();
		List<MbForm> forms = new ArrayList<>();
		
		for(Mb mb:mbs) {
			MbForm mbform = new MbForm();
			mbform.setTitle(mb.getTitle());
			mbform.setId(mb.getId());
			mbform.setDateandtime(mb.getDateandtime());
			mbform.setPrefecture(mb.getPrefecture());
			mbform.setGround(mb.getGround());
			mbform.setReferee(mb.getReferee());
			mbform.setCost(mb.getCost());
			mbform.setHelpmember(mb.getHelpmember());
			mbform.setComments(mb.getComments());		
			forms.add(mbform);
		}
		model.addAttribute("matchboards", forms); //model.addAttributeでmatchboards→forbsで呼び出す

		return "matchboard/show";

	}
	public String newShow(Model model) {
		model.addAttribute("form", new MbForm());
		return "matchboard/show";
	}
}
