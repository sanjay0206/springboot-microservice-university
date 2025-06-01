package com.infybuzz.service;

import com.infybuzz.entity.Address;
import com.infybuzz.repository.AddressRepository;
import com.infybuzz.request.CreateAddressRequest;
import com.infybuzz.response.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        Address savedAddress = addressRepository.save(address);

        return new AddressResponse(savedAddress);
    }

    public AddressResponse getById(long id) {
        log.info("Inside getById {}", id);

        Address address = addressRepository.findById(id).orElse(null);

        return address == null ? null : new AddressResponse(address);
    }
}
