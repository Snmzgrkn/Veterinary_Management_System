package dev.patika.veterinary.dto.request.animal;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalWithoutCustomerSaveRequest {

    private String name;
    private String species;
    private String breed;
    private String gender;
    private LocalDate dateOfBirth;
    private String colour;
}