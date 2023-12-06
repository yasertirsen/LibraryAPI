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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                new Author(2L, "Albert Camus", new ArrayList<>()));
    }

    // REST API

    @Test
    public void shouldReturnAuthors() throws Exception {
        String response = """
                [
                    {
                        "id":1,
                        "name":"John Green",
                        "books": []
                    },
                    {
                         "id":2,
                        "name":"Albert Camus",
                        "books": []
                    }
                ]
                """;

        when(authorService.findAll()).thenReturn(authors);

        mockMvc.perform(get("/api/author/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
