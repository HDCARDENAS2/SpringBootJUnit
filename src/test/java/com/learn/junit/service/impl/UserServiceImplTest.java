package com.learn.junit.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
		assertEquals(userDTOReturn.getId(),userEntityCreated.getId());
	
		
	}
	

    @Test
    @DisplayName("findByEmail Test")
    void testFindByEmail() {
        // creo objetos
        String email = "test@example.com";
        UserEntity mockUserEntity = new UserEntity(); 
        UserDTO mockUserDTO = new UserDTO();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUserEntity));
        when(userMapper.toDTO(any(UserEntity.class))).thenReturn(mockUserDTO);

        // cosumo
        UserDTO result = userService.findByEmail(email);

  
        assertNotNull(result, "The returned UserDTO should not be null");
        verify(userRepository).findByEmail(email);
        verify(userMapper).toDTO(mockUserEntity);
    }
    
    @Test
    @DisplayName("findById Test")
    void testFindById() {
    	
    	 Integer id = 1;
    	 UserEntity mockUserEntity = new UserEntity(); 
    	  UserDTO mockUserDTO = new UserDTO();
    	 when(userRepository.findById(id)).thenReturn(Optional.of(mockUserEntity));
    	 when(userMapper.toDTO(any(UserEntity.class))).thenReturn(mockUserDTO);
    	 // cosumo
         UserDTO result = userService.findById(id);
         assertNotNull(result, "The returned UserDTO should not be null");
        verify(userMapper).toDTO(mockUserEntity);
    }
    
    
    @Test
    @DisplayName("findAll devuelve una lista de UserDTO exitosamente")
    void testFindAll() {
        // Preparación
        List<UserEntity> mockUserEntities = Arrays.asList(new UserEntity(), new UserEntity()); 
        List<UserDTO> mockUserDTOs = Arrays.asList(new UserDTO(), new UserDTO());
        when(userRepository.findAll()).thenReturn(mockUserEntities);
        when(userMapper.toDTOList(mockUserEntities)).thenReturn(mockUserDTOs);

  
        List<UserDTO> resultados = userService.findAll();

        // Verificación
        assertNotNull(resultados, "La lista de UserDTO devuelta no debe ser nula");
        assertEquals(2, resultados.size(), "La lista de UserDTO debe contener dos elementos");
        verify(userRepository).findAll();
        verify(userMapper).toDTOList(mockUserEntities);
    }

    @Test
    @DisplayName("findUsersCreatedToday devuelve una lista de UserDTO exitosamente")
    void testFindUsersCreatedToday() {
        // Preparación similar a testFindAll
        List<UserEntity> mockUserEntities = Arrays.asList(new UserEntity()); 
        List<UserDTO> mockUserDTOs = Arrays.asList(new UserDTO());
        when(userRepository.findUsersCreatedToday()).thenReturn(mockUserEntities);
        when(userMapper.toDTOList(mockUserEntities)).thenReturn(mockUserDTOs);

     
        List<UserDTO> resultados = userService.findUsersCreatedToday();

     
        assertNotNull(resultados, "La lista de UserDTO devuelta no debe ser nula");
        assertEquals(1, resultados.size(), "La lista de UserDTO debe contener un elemento");
        verify(userRepository).findUsersCreatedToday();
        verify(userMapper).toDTOList(mockUserEntities);
    }

    @Test
    @DisplayName("findUsersCreatedByYear devuelve una lista de UserDTO exitosamente")
    void testFindUsersCreatedByYear() {
       
        Integer year = 2020;
        List<UserEntity> mockUserEntities = Arrays.asList(new UserEntity()); 
        List<UserDTO> mockUserDTOs = Arrays.asList(new UserDTO());
        when(userRepository.findUsersCreatedByYear(year)).thenReturn(mockUserEntities);
        when(userMapper.toDTOList(mockUserEntities)).thenReturn(mockUserDTOs);

 
        List<UserDTO> resultados = userService.findUsersCreatedByYear(year);

        // Verificación
        assertNotNull(resultados, "La lista de UserDTO devuelta no debe ser nula");
        assertEquals(1, resultados.size(), "La lista de UserDTO debe contener un elemento");
        verify(userRepository).findUsersCreatedByYear(year);
        verify(userMapper).toDTOList(mockUserEntities);
    }
}
