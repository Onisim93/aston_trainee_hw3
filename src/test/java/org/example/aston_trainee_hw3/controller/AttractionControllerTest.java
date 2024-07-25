package org.example.aston_trainee_hw3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.aston_trainee_hw3.MockTestDtoData;
import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.service.AttractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttractionController.class)
class AttractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttractionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllByCriteria() throws Exception {
        List<AttractionDto> dtos = MockTestDtoData.museumAttractionsForSamara;
        String sortBy = "name::asc";
        String type = "MUSEUM";
        String locality = "Самара";

        when(service.findByCriteria(sortBy, type, locality)).thenReturn(dtos);


        mockMvc.perform(get(String.format("/api/attraction?sort_by=%s&type=%s&locality_name=%s", sortBy, type, locality))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtos)));
    }

    @Test
    void getById() throws Exception {
        AttractionDto dto = MockTestDtoData.firstAttraction;
        when(service.findById(dto.getId())).thenReturn(dto);
        mockMvc.perform(get(String.format("/api/attraction/%d", dto.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void getAll() throws Exception {
        List<AttractionDto> dtos = MockTestDtoData.allAttractions;
        String sortBy = "id";

        when(service.findByCriteria(sortBy, null, null)).thenReturn(dtos);

        mockMvc.perform(get(String.format("/api/attraction?sort_by=%s", sortBy))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtos)));
    }

    @Test
    void create() throws Exception {
        AttractionDto dto = MockTestDtoData.newAttraction;

        dto.setLocalityId(dto.getLocalityId());
        dto.setServiceIds(dto.getServiceEntities().stream().map(ServiceDto::getId).toList());


        AttractionDto dtoCreated = MockTestDtoData.newAttractionCreated;



        when(service.save(dto)).thenReturn(dtoCreated);

        mockMvc.perform(post("/api/attraction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoCreated)));
    }

    @Test
    void update() throws Exception {
        AttractionDto dto = MockTestDtoData.updatedAttraction;

        when(service.update(dto, dto.getId())).thenReturn(dto);

        mockMvc.perform(patch("/api/attraction/" + dto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dto)));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/attraction/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}