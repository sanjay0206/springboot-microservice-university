package com.infybuzz.external.client;

import com.infybuzz.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "address-service", path = "/api/address")
//@FeignClient(value = "api-gateway", path = "/address-service/api/address")
public interface AddressFeignClient {

    @CircuitBreaker(name = "external", fallbackMethod = "getByIdFallback")
    @GetMapping(path = "/getById/{id}")
    AddressResponse getById(@PathVariable long id);

    // Fallback for getById
    default AddressResponse getByIdFallback(long addressId, Throwable th) {
        return new AddressResponse();
    }
}
