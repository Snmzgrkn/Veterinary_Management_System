package dev.patika.veterinary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;

}