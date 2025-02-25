package com.nt.service;

import java.util.List;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPassword;
import com.nt.bindings.UserAccount;

public interface UserMgmtService {
	
	public String registerUser(UserAccount user) throws Exception;
	public String activateUserAccount(ActivateUser user);
	public String login(LoginCredentials credentials);
	public String recoverPassword(RecoverPassword recover);
	public List<UserAccount> listUsers();
	public UserAccount showUsersById(Integer id);
	public UserAccount showUsersByEmailAndName(String email,String name);
	public String updateUser(UserAccount user);
	public String deleteUserById(Integer id);
	public String changeUserStatus(Integer id,String status);
	
}
