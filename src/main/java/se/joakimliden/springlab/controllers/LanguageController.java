package se.joakimliden.springlab.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.joakimliden.springlab.dtos.LanguageDto;
import se.joakimliden.springlab.services.LanguageService;

import java.util.List;


//ingen validering i controllerklassen!

@RestController
public class LanguageController {

    private LanguageService languageService;

//    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/languages")
    public List<LanguageDto> all() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/languages/{id}")
    public LanguageDto one(@PathVariable Long id) {
        return languageService.getOne(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @PostMapping("/languages")
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDto create(@RequestBody LanguageDto language) {
        return languageService.createLanguage(language);
    }

    @DeleteMapping("/languages/{id}")
    public void delete(@PathVariable Long id) {
        languageService.delete(id);
    }

    @PutMapping("/languages/{id}")  //put gör om hela objektet (bara språk ändå i detta fallet)
    public LanguageDto replace(@RequestBody LanguageDto languageDto, @PathVariable Long id) {
        return languageService.replace(id, languageDto);
    }

    @PatchMapping("/languages/{id}")  //uppdaterar bara enskilda fält (19/2 11.45)
    public LanguageDto update(@RequestBody LanguageDto languageDto, @PathVariable Long id) {
        return languageService.update(id, languageDto);
    }
}
