package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    Car car;

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

        car = new Car();
        car.setId(100L);
        car.setBrand("BMW");
        car.setModel("X7");
        car.setCategory('B');
    }

    @Test
    public void getAll() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        when(carService.getCars()).thenReturn(cars);

        String uri = "/api/car/all";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(
                                "[{" +
                                        "\"id\":100," +
                                        "\"brand\":\"BMW\"," +
                                        "\"model\":\"X7\"," +
                                        "\"category\":\"B\"," +
                                        "\"createDate\":null" +
                                        "}]"
                        ));

    }

    @Test
    public void getCar() throws Exception {
        when(carService.getCar(any())).thenReturn(Optional.of(car));

        String uri = "/api/car/100";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(
                                "{" +
                                        "\"id\":100," +
                                        "\"brand\":\"BMW\"," +
                                        "\"model\":\"X7\"," +
                                        "\"category\":\"B\"," +
                                        "\"createDate\":null" +
                                        "}"
                        ));

    }


    @Test
    public void saveCar_thenHttp201() throws Exception {
        when(carService.saveCar(any())).thenReturn(car);

        String uri = "/api/car/save";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"brand\": \"BMW\",\n" +
                        "  \"model\": \"X7\",\n" +
                        "  \"category\": \"B\",\n" +
                        "  \"driverId\": 22\n" +
                        "}"))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(
                                "{" +
                                        "\"id\":100," +
                                        "\"brand\":\"BMW\"," +
                                        "\"model\":\"X7\"," +
                                        "\"category\":\"B\"," +
                                        "\"createDate\":null" +
                                        "}"
                        ));
    }


    @Test
    public void saveCar_thenHttp400() throws Exception {
        when(carService.saveCar(any())).thenReturn(car);

        String uri = "/api/car/save";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(car)))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTest() throws Exception {
        String uri = "/api/car/update";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"brand\": \"BMW\",\n" +
                        "  \"model\": \"X7\",\n" +
                        "  \"category\": \"B\",\n" +
                        "  \"driverId\": 22\n" +
                        "}"))
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

}