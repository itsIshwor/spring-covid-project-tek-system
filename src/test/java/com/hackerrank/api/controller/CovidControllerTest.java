package com.hackerrank.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.repository.CovidRepository;

import static org.hamcrest.Matchers.greaterThan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class CovidControllerTest {
    ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CovidRepository repository;

    @Test
    public void testCreation() throws Exception {
        Covid expectedRecord = Covid.builder().country("Test Country").build();
        Covid actualRecord = om.readValue(mockMvc.perform(post("/covid")
                        .contentType("application/json").content(om.writeValueAsString(expectedRecord))).andDo(print())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(status().isCreated()).andReturn().getResponse()
                .getContentAsString(), Covid.class);

        Assertions.assertEquals(expectedRecord.getCountry(), actualRecord.getCountry());
    }

    @Test
    public void testCreationWithIdShouldBe400StatusCode() throws Exception {
        Covid record = Covid.builder().id(1l).country("Test Country").build();
        mockMvc.perform(post("/covid")
                .contentType("application/json").content(om.writeValueAsString(record))).andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        mockMvc.perform(get("/covid/-1")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void testGetByIdOkStatus() throws Exception {
        mockMvc.perform(get("/covid/1")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testTotalByBadRequest() throws Exception {
        mockMvc.perform(get("/covid/top5?by=test")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testTotalByOkRequest() throws Exception {
        mockMvc.perform(get("/covid/top5?by=active")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(jsonPath("$.[0].active", is(670)));

    }

    @Test
    public void testTop5lByBadRequest() throws Exception {
        mockMvc.perform(get("/covid/top5?by=test"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testTop5lByDeath() throws Exception {
        mockMvc.perform(get("/covid/total?by=death"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().contains("28"));

    }

}
