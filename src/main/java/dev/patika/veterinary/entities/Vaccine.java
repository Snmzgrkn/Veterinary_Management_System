package dev.patika.veterinary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name = "code")
    private String code;

    @Column(name = "protection_start_date")
    private LocalDate protectionStartDate;

    @Column(name = "protection_finish_date")
    private LocalDate protectionFinishDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    @JsonBackReference
    private Animal animal;
}
