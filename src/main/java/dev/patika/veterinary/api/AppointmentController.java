package dev.patika.veterinary.api;

import dev.patika.veterinary.business.abstracts.IAnimalService;
import dev.patika.veterinary.business.abstracts.IAppointmentService;
import dev.patika.veterinary.business.abstracts.IDoctorService;
import dev.patika.veterinary.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.core.result.Result;
import dev.patika.veterinary.core.result.ResultData;
import dev.patika.veterinary.core.utilies.ResultHelper;
import dev.patika.veterinary.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinary.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinary.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinary.dto.response.CursorResponse;
import dev.patika.veterinary.dto.response.appointment.AppointmentResponse;
import dev.patika.veterinary.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.entities.Animal;
import dev.patika.veterinary.entities.Appointment;
import dev.patika.veterinary.entities.Customer;
import dev.patika.veterinary.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService, IDoctorService doctorService, IAnimalService animalService, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest){
        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());

        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);
        saveAppointment.setDoctor(doctor);
        saveAppointment.setAnimal(animal);
        saveAppointment.setDate(appointmentSaveRequest.getDate());
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id){
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page,pageSize);

        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));


        return ResultHelper.cursor(appointmentResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){
        Appointment updateAppointment= this.modelMapper.forRequest().map(appointmentUpdateRequest,Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment,AppointmentResponse.class));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

}
