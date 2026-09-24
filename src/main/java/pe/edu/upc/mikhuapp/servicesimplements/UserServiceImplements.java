package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Users;
import pe.edu.upc.mikhuapp.repositories.IUsersRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplements implements IUserService {
    private final IUsersRepository uR;

    public UserServiceImplements(IUsersRepository uR) {
        this.uR = uR;
    }

    @Override
    public List<Users> list() {
        return uR.findAll();
    }

    @Override
    public Optional<Users> listId(Long id) {
        return uR.findById(id);
    }

    @Override
    public void insert(Users u) {
        uR.save(u);
    }

    @Override
    public void delete(Long id) {
        uR.deleteById(id);
    }

    @Override
    public Users update(Users u) {
        return uR.save(u);
    }

    @Override
    public List<Users> listmembers(Long idFamily) {
        return uR.findByFamily_idFamily(idFamily);
    }
}
