package org.example.aston_trainee_hw3.web;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.ServiceDto;
import org.example.aston_trainee_hw3.service.ServiceEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(("api/service"))
public class ServiceController {

    private final ServiceEntityService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceDto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDto create(@RequestBody ServiceDto serviceDto) {
        return service.save(serviceDto);
    }
}
