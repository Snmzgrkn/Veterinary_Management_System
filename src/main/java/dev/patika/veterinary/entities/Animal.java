package dev.patika.veterinary.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "animals")

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "colour")
    private String colour;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "animal",  cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "animal")
    @JsonManagedReference
    List<Appointment> appointments;
}
