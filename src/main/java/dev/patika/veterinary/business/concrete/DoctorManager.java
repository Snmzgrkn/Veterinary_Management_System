package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.IDoctorService;
import dev.patika.veterinary.dao.DoctorRepo;
import dev.patika.veterinary.entities.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;
    private final ModelMapper modelMapper;

    public DoctorManager(DoctorRepo doctorRepo, ModelMapper modelMapper) {
        this.doctorRepo = doctorRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }


}
