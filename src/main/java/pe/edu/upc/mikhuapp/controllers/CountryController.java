package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.CountryDTO;
import pe.edu.upc.mikhuapp.entities.Country;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.ICountryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    // Injections
    private final ICountryService countryService;
    private final ModelMapper modelMapper;

    public CountryController(
            ICountryService countryService,
            ModelMapper modelMapper) {

        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    // Methods

    // REGISTER NEW COUNTRY
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CountryDTO> register(
            @Validated @RequestBody CountryDTO dto) {

        Country newCountry =
                modelMapper.map(dto, Country.class);

        countryService.insert(newCountry);

        CountryDTO responseDTO =
                modelMapper.map(newCountry, CountryDTO.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCountry.getIdCountry())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // LIST COUNTRIES
    @GetMapping
    public ResponseEntity<List<CountryDTO>> listCountries() {

        List<CountryDTO> countryList = countryService.list()
                .stream()
                .map(country ->
                        modelMapper.map(country, CountryDTO.class))
                .toList();

        return ResponseEntity.ok(countryList);
    }

    // UPDATE COUNTRY
    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> update(
            @PathVariable("id") long id,
            @Validated @RequestBody CountryDTO dto) {

        Country country =
                modelMapper.map(dto, Country.class);

        country.setIdCountry(id);

        countryService.update(country);

        CountryDTO responseDTO =
                modelMapper.map(country, CountryDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    // FIND COUNTRY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> findById(
            @PathVariable Long id) {

        Country country = countryService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Country not found"));

        CountryDTO responseDTO =
                modelMapper.map(country, CountryDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    // DELETE COUNTRY
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        Country country = countryService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Country not found"));

        countryService.delete(country.getIdCountry());

        return ResponseEntity.noContent().build();
    }
}