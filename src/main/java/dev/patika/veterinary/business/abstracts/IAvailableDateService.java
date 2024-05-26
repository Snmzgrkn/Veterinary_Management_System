package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.dao.AvailableDateRepo;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.AvailableDate;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);
    AvailableDate get(Long id);
    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate update(AvailableDate animal);
    boolean delete(Long id);
    Optional<AvailableDate> findByDoctorIdAndDate(Long id, LocalDate localDate);
}
