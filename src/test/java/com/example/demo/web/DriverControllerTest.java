package com.example.demo.web;

import com.example.demo.converters.Converter;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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


    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void save() throws Exception {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setName("Vasily");
        driverDTO.setSurname("Pupkovich");
        driverDTO.setPhone("88005553535");

        String uri = "/driver/new";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(driverDTO)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("name").value("Vasily"))
                .andExpect(jsonPath("surname").value("Pupkovich"))
                .andExpect(status().isOk());

    }

    @Test
    public void saveAll() throws Exception {
        DriverDTO driverDTO1 = new DriverDTO();
        driverDTO1.setName("Vasily");
        driverDTO1.setSurname("Pupkovich");
        driverDTO1.setPhone("88005553535");
        DriverDTO driverDTO2 = new DriverDTO();
        driverDTO2.setName("Petr");
        driverDTO2.setSurname("Petrovich");
        driverDTO2.setPhone("88888888888");
        var list = Arrays.asList(driverDTO1, driverDTO2);

        String uri = "/driver/newList";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(list)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        String uri = "/driver/id/{id}";
        mockMvc.perform(get(uri, 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(status().isOk());
    }

    @Test
    public void findAll() throws Exception {
        String uri = "/driver/all";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document("." + uri))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setName("Dmitry");
        driverDTO.setSurname("Rogosin");
        driverDTO.setId(1);
        driverDTO.setPhone("89001111111");

        String uri = "/driver/update";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(driverDTO)))
                .andDo(document("." + uri))
                .andExpect(jsonPath("name").value("Dmitry"))
                .andExpect(jsonPath("surname").value("Rogosin"))
                .andExpect(status().isOk());
    }

}
