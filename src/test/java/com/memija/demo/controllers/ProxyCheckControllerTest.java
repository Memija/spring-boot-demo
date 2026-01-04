package com.memija.demo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProxyCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testProxyCheckFailure() throws Exception {
        // Since we don't have a real proxy setup, this should fail with 500
        mockMvc.perform(get("/proxycheck"))
                .andExpect(status().isInternalServerError());
    }
}
