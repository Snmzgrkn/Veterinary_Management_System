package dev.patika.veterinary.dto.request.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
