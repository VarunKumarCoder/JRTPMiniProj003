
package com.nt.service;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.ActivateUser;
import com.nt.bindings.LoginCredentials;
import com.nt.bindings.RecoverPassword;
import com.nt.bindings.UserAccount;
import com.nt.entity.UserMaster;
import com.nt.repository.IUserMasterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserMgmterviceImpl implements UserMgmtService {
	@Autowired
	private IUserMasterRepository userMasterRepo;
	@Override
	public String registerUser(UserAccount user) {
		log.info("Converting the UserAccount obj data into UserMaster object");
		UserMaster master=new UserMaster();
		log.info("Copy using BeanUtils");
		BeanUtils.copyProperties(user, master);
		log.info("Calling private generate random password method ");
		master.setPassword(generateRandomPassword(6));
		log.info("Set active switch status");
		master.setActive_Sw("InActive");
		UserMaster saveMaster=userMasterRepo.save(master);
		//TODO :: Send the mail
		
		log.info("At the Return statement using ternery operator and check the row is null or not");
		return saveMaster!=null?"Uer is registered with Id value::"+saveMaster.getUserId():" Problem in user registration";
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
	
	private String generateRandomPassword(int length) {
		String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder randomWord=new StringBuilder(length);
		int i;
		for(i=0;i<length;i++) {
			int ch=(int) (AlphaNumericStr.length()*Math.random());
			randomWord.append(AlphaNumericStr.charAt(ch));
		}
		return randomWord.toString();
	}

}
