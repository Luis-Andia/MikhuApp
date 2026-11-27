
package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.edu.upc.mikhuapp.dtos.FamilyDTOInsert;
import pe.edu.upc.mikhuapp.dtos.FamilyDTOList;
import pe.edu.upc.mikhuapp.dtos.JoinFamilyDTO;
import pe.edu.upc.mikhuapp.dtos.UserDTOList;
import pe.edu.upc.mikhuapp.entities.Family;
import pe.edu.upc.mikhuapp.entities.Users;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamilyService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/families")
public class FamilyController {

    private final IFamilyService fS;
    private final ModelMapper modelMapper;
    private final IUserService uS;

    public FamilyController(
            IFamilyService fS,
            ModelMapper modelMapper,
            IUserService uS) {
        this.fS = fS;
        this.modelMapper = modelMapper;
        this.uS = uS;
    }

    // HU26: INSERTAR FAMILIA
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<FamilyDTOInsert> insertar(
            @Validated @RequestBody FamilyDTOInsert familia) {

        String nombreFamilia = familia.getNomFamily().trim();

        // Validar que no exista otra familia con el mismo nombre.
        if (fS.findFamiliesByNomFamily(nombreFamilia).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe una familia con ese nombre."
            );
        }

        familia.setNomFamily(nombreFamilia);

        Family nueva_family = modelMapper.map(familia, Family.class);
        fS.insert(nueva_family);

        FamilyDTOInsert responseDTO =
                modelMapper.map(nueva_family, FamilyDTOInsert.class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nueva_family.getIdFamily())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    // HU27: LISTAR FAMILIAS
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<List<FamilyDTOList>> listar() {

        List<FamilyDTOList> lista_familias = fS.list()
                .stream()
                .map(f -> modelMapper.map(f, FamilyDTOList.class))
                .toList();

        return ResponseEntity.ok(lista_familias);
    }

    // HU28: ACTUALIZAR FAMILIA
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<FamilyDTOInsert> actualizarfamilia(
            @Validated @RequestBody FamilyDTOInsert dto) {

        Optional<Family> existente = fS.listid(dto.getIdFamily());

        if (existente.isEmpty()) {
            throw new ResourceNotFoundException("No existe la familia");
        }

        Family family = existente.get();

        family.setNomFamily(dto.getNomFamily());
        family.setPasswordFamily(dto.getPasswordFamily());

        fS.update(family);

        FamilyDTOInsert responseDTO =
                modelMapper.map(family, FamilyDTOInsert.class);

        return ResponseEntity.ok(responseDTO);
    }

    // HU29: ELIMINAR FAMILIA
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Void> eliminar_integrante_familia(
            @PathVariable Long id) {

        Family familia = fS.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe la familia"));

        fS.delete(familia.getIdFamily());

        return ResponseEntity.noContent().build();
    }

    // HU30: CONSULTAR FAMILIA POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<FamilyDTOInsert> buscarid(@PathVariable Long id) {

        Family family = fS.listid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe la familia"));

        FamilyDTOInsert responseDTO =
                modelMapper.map(family, FamilyDTOInsert.class);

        return ResponseEntity.ok(responseDTO);
    }

    // HU46: LISTAR INTEGRANTES DE UNA FAMILIA
    @GetMapping("/IntegrantesFamilia/{idFamilia}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<List<UserDTOList>> listmembers(
            @PathVariable long idFamilia) {

        fS.listid(idFamilia)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la familia con el id: " + idFamilia));

        List<UserDTOList> lista_usuarios = uS.listmembers(idFamilia)
                .stream()
                .map(u -> {
                    UserDTOList dto =
                            modelMapper.map(u, UserDTOList.class);

                    dto.setIdRol(u.getRoles().stream()
                            .map(role -> role.getIdRol())
                            .toList());

                    return dto;
                })
                .toList();

        return ResponseEntity.ok(lista_usuarios);
    }

    // UNIRSE A UNA FAMILIA
    @PostMapping("/joinfamily/{idUser}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST', 'MODERATOR', 'MEMBER')")
    public ResponseEntity<Void> joinFamily(
            @PathVariable Long idUser,
            @RequestBody JoinFamilyDTO dto) {

        Family family = fS.findFamiliesByNomFamily(dto.getNomFamily())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la familia con el nombre: "
                                        + dto.getNomFamily()));

        if (!family.getPasswordFamily().equals(dto.getPasswordFamily())) {
            throw new ResourceNotFoundException("Credenciales incorrectas");
        }

        Users user = uS.listId(idUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        user.setFamily(family);
        uS.update(user);

        return ResponseEntity.ok().build();
    }
}
