package com.maximbuza.appaston.controller;

import com.maximbuza.appaston.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private MyController myController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController).build();
    }

    @Test
    public void showAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList("user1", "user2").toString());
        mockMvc.perform(get("/api/getAllUsers")).andExpect(status().isOk());
        Mockito.verify(userService, times(1)).getAllUsers();
    }

}
