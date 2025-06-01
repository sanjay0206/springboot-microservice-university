package com.infybuzz.controller;

import com.infybuzz.request.CreateAddressRequest;
import com.infybuzz.response.AddressResponse;
import com.infybuzz.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest) {
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id) {
        return addressService.getById(id);
    }
}
