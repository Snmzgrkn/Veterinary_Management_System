package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.IVaccineService;
import dev.patika.veterinary.dao.DoctorRepo;
import dev.patika.veterinary.dao.VaccineRepo;
import dev.patika.veterinary.entities.Doctor;
import dev.patika.veterinary.entities.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final ModelMapper modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, ModelMapper modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return true;
    }

    @Override
    public List<Vaccine> findAnimalsByVaccineProtectionFinishDateRange(LocalDate startDate, LocalDate endDate) {
        return this.vaccineRepo.findByProtectionFinishDateBetween(startDate,endDate);
    }

    @Override
    public List<Vaccine> findVaccinesByAnimal(Long id) {
        return this.vaccineRepo.findByAnimalId(id);
    }
}
