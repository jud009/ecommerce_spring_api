package com.udj.course.services;


import com.udj.course.domain.Category;
import com.udj.course.repositories.CategoryRepository;
import com.udj.course.services.exceptions.DataIntegrityException;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final String OBJECT_NOT_FOUND = CategoryService.class.getSimpleName() + ": OBJETO NÃO ENCONTRADO";
    private static final String CANT_DELETE = "DATA CANNOT BE DELETED";

    @Autowired
    private CategoryRepository repository;


    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> op = repository.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }

    public Category insert(Category category) {
        category.setId(null); //se for null insere, não null atualiza
        return repository.save(category);
    }

    public Category update(Category category) {
        findById(category.getId());
        return repository.save(category);
    }

    public void deleteById(long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(CANT_DELETE);
        }
    }
}
