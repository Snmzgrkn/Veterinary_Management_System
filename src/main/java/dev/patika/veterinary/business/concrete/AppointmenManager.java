package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.IAppointmentService;
import dev.patika.veterinary.dao.AnimalRepo;
import dev.patika.veterinary.dao.AppointmentRepo;
import dev.patika.veterinary.entities.Appointment;
import dev.patika.veterinary.entities.AvailableDate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmenManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final ModelMapper modelMapper;

    public AppointmenManager(AppointmentRepo appointmentRepo, ModelMapper modelMapper) {
        this.appointmentRepo = appointmentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow();
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }

    @Override
    public List<Appointment> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return appointmentRepo.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    @Override
    public List<Appointment> findByDoctorIdAndDateBetween(Long doctorId, LocalDate startDate, LocalDate endDate) {
        if (doctorId==null){
            return appointmentRepo.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepo.findByDoctorIdAndDateBetween(doctorId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    @Override
    public List<Appointment> findByAnimalIdAndDateBetween(Long animalId, LocalDate startDate, LocalDate endDate) {
        if (animalId==null){
            return appointmentRepo.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepo.findByAnimalIdAndDateBetween(animalId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }
}
