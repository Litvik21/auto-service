package com.example.autoservice.controller;

import com.example.autoservice.dto.product.ProductRequestDto;
import com.example.autoservice.dto.product.ProductResponseDto;
import com.example.autoservice.dto.mapper.ProductMapper;
import com.example.autoservice.model.Product;
import com.example.autoservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    public ProductController(ProductService productService, ProductMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Save product to DB")
    public ProductResponseDto save(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(mapper.toModel(requestDto));
        return mapper.toDto(product);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product by id")
    public ProductResponseDto update(
            @PathVariable @ApiParam(value = "id of product that you want to update")
            Long id,
            @RequestBody ProductRequestDto requestDto) {

        Product product = mapper.toModel(requestDto);
        product.setId(id);
        return mapper.toDto(productService.update(product));
    }
}
