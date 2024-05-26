package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.business.abstracts.IDoctorService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinary.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinary.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.availableDate.AvailableDateResponse;
import dev.patika.veterinary.dto.response.doctor.DoctorResponse;
import dev.patika.veterinary.entities.AvailableDate;
import dev.patika.veterinary.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available_dates")
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService, IDoctorService doctorService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        Doctor doctor = this.doctorService.get(availableDateSaveRequest.getDoctorId());

        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest,AvailableDate.class);
        saveAvailableDate.setDoctor(doctor);
        this.availableDateService.save(saveAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvailableDate,AvailableDateResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate findAvailableDateById (@PathVariable Long id){
        return availableDateService.get(id);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDate>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<AvailableDate> availableDatePagePage = this.availableDateService.cursor(page,pageSize);

        Page<AvailableDateResponse> availableDateResponses = availableDatePagePage
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));


        return ResultHelper.cursor(availableDatePagePage);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
        AvailableDate availableDate= this.modelMapper.forRequest().map(availableDateUpdateRequest,AvailableDate.class);
        this.availableDateService.update(availableDate);

        return ResultHelper.success(this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
    }
}
