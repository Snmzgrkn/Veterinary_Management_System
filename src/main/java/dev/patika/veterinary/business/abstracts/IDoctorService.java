package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.entities.Doctor;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IDoctorService {
    Doctor save(Doctor doctor);
    Doctor get(Long id);
    Page<Doctor> cursor(int page, int pageSize);
    Doctor update(Doctor doctor);
    boolean delete(Long id);
}
