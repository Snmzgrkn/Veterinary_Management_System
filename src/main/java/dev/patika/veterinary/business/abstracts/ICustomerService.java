package dev.patika.veterinary.business.abstracts;

import dev.patika.veterinary.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer get(Long id);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);
    boolean delete(Long id);
    List<Customer> findCustomersByName(String name);

}
