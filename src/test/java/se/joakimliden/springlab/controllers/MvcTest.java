package se.joakimliden.springlab.controllers;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.joakimliden.springlab.dtos.LanguageDto;
import se.joakimliden.springlab.services.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(LanguageController.class)
//@Import(TestService.class)
public class MvcTest {

//    @Autowired
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingWithUrlLanguageShouldReturnAllLanguagesAsJson() throws Exception {
        //Tell mockito what to return when calling methods on Service
        Mockito.when(service.getAllLanguages()).thenReturn(List.of(new LanguageDto(1, "")));



        var result = mockMvc.perform(MockMvcRequestBuilders.get("/languages")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

    }
}
