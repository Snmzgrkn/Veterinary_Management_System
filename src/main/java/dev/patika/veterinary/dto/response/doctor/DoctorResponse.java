package dev.patika.veterinary.dto.response.doctor;

import dev.patika.veterinary.entities.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AvailableDate> availableDates;
}
