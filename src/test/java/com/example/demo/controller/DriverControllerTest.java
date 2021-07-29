package com.example.demo.controller;

import com.example.demo.entity.Driver;
import com.example.demo.services.DriverService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DriverControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DriverController driverController;

    @MockBean
    DriverService driverService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    private Driver driver;

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

        driver = new Driver();
        driver.setId(111L);
        driver.setFirstName("Vin");
        driver.setLastName("Diesel");
        driver.setDriverLicense("777777");
    }


    @Test
    public void getAll() throws Exception {

        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);
        when(driverService.getDrivers()).thenReturn(drivers);

        String uri = "/api/driver/all";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(
                                "[{" +
                                        "\"id\":111," +
                                        "\"firstName\":\"Vin\"," +
                                        "\"lastName\":\"Diesel\"," +
                                        "\"driverLicense\":\"777777\"," +
                                        "\"createDate\":null," +
                                        "\"updateDate\":null," +
                                        "\"cars\":null" +
                                        "}]"
                        ));
    }

    @Test
    public void getDriver() throws Exception {
        when(driverService.getDriver(any())).thenReturn(java.util.Optional.ofNullable(driver));

        String uri = "/api/driver/111";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(
                                "{" +
                                        "\"id\":111," +
                                        "\"firstName\":\"Vin\"," +
                                        "\"lastName\":\"Diesel\"," +
                                        "\"driverLicense\":\"777777\"," +
                                        "\"createDate\":null," +
                                        "\"updateDate\":null," +
                                        "\"cars\":null" +
                                        "}"
                        ));
    }

    @Test
    public void saveDriver201() throws Exception {
        when(driverService.saveDriver(any())).thenReturn(driver);

        String uri = "/api/driver/save";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": null,\n" +
                        "  \"firstName\": \"Vin\",\n" +
                        "  \"lastName\": \"Diesel\",\n" +
                        "  \"driverLicense\": \"777777\"\n" +
                        "}"))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(
                                "{" +
                                        "\"id\":111," +
                                        "\"firstName\":\"Vin\"," +
                                        "\"lastName\":\"Diesel\"," +
                                        "\"driverLicense\":\"777777\"," +
                                        "\"createDate\":null," +
                                        "\"updateDate\":null," +
                                        "\"cars\":null" +
                                        "}"
                        ));
    }

    @Test
    public void updateDriverIsOk() throws Exception {
        String uri = "/api/driver/update";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": null,\n" +
                        "  \"firstName\": \"Vin\",\n" +
                        "  \"lastName\": \"Diesel\",\n" +
                        "  \"driverLicense\": \"777777\"\n" +
                        "}"))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        String uri = "/api/driver/delete/1";
        mockMvc.perform(delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }
}