package com.caraveo.crud;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.caraveo.crud.dto.ClienteRec;
import com.caraveo.crud.dto.ClienteRec.TipoCliente;
import com.caraveo.crud.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class Crud1ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository repository;

    @BeforeEach
    public void setup() {
    	repository.deleteAll();
    }
	
    @Test
    public void shouldCreateRecord() throws Exception {
    	ClienteRec cliente = new ClienteRec("1a","carlos","email@email.com",28, TipoCliente.VIP);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        String requestJson = objectMapper.writeValueAsString(cliente);
        MockHttpServletResponse response = mockMvc.perform(post("/cliente")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(cliente.id()))
                .andExpect(jsonPath("$.nombre").value(cliente.nombre()))
                .andExpect(jsonPath("$.email").value(cliente.email()))
                .andExpect(jsonPath("$.edad").value(cliente.edad()))
                .andExpect(jsonPath("$.tipoCliente").value("VIP"))
                .andExpect(status().isCreated()).andReturn().getResponse();

        Integer id = JsonPath.parse(response.getContentAsString()).read("$.idRecord");
        assertEquals(true, repository.findById(id).isPresent());

    }
    
    @Test
    public void shouldGetAll() throws Exception {
    	ClienteRec cliente = new ClienteRec("1a","carlos","email@email.com",28, TipoCliente.VIP);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        String requestJson = objectMapper.writeValueAsString(cliente);
        MockHttpServletResponse response = mockMvc.perform(post("/cliente")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer firstId = JsonPath.parse(response.getContentAsString()).read("$.idRecord");

        response = mockMvc.perform(post("/cliente")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse();
        Integer secondId = JsonPath.parse(response.getContentAsString()).read("$.idRecord");


        mockMvc.perform(get("/cliente")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idRecord").value(firstId))
                .andExpect(jsonPath("$[1].idRecord").value(secondId))
                .andExpect(status().isOk());
    }
    
    @Test
    public void shouldDeleteById() throws Exception {
    	ClienteRec cliente = new ClienteRec("1a","carlos","email@email.com",28, TipoCliente.VIP);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        String requestJson = objectMapper.writeValueAsString(cliente);
        MockHttpServletResponse response = mockMvc.perform(post("/cliente")
                .contentType("application/json")
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse();
        String id = JsonPath.parse(response.getContentAsString()).read("$.id");

        mockMvc.perform(delete("/cliente/" + id))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(false, repository.findById(id).isPresent());

        mockMvc.perform(delete("/cliente" + Integer.MAX_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}