package org.example.aston_trainee_hw3.controller;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.LocalityDto;
import org.example.aston_trainee_hw3.service.LocalityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling CRUD operations related to localities.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/locality")
public class LocalityController {

    private final LocalityService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocalityDto> getAll(@RequestParam(required = false, defaultValue = "false") String withRec) {
        return Boolean.parseBoolean(withRec) ? service.findAllWithRec() : service.findAll();
    }

    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public LocalityDto getById(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") String withRec) {
        return Boolean.parseBoolean(withRec) ? service.findByIdWithRec(id) : service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocalityDto create(@RequestBody LocalityDto dto) {
        return service.save(dto);
    }

    @PatchMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public LocalityDto update(@PathVariable Long id, @RequestBody LocalityDto dto) {
        return service.update(dto, id);
    }
}
