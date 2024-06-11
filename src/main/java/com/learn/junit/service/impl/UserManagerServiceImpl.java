package com.learn.junit.service.impl;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.event.UserCreatedEvent;
import com.learn.junit.exception.BussinesException;
import com.learn.junit.exception.NotExistsException;
import com.learn.junit.service.UserManagerService;
import com.learn.junit.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service("userManagerService")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserManagerServiceImpl implements UserManagerService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;
    
	/**
	 * Creates a new user with the provided userDTO.
	 *
	 * @param userDTO The userDTO object containing the user information.
	 * @return The UserDTO object representing the created user.
	 * @throws BussinesException If the email already exists.
	 */
    @Override
	public UserDTO create(UserDTO userDTO) throws BussinesException {
		log.info("Entering newUser method with userDTO: {}", userDTO);
		
		if(Optional.ofNullable(userService.findByEmail(userDTO.getEmail())).isPresent()) {
			throw new BussinesException("The email already exists");
		}
		
		UserDTO userDTOCreated = userService.save(userDTO);
        eventPublisher.publishEvent(new UserCreatedEvent(this, userDTOCreated)); 
        log.info("Entering newUser method with userDTO: {}", userDTO);
        return userDTOCreated;   
	}
    
	/**
	 * Deletes a user with the specified ID.
	 *
	 * @param id the ID of the user to delete
	 * @throws NotExistsException if the user with the specified ID does not exist
	 */
    @Override
   	public void delete(Integer id) throws NotExistsException {
   		log.info("Entering delete method with id: {}", id);
   		
   		Optional<UserDTO> userDTOOpt = Optional.ofNullable(userService.findById(id));
   		
   		if(userDTOOpt.isEmpty()) {
   			throw new NotExistsException(String.format("User ID %s does not exist", id));
   		}
   		
   		userService.delete(userDTOOpt.get());
        
        log.info("Exiting delete method");
         
   	}
	
}
