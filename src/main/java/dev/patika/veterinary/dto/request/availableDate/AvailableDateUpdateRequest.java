package dev.patika.veterinary.dto.request.availableDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    private Long id;
    private Long doctorId;
    private LocalDate availableDate;
}
