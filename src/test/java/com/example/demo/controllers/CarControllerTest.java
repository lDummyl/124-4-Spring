package com.example.demo.controllers;

import com.example.demo.dataBases.repositories.CarRepository;
import com.example.demo.dto.CarDetails;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
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

import javax.annotation.PostConstruct;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class CarControllerTest {

    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarService carService;
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder<DefaultMockMvcBuilder> builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

           carInit();
    }

    public void carInit() {
        carService.init("VW", 1L);
        carService.init("BMW", 2L);
        carService.init("LADA", 3L);
        carService.init("FORD", 4L);
        carService.init("VOLGA", 5L);
    }

    @Test
    public void getCar() throws Exception {

        String uri = "/car/id/{id}";
        mockMvc.perform(get(uri,1L).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void getByModel() throws Exception {
        String uri = "/car/model/{model}";
        mockMvc.perform(get(uri,"VW").contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }



    @Test
    public void getAllCars() throws Exception {

        String uri = "/car/getAllCars";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void initCar() throws Exception {

        String uri = "/car/initCar";
        CarDetails carDetails = new CarDetails();
        carDetails.setModel("VW");
        carDetails.setId(1L);
        String content = objectMapper.writeValueAsString(carDetails);
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());


    }

    @Test
    public void carInitialisation() throws Exception {

        String uri = "/car/createCar";
        CarDetails carDetails = new CarDetails();
        carDetails.setModel("VW");
        carDetails.setId(1L);
        String content = objectMapper.writeValueAsString(carDetails);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void deletingCar() throws Exception {

        String uri = "/car/{id}";
        mockMvc.perform(delete(uri, 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

    }

    @Test
    public void updatingCar() throws Exception {

        String uri = "/car/updatingCar/{id}";
        CarDetails carDetails = new CarDetails();
        carDetails.setModel("VW");
        carDetails.setId(1L);
        String content = objectMapper.writeValueAsString(carDetails);
        mockMvc.perform(put(uri, 1).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());


    }
}