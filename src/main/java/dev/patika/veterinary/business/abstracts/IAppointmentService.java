package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Appointment;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);
    Appointment get(Long id);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);
    boolean delete(Long id);
    List<Appointment> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Appointment> findByDoctorIdAndDateBetween(Long doctorId, LocalDate startDate, LocalDate endDate);

    List<Appointment> findByAnimalIdAndDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);
}
