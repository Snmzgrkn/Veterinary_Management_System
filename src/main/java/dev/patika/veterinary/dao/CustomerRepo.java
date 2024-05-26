package dev.patika.veterinary.dao;

import dev.patika.veterinary.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByNameAndMail(String name, String mail);

    List<Customer> findByNameContaining(String name);
}
