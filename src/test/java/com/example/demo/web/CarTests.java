package com.example.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarTests {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder<DefaultMockMvcBuilder> builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }

    // FIXME: 28.07.2021 add more expectations in result ala->  .andExpect(jsonPath("name", equalTo("Masha")))

    @Test
    public void testGetById() throws Exception {
        String uri = "/car/{id}";
        mockMvc.perform(get(uri, "1").contentType(MediaType.APPLICATION_JSON))
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
    public void testCreate() throws Exception {
        String uri = "/car";
        // FIXME: 28.07.2021 create DTO and use Jackson -> writevalueAsString
        String content = "{\n" +
                "  \"modelName\": \"2104\",\n" +
                "  \"carName\": \"VAZ\",\n" +
                "  \"description\": \"Четырка\",\n" +
                "  \"driverId\": 2\n" +
                "}";
        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        String uri = "/car";
        // FIXME: 28.07.2021 create DTO and use Jackson -> writevalueAsString
        String content = "{\n" +
                "  \"id\": 5,\n" +
                "  \"modelName\": \"2106\",\n" +
                "  \"carName\": \"VAZ\",\n" +
                "  \"description\": \"Шестерка\",\n" +
                "  \"driverId\": 2\n" +
                "}";
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(content))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        String uri = "/car/{id}";
        // TODO: 28.07.2021 check in Repo if deleted, inject in test class
        mockMvc.perform(delete(uri, 5).contentType(MediaType.APPLICATION_JSON))
                .andDo(document(uri.replace("/", "\\")))
                .andExpect(status().isOk());
    }
}
