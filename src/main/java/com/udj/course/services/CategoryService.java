package com.udj.course.services;


import com.udj.course.domain.Category;
import com.udj.course.repositories.CategoryRepository;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private static final String OBJECT_NOT_FOUND = CategoryService.class.getSimpleName() + ": OBJETO NÃO ENCONTRADO";

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id) {
        Optional<Category> op = repository.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }

    public Category insert(Category category) {
        category.setId(null); //se for null insere, não null atualiza
        return repository.save(category);
    }

    public Category update(Category category){
        findById(category.getId());
        return repository.save(category);
    }
}
