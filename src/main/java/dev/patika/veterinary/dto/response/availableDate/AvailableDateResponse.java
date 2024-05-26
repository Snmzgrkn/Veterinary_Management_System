package dev.patika.veterinary.dto.response.availableDate;

import dev.patika.veterinary.entities.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private Long doctorId;
    private List<AvailableDate> availableDates;

}