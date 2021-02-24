package se.joakimliden.springlab.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.joakimliden.springlab.dtos.LanguageDto;
import se.joakimliden.springlab.services.Service;

import java.util.List;
import java.util.Optional;


//ingen validering i controllerklassen!

@RestController // ("/languages")   (för att slippa skriva hela urlen i metoderna nedan, obs funkar ej...)
public class LanguageController {

    private Service service;

//    @Autowired    //behöver inte
    public LanguageController(Service service) {
        this.service = service;
    }

    @GetMapping("/languages")
    public List<LanguageDto> all() {
        return service.getAllLanguages();
    }

    @GetMapping("/languages/{id}")
    public LanguageDto one(@PathVariable Long id) {
        return service.getOne(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @PostMapping("/languages")
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDto create(@RequestBody LanguageDto language) {
        return service.createLanguage(language);
    }

    @DeleteMapping("/languages/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/languages/{id}")  //put gör om hela objektet (bara språk ändå i detta fallet)
    public LanguageDto replace(@RequestBody LanguageDto languageDto, @PathVariable Long id) {
        return service.replace(id, languageDto);
    }

    @PatchMapping("/languages/{id}")  //uppdaterar bara enskilda fält (19/2 11.45)
    public LanguageDto update(@RequestBody LanguageDto languageDto, @PathVariable Long id) {
        return service.update(id, languageDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/languages/search")
    @ResponseBody
    public Optional<LanguageDto> findByLanguage(@RequestParam(value="language") String language) {
        return service.findByLanguage(language);
    }

}
