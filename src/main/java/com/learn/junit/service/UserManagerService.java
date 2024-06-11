package com.learn.junit.service;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.exception.BussinesException;
import com.learn.junit.exception.NotExistsException;

public interface UserManagerService {
	
    UserDTO create(UserDTO userDTO) throws BussinesException;
	void delete(Integer id) throws NotExistsException;
}