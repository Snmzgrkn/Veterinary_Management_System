package dev.patika.veterinary.dao;

import dev.patika.veterinary.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByDateAndDoctorIdAndAnimalId(LocalDateTime date, Long id, Long id1);
    Optional<Appointment> findByDateAndDoctorId(LocalDateTime date, Long id);
    List<Appointment> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByDoctorIdAndDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByAnimalIdAndDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);
}
