package com.udj.course.dto;

import com.udj.course.domain.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Cant be empty")
    @Size(min = 5, max = 80, message = "Length should be between 5 and 80 chars")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
