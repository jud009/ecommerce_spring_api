package com.udj.course.resources;

import com.udj.course.domain.Category;
import com.udj.course.dto.CategoryDTO;
import com.udj.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> categories = service.findAll();
        List<CategoryDTO> categoryDTOList = categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        Page<Category> categoryPage = service.findPage(page, linesPerPage, direction, orderBy);
        Page<CategoryDTO> categoryDTOList = categoryPage.map(CategoryDTO::new);

        return ResponseEntity.ok().body(categoryDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDT0(categoryDTO);
        category = service.insert(category);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = service.fromDT0(categoryDTO);
        category.setId(id);
        service.update(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
