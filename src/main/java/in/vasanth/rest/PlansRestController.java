package in.vasanth.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.vasanth.binding.CreatePlans;
import in.vasanth.entity.PlansDtls;
import in.vasanth.service.PlansService;

@RestController
public class PlansRestController {
	
	@Autowired
	private PlansService service;
	
		
	@PostMapping(value="/createPlan",
			consumes= {"application/json"},
			produces= {"application/json"}		
			)
	public ResponseEntity<String> createPlans(@RequestBody CreatePlans plan) {
		String status = service.savePlan(plan);
		
		return new ResponseEntity<String>(status, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/viewPlans",
			produces= {"application/json"})
	public List<PlansDtls> viewPlans(){
		
		return service.getPlanData();
	}
	
	 @GetMapping(value="/editPlan/{planId}", produces= {"application/json"} ) 
	 public PlansDtls editPlan(@PathVariable Integer planId) {
		 return service.editPlan(planId);
	 }

	 @GetMapping(value="/deletePlan/{planId}", produces= {"application/json"} ) 
	 public Boolean deletePlan(@PathVariable Integer planId) {
		 return service.planStatus(planId);
	 }
	

}
