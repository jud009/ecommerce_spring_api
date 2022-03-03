package com.udj.course.resources;

import com.udj.course.domain.Client;
import com.udj.course.domain.Product;
import com.udj.course.dto.ClientDTO;
import com.udj.course.dto.ProductDTO;
import com.udj.course.resources.utils.URL;
import com.udj.course.services.OrderService;
import com.udj.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

        String decodedName = URL.decodeParam(name);

        Page<Product> clientPage = service.search(decodedName, URL.decodeList(categories), page, linesPerPage, direction, orderBy);
        Page<ProductDTO> productsDTO = clientPage.map(ProductDTO::new);

        return ResponseEntity.ok().body(productsDTO);
    }
}
