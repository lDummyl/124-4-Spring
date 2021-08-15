package com.example.demo.web;

import com.example.demo.converters.Converter;
import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Address;
import com.example.demo.exceptions.CarDriverException;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@RunWith(SpringRunner.class)
public class DriverControllerTest {

    MockMvc mockMvc;

    @Autowired
    Converter converter;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    DriverService driverService;


    @Autowired
    ObjectMapper objectMapper;

    ObjectWriter objectWriter;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    DriverDTO driverDTO1;
    DriverDTO driverDTO2;
    DriverDTO driverDTO3;
    CarDTO carDTO1;
    CarDTO carDTO2;
    CarDTO carDTO3;
    List<DriverDTO> list;

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        init();
    }

    private void init(){
        carDTO1 = new CarDTO();
        carDTO1.setId(1);
        carDTO1.setModel("Toyota Camry");
        carDTO1.setGarages(new ArrayList<>());
        carDTO2 = new CarDTO();
        carDTO2.setId(2);
        carDTO2.setModel("Volksvagen jetta");
        carDTO2.setGarages(new ArrayList<>());
        carDTO3 = new CarDTO();
        carDTO3.setId(3);
        carDTO3.setModel("Kia optima");
        carDTO3.setGarages(new ArrayList<>());

        driverDTO1 = new DriverDTO();
        driverDTO1.setName("Vasily");
        driverDTO1.setSurname("Pupkin");
        driverDTO1.setId(1);
        driverDTO1.setBirthDate(new GregorianCalendar(1990, 0, 1));
        driverDTO1.setPhone("88005553535");
        driverDTO1.setCars(Arrays.asList(carDTO1));
        driverDTO2 = new DriverDTO();
        driverDTO2.setName("Dmitry");
        driverDTO2.setSurname("Rogosin");
        driverDTO2.setId(2);
        driverDTO2.setBirthDate(new GregorianCalendar(1960, 11, 30));
        driverDTO2.setPhone("89991488228");
        driverDTO2.setCars(Arrays.asList(carDTO2));
        driverDTO3 = new DriverDTO();
        driverDTO3.setName("Petr");
        driverDTO3.setSurname("Petrov");
        driverDTO3.setId(3);
        driverDTO3.setBirthDate(new GregorianCalendar(1984, 5, 15));
        driverDTO3.setPhone("999999");
        driverDTO3.setCars(Arrays.asList(carDTO3));
        list = Arrays.asList(driverDTO1, driverDTO2, driverDTO3);
        driverService.saveAll(list);
    }

    @Test
    public void save() throws Exception {
        String uri = "/driver/";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(driverDTO1)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("name").value("Vasily"))
                .andExpect(jsonPath("surname").value("Pupkin"))
                .andExpect(status().isOk());

    }

    @Test
    public void saveAll() throws Exception {
        String uri = "/driver/newList";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(list)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        String uri = "/driver/1";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(jsonPath("id").value(1))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll() throws Exception {
        String uri = "/driver/all";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(jsonPath("$.length()").exists())
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        driverDTO1.setName("Dima");
        driverDTO1.setSurname("Rogosin");
        driverDTO1.setId(2);
        driverDTO1.setPhone("89001111111");
        driverDTO1.setCars(new ArrayList<>());
        String uri = "/driver/";
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(driverDTO1)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("id").value(2))
                .andExpect(jsonPath("name").value("Dima"))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        String uri = "/driver/3";
        mockMvc.perform(delete(uri))
                .andDo(document("." + uri))
                .andExpect(status().isOk());
    }

    @Test
    public void catchException() throws Exception {
        String uri = "/driver/100";
        mockMvc.perform(get(uri))
                .andDo(document("." + uri))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CarDriverException));
    }
}
