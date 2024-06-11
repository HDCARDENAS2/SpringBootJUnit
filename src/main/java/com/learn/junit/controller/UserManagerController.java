package com.learn.junit.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learn.junit.dto.UserDTO;
import com.learn.junit.exception.BussinesException;
import com.learn.junit.exception.NotExistsException;
import com.learn.junit.service.UserManagerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/userManager")
@RequiredArgsConstructor
@Validated
@Log4j2
public class UserManagerController {

    private final UserManagerService userManagerService;

    @ApiOperation("Create a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) throws BussinesException {
        log.info("Entering createUser method with userDTO: {}", userDTO);
        UserDTO userCreated = userManagerService.create(userDTO);
        log.info("Exiting createUser method with UserDTO: {}", userCreated);
        return ResponseEntity.ok(userCreated);
    }
    
    @ApiOperation("Delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) throws NotExistsException {
        log.info("Entering create method with id: {}", id);
        userManagerService.delete(id);
        log.info("Exiting create method");
    }
  
}
