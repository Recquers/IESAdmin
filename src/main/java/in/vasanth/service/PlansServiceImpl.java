package in.vasanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vasanth.binding.CreatePlans;
import in.vasanth.entity.PlansDtls;
import in.vasanth.repository.PlanDtlsRepo;

@Service
public class PlansServiceImpl implements PlansService {
	
	@Autowired
	private PlanDtlsRepo planRepo;

	@Override
	public String savePlan(CreatePlans plans) {
		
		PlansDtls plan=new PlansDtls();
		BeanUtils.copyProperties(plans,plan);
		PlansDtls save = planRepo.save(plan);
		if(save.getPlanId()!=null) {
			return "Action Completed";
		}
		else {
			return "Error Occured, Try Again";
		}
		
		
	}

	@Override
	public List<PlansDtls> getPlanData() {
		return planRepo.findAll();
		
	}

	@Override
	public PlansDtls editPlan(Integer planId) {
		        
		Optional<PlansDtls> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
				
	}

	@Override
	public Boolean planStatus(Integer planId) {
		Optional<PlansDtls> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			PlansDtls plansDtls = findById.get();
			Boolean status = plansDtls.getStatus();
			if(status==true) {
				plansDtls.setStatus(false);
				planRepo.save(plansDtls);
			}else {
				plansDtls.setStatus(true);
				planRepo.save(plansDtls);
			}
		}
		return true;
		
	}


	
	

}
