package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {
    Animal save(Animal animal);
    Animal get(Long id);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(Long id);
    List<Animal> findAnimalsByName(String name);
    List<Animal> findAnimalsByCustomer(long id);
}
