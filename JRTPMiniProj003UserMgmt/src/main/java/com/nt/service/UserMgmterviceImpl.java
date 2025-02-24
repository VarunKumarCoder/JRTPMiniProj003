package com.nt.service;

import java.util.List;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPassword;
import com.nt.bindings.UserAccount;

public class UserMgmterviceImpl implements UserMgmtService {

	@Override
	public String registerUser(UserAccount user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String activateUserAccount(ActivateUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(LoginCredentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recoverPassword(RecoverPassword recover) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccount> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount showUsersById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount showUsersByEmailAndName(String email, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(UserAccount user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
