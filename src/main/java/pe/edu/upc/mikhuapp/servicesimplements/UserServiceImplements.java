package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Users;
import pe.edu.upc.mikhuapp.repositories.IUsersRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplements implements IUserService {

    private final IUsersRepository userRepository;

    public UserServiceImplements(IUsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> list() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void insert(Users user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users update(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> listFamilyMembers(Long familyId) {
        return userRepository.findByFamily_IdFamily(familyId);
    }
}