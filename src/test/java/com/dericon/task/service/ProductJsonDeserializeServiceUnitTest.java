package com.dericon.task.service;

import com.dericon.task.domain.product.*;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductJsonDeserializeServiceUnitTest {

    private static final String VALID_JSON = "[{\"_id\":\"QP00DE000CD5DET7\",\"derived\":{\"issuer\":{\"name\":\"Commerzbank AG\"},\"underlying\":{\"name\":\"BMW AG\"}},\"ids\":{\"isin\":\"DE000CD5DET7\",\"wkn\":\"CD5DET\",\"vwd\":\"CD5DET.STG\"},\"figures\":{\"sideYield\":0.69441667666308,\"sideYieldPa\":2.5095256136834}},{\"_id\":\"QP00DE000CD6TH58\",\"derived\":{\"issuer\":{\"name\":\"Commerzbank AG\"},\"underlying\":{\"name\":\"BMW AG\"}},\"ids\":{\"isin\":\"DE000CD6TH58\",\"wkn\":\"CD6TH5\",\"vwd\":\"CD6TH5.EUWAX\"},\"figures\":{\"sideYield\":0.12515644555695,\"sideYieldPa\":0.70280157889672}}]";

    private static final String INVALID_JSON = "[{\"brand\":\"Seat\",\"models\":[\"Alhambra\",\"Altea\",\"Altea XL\"]},{\"brand\":\"Renault\",\"models\":[\"Captur\",\"Clio\",\"Clio Grandtour\"]},{\"brand\":\"Peugeot\",\"models\":[\"1007\",\"107\",\"106\",\"108\",\"2008\"]},{\"brand\":\"Dacia\",\"models\":[\"Dokker\",\"Duster\",\"Lodgy\",\"Logan\"]}]\n";

    private static final String EMPTY_JSON = "";

    ProductJsonDeserializeService productJsonDeserializeService = new ProductJsonDeserializeService();

    @Test
    public void testDeserializeProductJsonWhenInputIsValid() throws IOException {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product("QP00DE000CD5DET7", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD5DET7", "CD5DET", "CD5DET.STG"), new Figure(0.69441667666308, 2.5095256136834));
        Product product2 = new Product("QP00DE000CD6TH58", new Derived(new Issuer("Commerzbank AG"), new Underlying("BMW AG")),
                new ProductIds("DE000CD6TH58", "CD6TH5", "CD6TH5.EUWAX"), new Figure(0.12515644555695, 0.70280157889672));
        productList.add(product1);
        productList.add(product2);

        InputStream inputStream = new ByteArrayInputStream(VALID_JSON.getBytes());

        List<Product> result = productJsonDeserializeService.deserializeProductJson(inputStream);

        Assert.assertEquals(productList, result);
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testDeserializeProductJsonWhenInputIsJsonWithInvalidObjects() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(INVALID_JSON.getBytes());
        productJsonDeserializeService.deserializeProductJson(inputStream);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserializeProductJsonWhenInputIsNull() throws IOException {
        productJsonDeserializeService.deserializeProductJson(null);
    }


    @Test(expected = IOException.class)
    public void testDeserializeProductJsonWhenInputIsEmpty() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(EMPTY_JSON.getBytes());
        productJsonDeserializeService.deserializeProductJson(inputStream);
    }

}
