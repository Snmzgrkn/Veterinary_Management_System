package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.dao.AvailableDateRepo;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.AvailableDate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final ModelMapper modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, ModelMapper modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

    @Override
    public Optional<AvailableDate> findByDoctorIdAndDate(Long id, LocalDate localDate) {
        return this.availableDateRepo.findByDoctorIdAndAvailableDate(id,localDate);
    }
}
