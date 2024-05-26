package dev.patika.veterinary.dto.response.appointment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private LocalDateTime date;
    private Doctor doctor;
    private Animal animal;
}
