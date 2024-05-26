package dev.patika.veterinary.dao;

import dev.patika.veterinary.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccineRepo extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findByNameAndCodeAndAnimalIdAndProtectionFinishDateGreaterThanEqual(String name, String code, Long id, LocalDate protectionStartDate);

    List<Vaccine> findByAnimalId(Long id);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
