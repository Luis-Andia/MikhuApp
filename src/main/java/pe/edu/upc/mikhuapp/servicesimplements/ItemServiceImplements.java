package pe.edu.upc.mikhuapp.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mikhuapp.entities.Item;
import pe.edu.upc.mikhuapp.repositories.IItemRepository;
import pe.edu.upc.mikhuapp.servicesinterfaces.IItemService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplements implements IItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Override
    public List<Item> list() {
        return itemRepository.findAll();
    }

    @Override
    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> listid(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> listarVencidos(LocalDate fechaActual) {
        return itemRepository.findByfechaVencimientoBefore(fechaActual);
    }

    @Override
    public List<Item> listarProximosVencer(LocalDate fechaActual, LocalDate fechaLimite){
        return itemRepository.findByfechaVencimientoBetween(fechaActual, fechaLimite);
    }

    @Override
    public List<Item> listarAlimentoBajoStock() {
        return itemRepository.findAlimentosBajoStock();
    }
}