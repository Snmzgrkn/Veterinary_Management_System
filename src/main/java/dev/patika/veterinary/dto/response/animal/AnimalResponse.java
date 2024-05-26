package dev.patika.veterinary.dto.response.animal;

import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entities.AvailableDate;
import dev.patika.veterinary.entities.Customer;
import dev.patika.veterinary.entities.Vaccine;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {
    private long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private String colour;
    private CustomerResponse customer;
    private List<Vaccine> vaccines;
}
