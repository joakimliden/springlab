package se.joakimliden.springlab.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;
import se.joakimliden.springlab.dtos.LanguageDto;
import se.joakimliden.springlab.services.Service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)

public class MvcTest {


    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlLanguageShouldReturnAllLanguagesAsJson() throws Exception {
        when(service.getAllLanguages()).thenReturn(List.of(new LanguageDto(1, "")));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/languages")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void callingWithUrlForAllLanguagesAndReturnAllLanguagesAsList() throws Exception {
        when(service.getAllLanguages()).thenReturn(List.of(
                new LanguageDto(1L, "Test1"),
                new LanguageDto(2L, "Test2")));

        mockMvc.perform(MockMvcRequestBuilders.get("/languages/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void callingWithUrlForOneLanguageWithValidIdAndReturnRequestedLanguageAsJson() throws Exception {
        when(service.getOne(1L)).thenReturn(Optional.of(new LanguageDto(1L, "TestLanguage")));

        mockMvc.perform(MockMvcRequestBuilders.get("/languages/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void callingWithUrlForOneLanguageWithInvalidIdAndReturn404Exeption() throws Exception {
        when(service.getOne(1L)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get("/languages/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
    }

    @Test
    void creatingNewLanguageAndReturnIsCreatedResponse() throws Exception {
        LanguageDto newLanguage = new LanguageDto(1L, "TestLanguage");

        when(service.createLanguage(any(LanguageDto.class))).thenReturn(newLanguage);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/languages")
                        .content(asJsonString(newLanguage))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.language")
                        .value(newLanguage.getLanguage()));
    }

    @Test
    void deleteLanguageWithValidIdAndReturnStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/languages/{id}", 1L))
                        .andExpect(status().isOk());
    }

    @Test
    void deleteLanguageWithInvalidIdAndReturnStatusNotFound() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(service)
                .delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/languages/{id}", 1L))
                        .andExpect(status().isNotFound());
    }

    @Test
    void searchForLanguageWithLanguageUrlString() throws Exception {
        Optional<LanguageDto> searchResults = Optional.of(new LanguageDto(1L, "TestLanguage"));

        when(service.findByLanguage(anyString()))
                    .thenReturn(searchResults);

        mockMvc.perform(MockMvcRequestBuilders.get("/languages/search?language=TestLanguage")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(searchResults))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
    @Test
    void searchForLanguageWithUrlStringThatDoesNotExist() throws Exception {
        Optional<LanguageDto> searchResults = Optional.of(new LanguageDto(1L, "TestLanguage"));

        when(service.findByLanguage(anyString()))
                    .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get("/languages/{id}",1L)
                        .content(asJsonString(searchResults))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status()
                        .isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
