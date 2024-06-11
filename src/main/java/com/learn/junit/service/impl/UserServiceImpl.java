package com.learn.junit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.entity.UserEntity;
import com.learn.junit.mapper.UserMapper;
import com.learn.junit.repository.UserRepository;
import com.learn.junit.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service("userService")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService {
	
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.info("Entering save method with userDTO: {}", userDTO);
        UserEntity userEntity = userMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDTO savedUserDTO = userMapper.toDTO(savedUserEntity);
        log.info("Exiting save method with savedUserDTO: {}", savedUserDTO);
        return savedUserDTO;
    }
    
    @Override
    public void delete(UserDTO userDTO) {
        log.info("Entering save method with userDTO: {}", userDTO);
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userRepository.delete(userEntity);
        log.info("Exiting delete method  ");
    }

    @Override
    public UserDTO findByEmail(String email) {
        log.info("Entering findByEmail method with email: {}", email);
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        log.info("Exiting findByEmail method with userDTO: {}", userDTO);
        return userDTO;
    }
    
    @Override
    public UserDTO findById(Integer id) {
        log.info("Entering findById method with id: {}", id);
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        UserDTO userDTO = userMapper.toDTO(userEntity);
        log.info("Exiting findById method with userDTO: {}", userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        log.info("Entering findAll method");
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = userMapper.toDTOList(userEntities);
        log.info("Exiting findAll method with userDTOs: {}", userDTOs);
        return userDTOs;
    }

    @Override
    public List<UserDTO> findUsersCreatedToday() {
    	 log.info("Entering findUsersCreatedToday method");
        List<UserEntity> usersCreatedToday = userRepository.findUsersCreatedToday();
        List<UserDTO> userDTOs = userMapper.toDTOList(usersCreatedToday);
        log.info("Exiting findUsersCreatedToday method with userDTOs: {}", userDTOs);
        return userDTOs;
    }
    
    @Override
    public List<UserDTO> findUsersCreatedByYear(Integer year) {
        log.info("Entering findUsersCreatedByYear method with year: {}", year);
        List<UserEntity> usersCreatedByYear = userRepository.findUsersCreatedByYear(year);
        List<UserDTO> userDTOs = userMapper.toDTOList(usersCreatedByYear);
        log.info("Exiting findUsersCreatedByYear method with userDTOs: {}", userDTOs);
        return userDTOs;
    }
}