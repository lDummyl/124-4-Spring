package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DriverControllerTest {

    MockMvc mockMvc;
    String URI_CONTROLLER = "/drivers";

    private DriverDTO testDriver1;
    private DriverDTO testDriver2;
    private Set<DriverDTO> driverSet;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DriverService driverService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
        initTestData();
    }

    private void initTestData() {
        //TODO: С подключение БД перенести в init.sql и отказаться от Service
        clearTestData();

        testDriver1 = new DriverDTO();
        testDriver1.setId(101L);
        testDriver1.setName("Alexander");
        testDriver1.setSecondName("A");
        testDriver1.setLastName("V");
        CarDTO testCar1 = new CarDTO();
        CarDTO testCar2 = new CarDTO();
        Set<CarDTO> testCars = new HashSet<>();
        testCars.add(testCar1);
        testCars.add(testCar2);
        testDriver1.setCars(testCars);
        driverService.add(testDriver1);

        testDriver2 = new DriverDTO();
        testDriver2.setId(102L);
        testDriver1.setName("Ivan");
        testDriver1.setSecondName("S");
        testDriver1.setLastName("P");
        testCar1 = new CarDTO();
        testCar2 = new CarDTO();
        testCars = new HashSet<>();
        testCars.add(testCar1);
        testCars.add(testCar2);
        driverService.add(testDriver2);

        driverSet = driverService.getAll();
    }

    private void clearTestData() {
        for (DriverDTO delDriver : driverService.getAll()) {
            driverService.delete(delDriver.getId());
        }
    }


    @Test
    public void readAll_get_all_jsonSet_succ() throws Exception {
        String uri = URI_CONTROLLER;

        String responceJson = mockMvc.perform(get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(responceJson, objectMapper.writeValueAsString(driverSet));
    }

    @Test
    public void read_driver_by_id_succ() throws Exception {
        Long id = testDriver1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString().equals(objectMapper.writeValueAsString(testDriver1));
    }

    @Test
    public void read_driver_by_id_fail() throws Exception {
        Long id = testDriver1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        clearTestData();

        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals(Objects.requireNonNull(result.getResolvedException()).getMessage(), "Could not find data by " + id));
    }

    @Test
    public void add_new_driver_by_post_jsondto_succ() throws Exception {
        String uri = URI_CONTROLLER;

        clearTestData();

        DriverDTO testNewDriver = new DriverDTO();
        testNewDriver.setName("Test");
        testNewDriver.setSecondName("T");
        testNewDriver.setLastName("S");

        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNewDriver)))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

        boolean res = driverService.getAll().stream()
                .filter(c -> c.getName().equals(testNewDriver.getName()))
                .filter(c -> c.getLastName().equals(testNewDriver.getLastName()))
                .anyMatch(c -> c.getSecondName().equals(testNewDriver.getSecondName()));

        Assert.assertTrue(res);
    }

    @Test
    public void update_driver_by_id_and_jsondto() throws Exception {
        Long id = testDriver1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        testDriver1.setName("NewTestName");

        mockMvc.perform(put(uri, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDriver1)))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

        Assert.assertTrue(driverService.getAll().contains(testDriver1));
    }
}
