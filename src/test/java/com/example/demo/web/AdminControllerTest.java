package com.example.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @Autowired
    ObjectMapper objectMapper;

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
    public void testInit() throws Exception {
//
//        String uri = "/users/{id}";
//        mockMvc.perform(get(uri, 99)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcRestDocumentation.document(uri.replace("/", "\\")))
//                .andExpect(status().isOk());



        B b = objectMapper.convertValue(new Aa(), B.class);
        System.out.println("b.c = " + b.getC().getName());
    }
}

@Data
class C {
    String name = "test";
}
@Data
class D {
    String name;
}
@Data
class Aa {
    private C c = new C();
}
@Data
class B {
    private D c;
}