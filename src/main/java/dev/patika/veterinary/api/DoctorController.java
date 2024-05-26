package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.ICustomerService;
import dev.patika.veterinary.business.abstracts.IDoctorService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinary.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinary.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinary.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.dto.response.doctor.DoctorResponse;
import dev.patika.veterinary.entities.Customer;
import dev.patika.veterinary.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest,Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor,DoctorResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id){
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor,DoctorResponse.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Doctor> doctorPage = this.doctorService.cursor(page,pageSize);

        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse().map(doctor,DoctorResponse.class));


        return ResultHelper.cursor(doctorResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        Doctor updateDoctor= this.modelMapper.forRequest().map(doctorUpdateRequest,Doctor.class);
        this.doctorService.update(updateDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDoctor,DoctorResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}
