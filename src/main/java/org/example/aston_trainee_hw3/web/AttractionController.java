package org.example.aston_trainee_hw3.web;

import lombok.RequiredArgsConstructor;
import org.example.aston_trainee_hw3.dto.AttractionDto;
import org.example.aston_trainee_hw3.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/attraction")
public class AttractionController {

    private final AttractionService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttractionDto> getAllByCriteria(
            @RequestParam(name = "sort_by",defaultValue = "name::desc", required = false) String sortBy,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "locality_name", required = false) String localityName) {
        return service.findByCriteria(sortBy, type, localityName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttractionDto create(@RequestBody AttractionDto dto) {
        return service.save(dto);
    }

    @PatchMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    public AttractionDto update(@PathVariable Long id, @RequestBody AttractionDto dto) {
       return service.update(dto, id);
    }

    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}
