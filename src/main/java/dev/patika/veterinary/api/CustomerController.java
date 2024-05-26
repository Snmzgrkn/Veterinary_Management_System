package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinary.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest){
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest,Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer,CustomerResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id){
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer,CustomerResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Customer> customerPage = this.customerService.cursor(page,pageSize);

        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer,CustomerResponse.class));


        return ResultHelper.cursor(customerResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){
        Customer updateCustomer= this.modelMapper.forRequest().map(customerUpdateRequest,Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer,CustomerResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/searchByName")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findCustomersByName (@RequestParam String name){
        return customerService.findCustomersByName(name);
    }

}
