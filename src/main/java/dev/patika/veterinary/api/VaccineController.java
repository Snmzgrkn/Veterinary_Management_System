package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IDoctorService;
import dev.patika.veterinary.business.abstracts.IVaccineService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinary.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinary.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinary.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.doctor.DoctorResponse;
import dev.patika.veterinary.dto.response.vaccine.VaccineResponse;
import dev.patika.veterinary.entities.Customer;
import dev.patika.veterinary.entities.Doctor;
import dev.patika.veterinary.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){
        Vaccine vaccine = this.modelMapper.forRequest().map(vaccineSaveRequest,Vaccine.class);
        this.vaccineService.save(vaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id){
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page,pageSize);

        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));

        return ResultHelper.cursor(vaccineResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){
        Vaccine updateVaccine= this.modelMapper.forRequest().map(vaccineUpdateRequest,Vaccine.class);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine,VaccineResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }
    @GetMapping("/searchByAnimal")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findVaccinecsByAnimal (@RequestParam Long id){
        return vaccineService.findVaccinesByAnimal(id);
    }
    @GetMapping("/searchByVaccinationDates")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findVaccinecsByVaccinationDates (@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return vaccineService.findAnimalsByVaccineProtectionFinishDateRange(startDate,endDate);
    }
}
