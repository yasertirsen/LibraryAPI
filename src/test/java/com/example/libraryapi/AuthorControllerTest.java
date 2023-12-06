package com.example.libraryapi;

import com.example.libraryapi.controller.AuthorController;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;

    private List<Author> authors = new ArrayList<>();

    @BeforeEach
    public void setup (){
        //sample data
        authors = List.of(
                new Author(1L, "John Green", new ArrayList<>()),
                new Author(2L, "Albert Camus", new ArrayList<>()),
                new Author(3L, "Jordan Peterson", new ArrayList<>()));
    }

    // REST API

    @Test
    public void shouldReturnAuthors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/author/all"))
                .andExpect(status().isOk());
    }
}
