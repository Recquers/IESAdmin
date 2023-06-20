package in.vasanth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PlansDtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private String startDate;
	private String endDate;
	private String planCategory;
	private Boolean status = true;
	
	

}
