package com.infybuzz.request;


import lombok.Data;

@Data
public class CreateAddressRequest {
    private String street;
    private String city;
}
