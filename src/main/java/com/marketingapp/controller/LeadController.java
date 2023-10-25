package com.marketingapp.controller;  //08.02.23
//kindly check video  08.02.23 time 16.20
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadDto;
import com.marketingapp.entities.Lead;
import com.marketingapp.service.LeadService;
import com.marketingapp.util.Emailservice;
//Servlets are outdated.So,spring comes with annotations i.e. @controller.
//Here you can see ,there is no doget() and dopost() method.
@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	//localhost:8080/create
	
	@Autowired
	private Emailservice emailService;
	
	@RequestMapping("/create")  //@WebServlet
	public String viewCreateLeadForm() {
		return "create_lead";   //works as requestdispatcher //RequestDispatcher
	}

//   @RequestMapping("/saveReg")
//   public String saveOneLead(@ModelAttribute Lead lead) {
//	   leadService.saveReg(lead);
//	   return "create_lead";
//   }
	
  @RequestMapping("/saveReg")
  public String saveOneLead(@ModelAttribute Lead lead, Model model) {
	  model.addAttribute("msg","Lead saved");
	   leadService.saveReg(lead);
	   emailService.sendEmail(lead.getEmail(), "Test", "Testing Email Services");
	   return "create_lead";
  }
	
//	
//	@RequestMapping("/saveReg")
//	   public String saveOneLead(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email,@RequestParam("mobile") long mobile, ModelMap model) {
//		Lead l = new Lead(); 
//		l.setFirstName(firstName);
//		l.setLastName(lastName);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		
//		model.addAttribute("msg","Lead is saved!");
//		leadService.saveReg(l);
//		   return "create_lead";
//	   }
	
//	 @RequestMapping("/saveReg")
//	  public String saveOneLead(LeadDto dto, Model model) {
//		 Lead lead = new Lead();
//		 lead.setFirstName(dto.getFirstName());
//		 lead.setLastName(dto.getLastName());
//		 lead.setEmail(dto.getEmail());
//		 lead.setMobile(dto.getMobile());
//		  model.addAttribute("msg","Lead saved");
//		   leadService.saveReg(lead);
//		   return "create_lead";
//	  }
  
//localhost:8080/listall
  @RequestMapping("/listall")
  public String getAllLeads(Model model) {//model acts like request.setAttribute and request.getAttribute
	  List<Lead> leads = leadService.findAllLeads();
	  model.addAttribute("leads",leads);
	  return "list_leads";
  }
  
//localhost:8080/delete
  @RequestMapping("/delete")
  public String deleteLeadById(@RequestParam("id") long id,Model model) {
	  leadService.deleteLeadById(id);
	  List<Lead> leads = leadService.findAllLeads();
	  model.addAttribute("leads",leads);
	  return "list_leads";
  }
  
  @RequestMapping("/update")
  public String updateLead(@RequestParam("id") long id, Model model) {
	  Lead lead = leadService.findAllLeads(id);
	  model.addAttribute("lead",lead);
	  return "update_lead";
  }
  
  @RequestMapping("/editReg")
  public String editReg(LeadDto dto,Model model) {
	  
	  Lead lead = new Lead();
	  lead.setId(dto.getId());
	  lead.setFirstName(dto.getFirstName());
	  lead.setLastName(dto.getLastName());
	  lead.setEmail(dto.getEmail());
	  lead.setMobile(dto.getMobile());
	  
	  leadService.saveReg(lead);
	  
	  List<Lead> leads = leadService.findAllLeads();
	  model.addAttribute("leads",leads);
	  return "list_leads";
  }
	
}
// modelattribute here is nothing but you are reading the form data and that form data,you are putting that
// into that entity object.attributes of entity.

//there are three ways to read the data from the form:-
//1. take the data and put that into entity class
//2. take the data and use requestparam and 3. DTO

// Model= It finds the controller data with the view.

// Webservices- the concept of integrating two different/same technologies where your exchanging the data
// will be them via the standard protocol is called as webservices.

//to run web applications, you have to use tomcat .so import tomcat dependency in pom.xml
