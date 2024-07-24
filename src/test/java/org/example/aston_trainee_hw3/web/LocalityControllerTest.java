package org.example.aston_trainee_hw3.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.service.LocalityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocalityController.class)
class LocalityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalityService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        List<LocalityDto> dtos = MockTestDtoData.allLocalities;
        when(service.findAll()).thenReturn(dtos);

        mockMvc.perform(get("/api/locality")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtos)));
    }

    @Test
    void getById() throws Exception {
        LocalityDto dto = MockTestDtoData.firstLocality;
        when(service.findById(dto.getId())).thenReturn(dto);

        mockMvc.perform(get("/api/locality/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void create() throws Exception {
        LocalityDto dto = MockTestDtoData.newLocality;
        LocalityDto dtoCreated = MockTestDtoData.newLocalityCreated;
        when(service.save(dto)).thenReturn(dtoCreated);

        mockMvc.perform(post("/api/locality")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoCreated)));

    }

    @Test
    void update() throws Exception {
        LocalityDto dto = MockTestDtoData.updatedLocality;

        when(service.update(dto, dto.getId())).thenReturn(dto);

        mockMvc.perform(patch("/api/locality/" + dto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }
}