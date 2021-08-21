package com.example.demo.controllers;

import com.example.demo.dataBases.repositories.UserRepo;
import com.example.demo.dto.UserDetails;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class UserControllerTest {

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp(){
        ConfigurableMockMvcBuilder<DefaultMockMvcBuilder> builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
//        userInit();
    }

    public void userInit(){
        userService.init(1L, "Vova", 1989, 1L);
        userService.init(1L, "Petia", 1977, 2L);

    }

    @Test
    public void getUser() throws Exception {

        String uri = "/user/id/{id}";
        mockMvc.perform(get(uri,1L).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void findByName() throws Exception {

        String uri = "/user/userName/{name}";
        mockMvc.perform(get(uri,"Vova").contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllUser() throws Exception {

        String uri = "/user/getAllUsers";
        mockMvc.perform(get(uri,1L).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void initUser() throws Exception {

        String uri = "/user/initUser";
        UserDetails userDetails = new UserDetails();
        userDetails.setName("Vova");
        userDetails.setYearOfBirth(1989);
        userDetails.setId(1L);
        userDetails.setCarId(1L);
        String content = objectMapper.writeValueAsString(userDetails);
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());


    }

    @Test
    public void addingUser() throws Exception {

        String uri = "/user/addUser";
        UserDetails userDetails = new UserDetails();
        userDetails.setName("Vova");
        userDetails.setYearOfBirth(1989);
        userDetails.setId(1L);
        userDetails.setCarId(1L);
        String content = objectMapper.writeValueAsString(userDetails);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void deletingUser() throws Exception {

        String uri = "/user/delete/{id}";
        mockMvc.perform(delete(uri,1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void updatingUser() throws Exception {

        String uri = "/user/updateUser/{id}";
        UserDetails userDetails = new UserDetails();
        userDetails.setName("Vovan");
        userDetails.setYearOfBirth(1989);
        userDetails.setId(1L);
        userDetails.setCarId(1L);
        String content = objectMapper.writeValueAsString(userDetails);
        mockMvc.perform(put(uri, 1).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }
}