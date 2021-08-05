package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarController carController;

    @MockBean
    CarService carService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }

    @Test
    public void getAll() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(getCar());
        when(carService.getCars()).thenReturn(cars);

        String s = objectMapper.writeValueAsString(getCar());

        String uri = "/api/car/all";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(Collections.singletonList(s).toString()));
    }

    @Test
    public void getOneCar() throws Exception {
        when(carService.getCar(any())).thenReturn(Optional.of(getCar()));

        String s = objectMapper.writeValueAsString(getCar());

        String uri = "/api/car/100";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(s));

    }


    @Test
    public void saveCar_thenHttp201() throws Exception {
        when(carService.saveCar(any())).thenReturn(getCar());

        String s = objectMapper.writeValueAsString(getCar());

        String uri = "/api/car/save";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(s));
    }


    @Test
    public void saveCar_thenHttp400() throws Exception {
        when(carService.saveCar(any())).thenReturn(getCar());

        String uri = "/api/car/save";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(getCar())))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTest() throws Exception {
        String s = objectMapper.writeValueAsString(getCar());

        String uri = "/api/car/update";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        String uri = "/api/car/delete/1";
        mockMvc.perform(delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void getCarsByUserAgeTestHttp200() throws Exception {
        String uri = "/api/car/findbyuserage?age=10";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void getPageableCarAndSortHttp200() throws Exception {
        String uri = "/api/car/pageable?page=0&size=100&sortby=id";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    private Car getCar() {
        Car car;
        car = new Car();
        car.setId(100L);
        car.setBrand("BMW");
        car.setModel("X7");
        car.setCategory('B');

        return car;
    }

}