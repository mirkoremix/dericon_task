package com.dericon.task.service;

import com.dericon.task.domain.product.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductJsonDeserializeService {

    private final Logger log = LoggerFactory.getLogger(ProductJsonDeserializeService.class);

    /**
     * Deserialize input stream to the list of {@link Product}
     *
     * @param inputStream to read JSON string from
     * @return list of deserialize Products
     * @throws IOException when cannot read stream
     */
    public List<Product> deserializeProductJson(InputStream inputStream) throws IOException {
        log.debug("Deserialize JSON file");

        if(inputStream == null){
            throw new IllegalArgumentException("Input stream should not be null");
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, new TypeReference<List<Product>>(){});
    }

}
