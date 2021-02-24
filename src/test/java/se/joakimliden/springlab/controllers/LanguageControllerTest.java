package se.joakimliden.springlab.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LanguageControllerTest {

    //Unit tests. Testing one thing at a time, in isolation.

    @Test
    void callingOneWithValidIdReturnsOneLanguage() {
        LanguageController languageController = new LanguageController(new TestService());

        var language = languageController.one(1L);

        assertThat(language.getId()).isEqualTo(1L);
        assertThat(language.getLanguage()).isEqualTo("Test");
    }

    @Test
    void callingOneWithInvalidIdThrowsExceptionWithResponseStatus404() {
        LanguageController languageController = new LanguageController(new TestService());

       // var language = languageController.one(2L);

        var exception = assertThrows(ResponseStatusException.class, () -> languageController.one(2L));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

        // testet ska lyckas när metoden misslyckas
    }
}