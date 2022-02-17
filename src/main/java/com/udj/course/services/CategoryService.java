package com.udj.course.services;


import com.udj.course.Constants;
import com.udj.course.domain.Category;
import com.udj.course.dto.CategoryDTO;
import com.udj.course.repositories.CategoryRepository;
import com.udj.course.services.exceptions.DataIntegrityException;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;


    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> op = repository.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException(Constants.OBJECT_NOT_FOUND));
    }

    public Page<Category> findPage(int page, int linesPerPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
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
            throw new DataIntegrityException(Constants.CANT_DELETE);
        }
    }

    public Category fromDT0(CategoryDTO obj){
        return new Category(obj.getId(), obj.getName());
    }
}
