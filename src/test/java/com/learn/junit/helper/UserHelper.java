package com.learn.junit.helper;

import java.util.Date;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.entity.UserEntity;

public class UserHelper {

	private UserHelper() {
		
	}

	public static UserDTO newUserDTO() {
		return UserDTO.builder().name("test").email("test").build();
	}
	
	public static UserDTO createdUserDTO() {
		return UserDTO.builder().id(1).name("test").email("test").createdAt(new Date()).build();	
	}

	public static UserEntity newUserEntity() {
		return UserEntity.builder().name("test").email("test").build();
	}
	
	public static UserEntity createdUserEntity() {
		return UserEntity.builder().id(1).name("test").email("test").createdAt(new Date()).build();	
	}
	
	
	
}
