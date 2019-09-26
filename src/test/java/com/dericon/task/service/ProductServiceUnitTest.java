package com.dericon.task.service;

import com.dericon.task.domain.product.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceUnitTest {

    private ProductService productService = new ProductService();

    @Test
    public void testSortProductsBySideYieldPaDescendingWhenInputIsNotOrdered() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("QP00DE000CD5DET7", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD5DET7", "CD5DET", "CD5DET.STG"), new Figure(2.3, 2.2));
        Product product2 = new Product("QP00DE000CD6TH58", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD6TH58", "CD6TH5", "CD6TH5.EUWAX"), new Figure(2.8, 2.6));
        Product product3 = new Product("QP00DE000CD6TH66", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD6TH66", "CD6TH6", "CD6TH6.EUWAX"), new Figure(2.8, 2.5));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        List<Product> result = productService.sortProductsBySideYieldPaDescending(productList);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(product2, result.get(0));
        Assert.assertEquals(product3, result.get(1));
        Assert.assertEquals(product1, result.get(2));
    }

    @Test
    public void testSortProductsBySideYieldPaDescendingWhenInputIsOrdered() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("QP00DE000CD5DET7", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD5DET7", "CD5DET", "CD5DET.STG"), new Figure(2.3, 2.6));
        Product product2 = new Product("QP00DE000CD6TH58", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD6TH58", "CD6TH5", "CD6TH5.EUWAX"), new Figure(2.8, 2.5));
        Product product3 = new Product("QP00DE000CD6TH66", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD6TH66", "CD6TH6", "CD6TH6.EUWAX"), new Figure(2.8, 2.4));
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        List<Product> result = productService.sortProductsBySideYieldPaDescending(productList);

        Assert.assertEquals(3, result.size());
        Assert.assertEquals(product1, result.get(0));
        Assert.assertEquals(product2, result.get(1));
        Assert.assertEquals(product3, result.get(2));
    }

    @Test
    public void testSortProductsBySideYieldPaDescendingWhenInputIsEmpty() {
        List<Product> productList = new ArrayList<>();

        List<Product> result = productService.sortProductsBySideYieldPaDescending(productList);

        Assert.assertEquals(0, result.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSortProductsBySideYieldPaDescendingWhenInputIsNull() {
        productService.sortProductsBySideYieldPaDescending(null);
    }


}
