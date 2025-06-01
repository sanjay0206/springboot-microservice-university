package com.infybuzz.service;

import com.infybuzz.external.client.AddressFeignClient;
import com.infybuzz.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommonService {

    long count = 1;

    private final AddressFeignClient addressFeignClient;

    @Autowired
    public CommonService(AddressFeignClient addressFeignClient) {
        this.addressFeignClient = addressFeignClient;
    }

    @CircuitBreaker(name = "external", fallbackMethod = "getAddressByIdFallback")
    public AddressResponse getAddressById(long addressId) {
        log.info("count = {}", count);
        count++;

        return addressFeignClient.getById(addressId);
    }

    public AddressResponse getAddressByIdFallback(long addressId, Throwable th) {
        log.error("Error = {}", th.getMessage());
        return new AddressResponse();
    }
}
