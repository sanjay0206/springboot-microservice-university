package com.infybuzz.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infybuzz.response.AddressResponse;

// @FeignClient(value = "address-service", path = "/api/address")
<<<<<<< HEAD
@FeignClient(value = "api-gateway", path = "/address-service/api/address")
public interface AddressFeignClient {

	@GetMapping(path = "/getById/{id}")
=======
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

	@GetMapping(path = "/address-service/api/address/getById/{id}")
>>>>>>> 98acbe0 (first commit)
    AddressResponse getById(@PathVariable long id);
	
}
