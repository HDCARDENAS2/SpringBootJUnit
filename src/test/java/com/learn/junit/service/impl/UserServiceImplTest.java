package com.learn.junit.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.entity.UserEntity;
import com.learn.junit.helper.UserHelper;
import com.learn.junit.mapper.UserMapper;
import com.learn.junit.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
    private UserServiceImpl userService;
	@Mock
	private UserMapper userMapper;
	@Mock
    private UserRepository userRepository;
	
	private UserDTO userDTO = UserHelper.newUserDTO();
	private UserDTO userDTOCreated = UserHelper.createdUserDTO();
	private UserEntity userEntity = UserHelper.newUserEntity();
	private UserEntity userEntityCreated = UserHelper.createdUserEntity();

	@Test
	@DisplayName("Save User Test")
	void testSave() {
	
		when(userMapper.toEntity(any(UserDTO.class))).thenReturn(userEntity);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityCreated);
		when(userMapper.toDTO(any(UserEntity.class))).thenReturn(userDTOCreated);
	    UserDTO userDTOReturn = userService.save(userDTO);
		assertNotNull(userDTOReturn);
		assertEquals(userDTOReturn.getId(),userEntityCreated.getId());
	
		
	}

}
