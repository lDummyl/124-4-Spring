package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class CarTests {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarRepository repository;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder<DefaultMockMvcBuilder> builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }

    @Test
    public void testCreate() throws Exception {
        String uri = "/car";
        CarDTO dto = new CarDTO();
        dto.setModelName("2104");
        dto.setCarName("VAZ");
        dto.setDescription("Четырка");
        dto.setDriverId(1);
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("modelName").value("2104"));
    }

    @Test
    public void testGetById() throws Exception {
        String uri = "/car/{id}";
        mockMvc.perform(get(uri, 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        String uri = "/car";
        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        String uri = "/car";
        CarDTO dto = new CarDTO();
        dto.setId(1);
        dto.setModelName("2106");
        dto.setCarName("VAZ");
        dto.setDescription("Шестерка");
        dto.setDriverId(2);
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("modelName").value("2106"));
    }

    @Test
    public void testDelete() throws Exception {
        String uri = "/car/{id}";
        Integer idToDelete = 2;
        Assert.assertTrue("There was not such entity to remove!", repository.existsById(idToDelete));
        mockMvc.perform(delete(uri, idToDelete).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
        Assert.assertFalse("The entity was not removed!", repository.existsById(idToDelete));
    }

    @Test
    public void whenMethodArgumentMismatch_thenBadRequest() throws Exception {
        String uri = "/car/{id}";
        mockMvc.perform(get(uri, "blah-de-blah").contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.contains("id should be of type java.util.Optional")));
    }

    @Test
    public void whenInternalException_thenBadRequest() throws Exception {
        String uri = "/car/{id}";
        Integer idToDelete = 20;
        mockMvc.perform(delete(uri, idToDelete).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", Matchers.contains("There is no such car!")));
    }
}
