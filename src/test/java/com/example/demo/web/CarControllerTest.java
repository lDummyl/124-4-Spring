package com.example.demo.web;

import com.example.demo.converters.Converter;
import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
@RunWith(SpringRunner.class)
public class CarControllerTest {

    MockMvc mockMvc;

    @Autowired
    Converter converter;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarService carService;

    ObjectWriter objectWriter;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    CarDTO carDTO1;
    CarDTO carDTO2;
    List<CarDTO> list;

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();

        list = new ArrayList<>();
        carDTO1 = new CarDTO();
        carDTO1.setModel("Hyundai Solaris");
        carDTO1.setId(1);
        carDTO1.setDrivers(new ArrayList<>());

        carDTO2 = new CarDTO();
        carDTO2.setModel("Volkswagen Polo");
        carDTO2.setId(2);
        carDTO2.setDrivers(new ArrayList<>());
        list.add(carDTO1);
        list.add(carDTO2);
        carService.saveAll(list);
    }


    @Test
    public void save() throws Exception {
        carDTO1.setId(3);
        String uri = "/car/";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(carDTO1)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("model").value("Hyundai Solaris"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveAll() throws Exception {
        String uri = "/car/newList";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(list)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        String uri = "/car/1";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(jsonPath("id").value(1))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll() throws Exception {
        String uri = "/car/all";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(jsonPath("$.length()").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        carDTO1.setModel("Soyuz");
        carDTO1.setId(1);
        String uri = "/car/";
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(carDTO1)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("id").value(1))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        String uri = "/car/2";

        mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(jsonPath("id").value(2))
                .andExpect(status().isOk());
    }
}
