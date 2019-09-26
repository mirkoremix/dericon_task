package com.dericon.task.service;

import com.dericon.task.domain.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    /**
     * Sort list of products in descending order
     *
     * @param products list to be sorted
     * @return list of products sorted by sideYieldPa
     */
    public List<Product> sortProductsBySideYieldPaDescending(List<Product> products) {
        log.debug("Sort products by SideYieldPa value, descending");
        return products.stream().sorted(Comparator.comparing(product -> product.getFigures().getSideYieldPa(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
