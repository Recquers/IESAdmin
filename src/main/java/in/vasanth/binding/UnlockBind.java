package in.vasanth.binding;

import lombok.Data;

@Data
public class UnlockBind {
	
	private String email;
	private String tempPwd;
	private String newPwd;
	private String cnfrmPwd;

}
