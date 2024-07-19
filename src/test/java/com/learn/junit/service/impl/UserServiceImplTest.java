package com.learn.junit.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
		assertEquals(userDTOReturn.getId(), userEntityCreated.getId());

	}

	@Test
	@DisplayName("delete User Test")
	void testDelete() {
		doNothing().when(userRepository).delete(any(UserEntity.class));
		when(userMapper.toEntity(any(UserDTO.class))).thenReturn(userEntity);
		userService.delete(userDTOCreated);
		verify(userRepository, times(1)).delete(any(UserEntity.class));
	}

	@Test
	@DisplayName("findByEmail User Test")
	void testFindByEmail() {
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

		when(userMapper.toDTO(any(UserEntity.class))).thenReturn(userDTOCreated);
		UserDTO userDTO = userService.findByEmail("jimenezjesuz@outlook.com");
		assertNotNull(userDTO);
	}

	@Test
	@DisplayName("findById User Test")
	void testfindById() {
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(userEntity));

		when(userMapper.toDTO(any(UserEntity.class))).thenReturn(userDTOCreated);
		UserDTO userDTO = userService.findById(1);
		assertNotNull(userDTO);
	}

	@Test
	@DisplayName("findAll User Test")
	void testFindAll() {
		when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntityCreated));

		when(userMapper.toDTOList(anyList())).thenReturn(Collections.singletonList(userDTOCreated));
		List<UserDTO> list = userService.findAll();
		assertNotNull(list);
	}

	@Test
	@DisplayName("findUsersCreatedToday User Test")
	void testFindUsersCreatedToday() {
		when(userRepository.findUsersCreatedToday()).thenReturn(Collections.singletonList(userEntityCreated));

		when(userMapper.toDTOList(anyList())).thenReturn(Collections.singletonList(userDTOCreated));
		List<UserDTO> list = userService.findUsersCreatedToday();
		assertNotNull(list);
	}

	@Test
	@DisplayName("findUsersCreatedByYear User Test")
	void testFindUsersCreatedByYear() {
		when(userRepository.findUsersCreatedByYear(anyInt())).thenReturn(Collections.singletonList(userEntityCreated));

		when(userMapper.toDTOList(anyList())).thenReturn(Collections.singletonList(userDTOCreated));
		List<UserDTO> list = userService.findUsersCreatedByYear(1995);
		assertNotNull(list);
	}
}
