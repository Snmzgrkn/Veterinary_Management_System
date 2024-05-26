package dev.patika.veterinary.dao;

import dev.patika.veterinary.entities.Customer;
import dev.patika.veterinary.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.Optional;

public interface DoctorRepo  extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByNameAndMail(String name, String mail);
}
