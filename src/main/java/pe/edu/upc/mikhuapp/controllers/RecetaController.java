package pe.edu.upc.mikhuapp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upc.mikhuapp.dtos.*;
import pe.edu.upc.mikhuapp.entities.Familia;
import pe.edu.upc.mikhuapp.entities.Ingrediente;
import pe.edu.upc.mikhuapp.entities.Receta;
import pe.edu.upc.mikhuapp.entities.Usuario;
import pe.edu.upc.mikhuapp.exceptions.ResourceNotFoundException;
import pe.edu.upc.mikhuapp.repositories.IRecetaRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IFamiliaService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IPaisService;
import pe.edu.upc.mikhuapp.servicesinterfaces.IRecetaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private IRecetaService recetaService;

    @Autowired
    private IPaisService paisService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IRecetaRepository iRecetaRepository;

    @Autowired
    private IRecetaService iRecetaService;
    @Autowired
    private IFamiliaService iFamiliaService;

    //HU23 LISTAR RECETAS
    @GetMapping("/listarReceta")
    public ResponseEntity<List<RecetaDTOList>> listarReceta() {
        List<RecetaDTOList> lista =iRecetaService.list()
                .stream()
                .map(receta->modelMapper.map(receta, RecetaDTOList.class))
                .toList();
        return ResponseEntity.ok(lista);
    }

    // HU22 - Consultar recetas personalizadas
    @GetMapping("/personalizadas/{idFamilia}")
    public ResponseEntity<List<RecetaSugeridaDTO>> buscarRecetasPersonalizadas(
            @PathVariable Long idFamilia) {

        List<RecetaSugeridaDTO> recetas =
                recetaService.buscarRecetasPersonalizadas(idFamilia);

        return ResponseEntity.ok(recetas);
    }
}
