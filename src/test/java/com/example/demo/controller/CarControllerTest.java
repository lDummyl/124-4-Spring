package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chervinko <br>
 * 02.08.2021
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CarControllerTest {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder<DefaultMockMvcBuilder> builder = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }

    @Test
    public void create() throws Exception {
        String uri = "/car";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON).content("{\"make\":\"VW\",\"model\":\"Tiguan\",\"registration_number\":\"О320ХУ172\"}"))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"make\":\"VW\",\"model\":\"Tiguan\"}"));
    }

    @Test
    public void getOne() throws Exception {
        String uri = "/car/{id}";
        mockMvc.perform(get(uri, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"make\":\"VW\",\"model\":\"Tiguan\"}"));
    }

    @Test
    public void getAll() throws Exception {
        String uri = "/car";
        mockMvc.perform(get(uri, 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"make\":\"VW\",\"model\":\"Tiguan\"}]"));
    }

    @Test
    public void update() throws Exception {
        String uri = "/car";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON).content("{\"id\": 1,\"make\":\"VW\",\"model\":\"Tiguan II\",\"registration_number\":\"О320ХУ172\"}"))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"make\":\"VW\",\"model\":\"Tiguan II\"}"));
    }

    @Test
    public void drop() throws Exception {
        String uri = "/car/{id}";
        mockMvc.perform(delete(uri, 1))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }
}
