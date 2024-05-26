package dev.patika.veterinary.business.concrete;

import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.dao.CustomerRepo;
import dev.patika.veterinary.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;
    public CustomerManager(CustomerRepo customerRepo, ModelMapper modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerRepo.findByNameContaining(name);
    }

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> existCustomer = customerRepo.findByNameAndMail(customer.getName(), customer.getMail());
        if (existCustomer.isPresent()){
            throw new RuntimeException("The Customer has already been saved.");
        }
        Customer newCustomer = modelMapper.map(customer, Customer.class);
        return customerRepo.save(newCustomer);
    }

    @Override
    public Customer get(Long id) {
        return customerRepo.findById(id).orElseThrow(()-> new RuntimeException(("id:") +"Customer could not found!"));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }
}
