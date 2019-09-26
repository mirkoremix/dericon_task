package com.dericon.task.controller;

import com.dericon.task.domain.product.Product;
import com.dericon.task.domain.product.dto.ProductDTO;
import com.dericon.task.service.ProductJsonDeserializeService;
import com.dericon.task.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductJsonDeserializeService jsonDeserialize;

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<List<ProductDTO>> postProductsJson(@RequestParam("file") MultipartFile originalFile) throws IOException {
        log.info("REST request parse JSON file");

        try (InputStream inputStream = originalFile.getInputStream()) {
            List<Product> products = jsonDeserialize.deserializeProductJson(inputStream);
            products = productService.sortProductsBySideYieldPaDescending(products);

            List<ProductDTO> result = products.stream().map(ProductDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
