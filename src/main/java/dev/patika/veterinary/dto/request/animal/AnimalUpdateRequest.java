package dev.patika.veterinary.dto.request.animal;

import dev.patika.veterinary.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private String colour;
    private Long customerId;
}