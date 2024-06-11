package com.learn.junit.service.control;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.learn.junit.controller.UserController;
import com.learn.junit.dto.UserDTO;
import com.learn.junit.helper.UserHelper;
import com.learn.junit.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration(classes = {UserController.class, UserController.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDTO userDTO = UserHelper.createdUserDTO();

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
   
    }

    @Test
    public void testFindUserByIdUserExists() throws Exception {
        when(userService.findById(1)).thenReturn(userDTO);

        mockMvc.perform(get("/user/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(userDTO.getId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }

    @Test
    public void testFindUserByIdUserNotExist() throws Exception {
        when(userService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/user/1")).andExpect(status().isNoContent());
    }

}