package com.dericon.task.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    private static final String VALID_JSON = "[{\"_id\":\"QP00DE000CD5DET7\",\"derived\":{\"issuer\":{\"name\":\"Commerzbank AG\"},\"underlying\":{\"name\":\"BMW AG\"}},\"ids\":{\"isin\":\"DE000CD5DET7\",\"wkn\":\"CD5DET\",\"vwd\":\"CD5DET.STG\"},\"figures\":{\"sideYield\":0.69441667666308,\"sideYieldPa\":2.5095255}},{\"_id\":\"QP00DE000CD6TH58\",\"derived\":{\"issuer\":{\"name\":\"Commerzbank AG\"},\"underlying\":{\"name\":\"BMW AG\"}},\"ids\":{\"isin\":\"DE000CD6TH58\",\"wkn\":\"CD6TH5\",\"vwd\":\"CD6TH5.EUWAX\"},\"figures\":{\"sideYield\":0.12515644555695,\"sideYieldPa\":0.7028016}}]";

    private static final String EXPECTED_RESULT = "[{\"issuerName\":\"Commerzbank AG\",\"underlyingName\":\"BMW AG\",\"isIn\":\"DE000CD5DET7\",\"sideYieldPa\":2.5095255},{\"issuerName\":\"Commerzbank AG\",\"underlyingName\":\"BMW AG\",\"isIn\":\"DE000CD6TH58\",\"sideYieldPa\":0.7028016}]\n";

    private static final String INVALID_JSON = "[{\"brand\":\"Seat\",\"models\":[\"Alhambra\",\"Altea\",\"Altea XL\"]},{\"brand\":\"Renault\",\"models\":[\"Captur\",\"Clio\",\"Clio Grandtour\"]},{\"brand\":\"Peugeot\",\"models\":[\"1007\",\"107\",\"106\",\"108\",\"2008\"]},{\"brand\":\"Dacia\",\"models\":[\"Dokker\",\"Duster\",\"Lodgy\",\"Logan\"]}]\n";

    private static final String EMPTY_JSON = "";

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testProductControllerWhenInputIsValid() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile file = new MockMultipartFile("file", "file", "application/json", VALID_JSON.getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(content().json(EXPECTED_RESULT));
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testProductControllerWhenInputIsInvalid() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile file = new MockMultipartFile("file", "file", "application/json", INVALID_JSON.getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                .file(file))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = Exception.class)
    public void testProductControllerWhenInputIsEmpty() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockMultipartFile file = new MockMultipartFile("file", "file", "application/json", EMPTY_JSON.getBytes());

        mvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                .file(file))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("No content to map due to end-of-input at [Source: (ByteArrayInputStream); line: 1, column: 0]"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductControllerWhenInputIsNull() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc.perform(MockMvcRequestBuilders.multipart("/api/products")
                .file(null))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("MultipartFile must not be null"));
    }
}