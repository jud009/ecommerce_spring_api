package com.udj.course.services;

import com.udj.course.domain.Category;
import com.udj.course.domain.Product;
import com.udj.course.repositories.CategoryRepository;
import com.udj.course.repositories.ProductRepository;
import com.udj.course.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final String OBJECT_NOT_FOUND = ProductService.class.getSimpleName() + ": OBJETO NÃO ENCONTRADO";

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product findById(Integer id) {
        Optional<Product> op = repository.findById(id);
        return op.orElseThrow(() -> new ObjectNotFoundException(OBJECT_NOT_FOUND));
    }

    public Page<Product> search(String name, List<Long> ids,
                                Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(
                page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);

        return repository.search(name, categories, pageRequest);
    }
}
