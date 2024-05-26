package dev.patika.veterinary.dto.request.customer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

}