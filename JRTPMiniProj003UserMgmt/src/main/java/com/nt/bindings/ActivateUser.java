package com.nt.bindings;

import java.time.LocalDate;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateUser {
	
	private String Email;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
}
