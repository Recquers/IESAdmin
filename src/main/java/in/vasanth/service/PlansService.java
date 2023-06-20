package in.vasanth.service;

import java.util.List;

import in.vasanth.binding.CreatePlans;
import in.vasanth.entity.PlansDtls;

public interface PlansService {
	
	
	public String savePlan(CreatePlans plans);
	
	public List<PlansDtls> getPlanData();
	
	public PlansDtls editPlan(Integer planId);
	
	public Boolean planStatus(Integer planId);

}
