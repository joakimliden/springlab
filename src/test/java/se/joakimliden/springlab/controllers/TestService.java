package se.joakimliden.springlab.controllers;

import se.joakimliden.springlab.dtos.LanguageDto;
import se.joakimliden.springlab.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {
    @Override
    public List<LanguageDto> getAllLanguages() {
        return List.of(new LanguageDto(1, "Test"), new LanguageDto(2, "Test2"));
    }

    @Override
    public Optional<LanguageDto> getOne(Long id) {
        if (id == 1)
            return Optional.of(new LanguageDto(1, "Test"));
        return Optional.empty();
    }

    @Override
    public LanguageDto createLanguage(LanguageDto language) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public LanguageDto replace(Long id, LanguageDto languageDto) {
        return null;
    }

    @Override
    public LanguageDto update(Long id, LanguageDto languageDto) {
        return null;
    }

    @Override
    public Optional<LanguageDto> findByLanguage(String language) {
        return Optional.empty();
    }
}
