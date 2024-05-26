package dev.patika.veterinary.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "phone")
    private String phone;

    @Column (name = "mail")
    private String mail;

    @Column (name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Animal> animals ;

}
