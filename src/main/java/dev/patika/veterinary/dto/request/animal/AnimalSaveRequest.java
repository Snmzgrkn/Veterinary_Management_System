package dev.patika.veterinary.dto.request.animal;

import dev.patika.veterinary.entities.Customer;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private String colour;
    private Long customerId;
}