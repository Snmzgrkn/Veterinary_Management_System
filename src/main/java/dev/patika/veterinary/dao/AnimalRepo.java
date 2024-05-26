package dev.patika.veterinary.dao;

import dev.patika.veterinary.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimalRepo extends JpaRepository<Animal,Long> {
    Optional<Animal> findByNameAndSpeciesAndGenderAndDateOfBirth(String name, String species, String gender, LocalDate dateOfBirth);

    List<Animal> findByNameContaining(String name);

    List<Animal> findByCustomerId(Long id);

}
