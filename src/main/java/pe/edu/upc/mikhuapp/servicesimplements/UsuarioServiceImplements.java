package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Usuario;
import pe.edu.upc.mikhuapp.repositories.IUsuarioRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImplements implements IUsuarioService {
    private final IUsuarioRepository uR;


    public UsuarioServiceImplements(IUsuarioRepository uR) {
        this.uR = uR;
    }

    @Override
    public List<Usuario> list() {
        return uR.findAll();
    }

    @Override
    public Optional<Usuario> listId(Long id) {
        return uR.findById(id);
    }

    @Override
    public void insert(Usuario u) {
        uR.save(u);
    }

    @Override
    public void delete(Long id) {
        uR.deleteById(id);
    }

    @Override
    public Usuario update(Usuario u) {
        return uR.save(u);
    }

    @Override
    public List<Usuario> listarIntegrantes(Long idFamilia) {
        return uR.findByFamilia_idFamilia(idFamilia);
    }
}
