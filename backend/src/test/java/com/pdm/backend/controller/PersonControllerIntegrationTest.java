package com.pdm.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.pdm.backend.TestDataUtil;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerIntegrationTest {

    private MockMvc mockMvc ;
    private ObjectMapper objectMapper ;

    public PersonControllerIntegrationTest(MockMvc mockMvc , ObjectMapper objectMapper)
    {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();

    }

    @Autowired
    public void TestThatPersonSuccessfullyCreatedHTTP201() throws Exception{
        Person personTestA = TestDataUtil.createTestPersonA();
        
        String json = objectMapper.writeValueAsString(personTestA);

        mockMvc.perform(
            MockMvcRequestBuilder.put("/person/" + personTestA.getPersonID())
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void TestThatPersonSuccessfullyReturnSavedPerson() throws Exception{

        Person personTestA = TestDataUtil.createTestPersonA();
        
        String json = objectMapper.writeValueAsString(personTestA);

        mockMvc.perform(
            MockMvcRequestBuilder.put("/person/" + personTestA.getPersonID())
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        ).andExpect(
            MockMvcRequestBuilder.jsonPath("$.PersonID").isNumber()
        ).andExpect(
            MockMvcRequestBuilder.jsonPath("$.Name").value("????")
        ).andExpect(
            MockMvcRequestBuilder.jsonPath("$.Age").value("????")
        ).andExpect(
            MockMvcRequestBuilder.jsonPath("$.Address").value("????")
        );
    }

}
