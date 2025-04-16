package com.pdm.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.backend.TestDataUtil;
import com.pdm.backend.models.Course;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc

public class CourseControllerIntegrationTest {


    private MockMvc mockMvc ; 
    private ObjectMapper objectmapper;

    @Autowired
    public CourseControllerIntegrationTest(MockMvc mockMvc , ObjectMapper objectMapper){
        this.mockMvc = mockMvc;
        this.objectmapper = new ObjectMapper();
    }

    @Test
    public void testthatCreateCourseSuccesFullyReturnHttp201() throws Exception{

        Course testCourseA = TestDataUtil.createTestCourseA();
        testCourseA.setCourseID(null);
        String courseJson = objectmapper.writeValueAsString(testCourseA);
        
        mockMvc.perform(
            MockMvcRequestBuilders.post("/courses").
            contentType(MediaType.APPLICATION_JSON)
            .content(courseJson)
        ).andExpect(
            MockMvcResultMatchers.status().isCreated()
        );
    }



    @Test
    public void testthatCreateCourseSuccesFullyReturnSavedCourse() throws Exception{

        Course testCourseA = TestDataUtil.createTestCourseA();
        testCourseA.setCourseID(null);
        String courseJson = objectmapper.writeValueAsString(testCourseA);
        
        mockMvc.perform(
            MockMvcRequestBuilders.post("/courses").
            contentType(MediaType.APPLICATION_JSON)
            .content(courseJson)
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.CourseID").isNumber()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.Name").value("Principle Of Database Management")
        ).andExpect(
            MockMvcResultMatchers.jsonPath( "$.Abbreviation").value("PDM")
        );
    }

    public void testthatGetCourseSuccesFullyReturnHttp201() throws Exception{
         
         Course courseEntityTestA = TestDataUtil.createTestCourseA();
         courseServices.createCourse(courseEntityTestA);

        mockMvc.perform(
            MockMvcRequestBuilders.get("/courses/1").
            contentType(MediaType.APPLICATION_JSON)
            
        ).andExpect(
            MockMvcResultMatchers.status().isOk()
        );

    }

    public void testthatGetCourseSuccesFullyReturnHttp404() throws Exception{

        mockMvc.perform(
            MockMvcRequestBuilders.get("/courses/99").
            contentType(MediaType.APPLICATION_JSON)
            
        ).andExpect(
            MockMvcResultMatchers.status().isNotFound()
        );
    }
}
