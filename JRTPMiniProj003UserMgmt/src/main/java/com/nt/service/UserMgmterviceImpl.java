
package com.nt.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	/*@Override
	public String activateUserAccount(ActivateUser user) {
		UserMaster master=new UserMaster();
		master.setEmail(user.getEmail());
		master.setPassword(user.getTempPassword());
		Example<UserMaster> example=Example.of(master);
		List<UserMaster> list=userMasterRepo.findAll(example);
		
		if(list.size()!=0) {
			UserMaster entity=list.get(0);
			entity.setPassword(user.getConfirmPassword());
			entity.setActive_Sw("Active");
			UserMaster updatedEntity=userMasterRepo.save(entity);
			return "User is activated with new password";
		}
		return "User Not Found to activate";
	}

	@Override
	public String login(LoginCredentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	@Override
	public String activateUserAccount(ActivateUser user) {
		UserMaster entity=userMasterRepo.findByEmailAndPassword(user.getEmail(),user.getTempPassword());

		if(entity==null) {
			return "User is Not Found for activation";
		}
		else {
			entity.setPassword(user.getConfirmPassword());
			entity.setActive_Sw("Active");
			UserMaster updatedEntity=userMasterRepo.save(entity);
			return "User is Activated with new Password";
		}
	}
	
	@Override
	public String login(LoginCredentials credentials) {
		UserMaster master=new UserMaster();
		BeanUtils.copyProperties(credentials, master);
		Example<UserMaster> exmple=Example.of(master);
		List<UserMaster> listEntities=userMasterRepo.findAll(exmple);
		if(listEntities.size()==0) {
			return " Invalid Credentials";
		}
		else {
			UserMaster entity=listEntities.get(0);
			if(master.getActive_Sw().equalsIgnoreCase("Active")) {
				return " Valid Credentials and login successul";
			}
			else {
				return " User Account is Not Active";
			}
		}
	}

	@Override
	public String recoverPassword(RecoverPassword recover) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccount> listUsers() {
		//Report Generation using ForEach method
		 
		 List<UserMaster> listEntities=userMasterRepo.findAll();
		
		List<UserAccount> listUser=new ArrayList();
		listEntities.forEach(entity->{
			UserAccount user=new UserAccount();
			BeanUtils.copyProperties(listEntities,user);
			listUser.add(user);
		});
		return listUser; 
		
		//report generation using Stream API
		
		/*List<UserMaster> listEntities=userMasterRepo.findAll();
		List<UserAccount> listUsers=listEntities.stream().map(entity->{
			UserAccount user=new UserAccount();
			BeanUtils.copyProperties(entity, user);
		}).toList();*/
	}

	@Override
	public UserAccount showUsersById(Integer id) {
		Optional<UserMaster> opt=userMasterRepo.findById(id);
		UserAccount account=null;
		if(opt.isPresent()) {
		account=new UserAccount();
			BeanUtils.copyProperties( opt.get(),account);
		}
		return account;
	}

	@Override
	public UserAccount showUsersByEmailAndName(String email, String name) {
		//using Custom Finder method
		UserMaster master=userMasterRepo.fingByNameAndEmail(name, email);
		UserAccount account=null;
		if(master!=null) {
			account=new UserAccount();
			BeanUtils.copyProperties(master, account);
		}
		return account;
	}

	@Override
	public String updateUser(UserAccount user) {
		//using Custom Finder method
				UserMaster master=userMasterRepo.fingByNameAndEmail(user.getName(), user.getEmail());
				if(master!=null) {
					BeanUtils.copyProperties(user, master);
					userMasterRepo.save(master);
					return "User Details are Updated";
				}
				else {
					return "User not Found for Updation";
				}
	}

	@Override
	public String deleteUserById(Integer id) {
		Optional<UserMaster> opt=userMasterRepo.findById(id);
		if(opt.isPresent()) {
			userMasterRepo.deleteById(id);
			return "User is deleted";
		}
		else {
			return "User is not Found to delete";
		}
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		Optional<UserMaster> opt=userMasterRepo.findById(id);
		if(opt.isPresent()) {
			UserMaster master=opt.get();
			master.setActive_Sw(status);
			userMasterRepo.save(master);
			return "User Status Changed";
		}
		return "User Not Found for changing the status";
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
