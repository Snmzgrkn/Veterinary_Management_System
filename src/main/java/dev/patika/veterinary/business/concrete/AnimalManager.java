package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.dao.AnimalRepo;
import dev.patika.veterinary.dao.CustomerRepo;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final ModelMapper modelMapper;

    public AnimalManager(AnimalRepo animalRepo, ModelMapper modelMapper) {
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> findAnimalsByName(String name) {
        return this.animalRepo.findByNameContaining(name);
    }

    @Override
    public List<Animal> findAnimalsByCustomer(long id) {
        return this.animalRepo.findByCustomerId(id);
    }
}
