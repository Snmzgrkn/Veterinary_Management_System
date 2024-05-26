package dev.patika.veterinary.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    @JsonBackReference
    private Animal animal;

}
