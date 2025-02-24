package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JRTP_USER_MASTER")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(length = 30)
	private String name;	
	@Column(length=10)
	private String password;
	@Column(length = 90)
	private String email;
	private Long mobileNo;
	private Long aadharNo;
	@Column(length = 30)
	private String gender;
	private LocalDate dob;
	@Column(length = 30)
	private String active_Sw;
	
	//Meta Data
	@CreationTimestamp
	@Column(updatable = false,insertable = true)
	private LocalDateTime createdOn;
	@Column(insertable=false,updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	@Column(length = 30)
	private String createdBy;
	@Column(length = 30)
	private String updatedBy;
	
}
