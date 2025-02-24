package com.nt.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateUser {
	
	private String Email;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
}
