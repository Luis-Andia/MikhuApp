package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.FamilyDTOInsert;
import pe.edu.upc.mikhuapp.dtos.FamilyDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    // Injections
    private final IFamilyService familyService;
    private final ModelMapper modelMapper;

    public FamilyController(
            IFamilyService familyService,
            ModelMapper modelMapper) {

        this.familyService = familyService;
        this.modelMapper = modelMapper;
    }

    // Methods

    // LIST
    @GetMapping
    public ResponseEntity<List<FamilyDTOList>> list() {

        List<FamilyDTOList> familyList = familyService.list()
                .stream()
                .map(family ->
                        modelMapper.map(family, FamilyDTOList.class))
                .toList();

        return ResponseEntity.ok(familyList);
    }

    // INSERT
    @PostMapping
    public ResponseEntity<FamilyDTOInsert> insert(
            @Validated @RequestBody FamilyDTOInsert familyDTO) {

        Family newFamily =
                modelMapper.map(familyDTO, Family.class);

        familyService.insert(newFamily);

        FamilyDTOInsert responseDTO =
                modelMapper.map(newFamily, FamilyDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFamily.getIdFamily())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    // FIND FAMILY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTOList> findById(
            @PathVariable Long id) {

        Family family = familyService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Family not found"));

        FamilyDTOList responseDTO =
                modelMapper.map(family, FamilyDTOList.class);

        return ResponseEntity.ok(responseDTO);
    }

    // UPDATE FAMILY
    @PutMapping
    public ResponseEntity<FamilyDTOInsert> update(
            @Validated @RequestBody FamilyDTOInsert dto) {

        Optional<Family> existingFamily =
                familyService.findById(dto.getIdFamily());

        if (existingFamily.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Family not found");
        }

        Family family = existingFamily.get();

        family.setFamilyName(dto.getFamilyName());
        family.setFamilyPassword(dto.getFamilyPassword());

        familyService.update(family);

        FamilyDTOInsert responseDTO =
                modelMapper.map(family, FamilyDTOInsert.class);

        return ResponseEntity.ok(responseDTO);
    }

    // DELETE FAMILY MEMBER
    @DeleteMapping("/{id}/family/{familyId}")
    public ResponseEntity<Void> deleteFamilyMember(
            @PathVariable Long id,
            @PathVariable Long familyId) {

        Family family = familyService.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Family not found"));

        family.setIdFamily(familyId);
        familyService.update(family);

        return ResponseEntity.noContent().build();
    }
}