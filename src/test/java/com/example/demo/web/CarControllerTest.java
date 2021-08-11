package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarControllerTest {

    MockMvc mockMvc;
    String URI_CONTROLLER = "/cars";

    private CarDTO testCar1;
    private CarDTO testCar2;
    private Set<CarDTO> carSet;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarService carService;

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
        //TODO: С подключение БД перенести в init.sql и отказаться от carService
        clearTestData();

        testCar1 = new CarDTO();
        testCar1.setId(101L);
        testCar1.setBrand("bmw");
        testCar1.setModel("1");
        carService.add(testCar1);

        testCar2 = new CarDTO();
        testCar2.setId(102L);
        testCar2.setBrand("ford");
        testCar2.setModel("focus");
        carService.add(testCar2);

        carSet = carService.getAll();
    }

    private void clearTestData() {
        for (CarDTO delCar : carService.getAll()) {
            carService.delete(delCar.getId());
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

        assertEquals(responceJson, objectMapper.writeValueAsString(carSet));
    }

    @Test
    public void read_car_by_id_succ() throws Exception {
        Long id = testCar1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString().equals(objectMapper.writeValueAsString(testCar1));
    }

    @Test
    public void read_car_by_id_fail() throws Exception {
        Long id = testCar1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        clearTestData();

        mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result -> assertEquals(Objects.requireNonNull(result.getResolvedException()).getMessage(), "Could not find data by " + id));
    }

    @Test
    public void add_new_car_by_post_jsondto_succ() throws Exception {
        String uri = URI_CONTROLLER;

        clearTestData();

        CarDTO testNewCar = new CarDTO();
        testNewCar.setBrand("sizuki");
        testNewCar.setModel("sx-4");

        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNewCar)))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

        boolean res = carService.getAll().stream()
                .filter(c -> c.getModel().equals(testNewCar.getModel()))
                .anyMatch(c -> c.getBrand().equals(testNewCar.getBrand()));

        Assert.assertTrue(res);
    }

    @Test
    public void update_car_by_id_and_jsondto() throws Exception {
        Long id = testCar1.getId();
        String uri = URI_CONTROLLER + "/" + id;

        testCar1.setModel("mokka");

        mockMvc.perform(put(uri, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCar1)))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());

        Assert.assertTrue(carService.getAll().contains(testCar1));
    }
}