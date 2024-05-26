package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dao.AnimalRepo;
import dev.patika.veterinary.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinary.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinary.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinary.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Customer;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public AnimalController(IAnimalService animalService, ICustomerService customerService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());

        Animal saveAnimal= this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);
        saveAnimal.setCustomer(customer);
        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal,AnimalResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id){
        // Veritabanından hayvanı çek
        Animal animal = this.animalService.get(id);
        if (animal == null) {
            throw new EntityNotFoundException("Animal not found with id: " + id);
        }

        // Hayvanı AnimalResponse'a map et
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);


        // AnimalResponse'u başarıyla dön
        return ResultHelper.success(animalResponse);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Animal> animalPage = this.animalService.cursor(page,pageSize);

        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal,AnimalResponse.class));


        return ResultHelper.cursor(animalResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        // Mevcut hayvanı veritabanından çek
        Animal existingAnimal = this.animalService.get(animalUpdateRequest.getId());
        if (existingAnimal == null) {
            throw new EntityNotFoundException("Animal not found with id: " + animalUpdateRequest.getId());
        }

        // Yeni müşteri ID'sine göre müşteri nesnesini al
        Customer customer = this.customerService.get(animalUpdateRequest.getCustomerId());
        if (customer == null) {
            throw new EntityNotFoundException("Customer not found with id: " + animalUpdateRequest.getCustomerId());
        }

        // Gerekli alanları güncelle
        existingAnimal.setName(animalUpdateRequest.getName());
        existingAnimal.setSpecies(animalUpdateRequest.getSpecies());
        existingAnimal.setBreed(animalUpdateRequest.getBreed());
        existingAnimal.setGender(animalUpdateRequest.getGender());
        existingAnimal.setDateOfBirth(animalUpdateRequest.getDateOfBirth());
        existingAnimal.setColour(animalUpdateRequest.getColour());
        existingAnimal.setCustomer(customer);

        // Hayvanı güncelle
        this.animalService.update(existingAnimal);

        // Güncellenen hayvanı AnimalResponse'a map et ve geri döndür
        return ResultHelper.success(this.modelMapper.forResponse().map(existingAnimal,AnimalResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/searchByName")
    public List<Animal> findAnimalsByName (@RequestParam String name){
        return animalService.findAnimalsByName(name);
    }
    @GetMapping("/searchByCustomer")
    public List<Animal> findAnimalsByCustomer (@RequestParam Long id){
        return animalService.findAnimalsByCustomer(id);
    }
}
