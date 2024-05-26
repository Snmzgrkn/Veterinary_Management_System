package dev.patika.veterinary.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<AvailableDate> availableDates;

    @OneToMany (mappedBy = "doctor")
    @JsonManagedReference
    private List<Appointment> appointments;
}
