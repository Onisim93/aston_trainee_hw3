package org.example.aston_trainee_hw3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.service.ServiceEntityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceController.class)
class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceEntityService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAll() throws Exception {
        List<ServiceDto> serviceDtos = MockTestDtoData.allServices;
        when(service.findAll()).thenReturn(serviceDtos);

        mockMvc.perform(get("/api/service")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(serviceDtos)));
    }

    @Test
    void getById() throws Exception {
        ServiceDto dto = MockTestDtoData.firstService;
        when(service.findById(dto.getId())).thenReturn(dto);

        mockMvc.perform(get("/api/service/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void create() throws Exception {
        ServiceDto dto = MockTestDtoData.newService;
        ServiceDto dtoCreated = MockTestDtoData.newServiceCreated;
        when(service.save(dto)).thenReturn(dtoCreated);

        mockMvc.perform(post("/api/service")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoCreated)));

    }
}