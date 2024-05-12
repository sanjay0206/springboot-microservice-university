package com.infybuzz.service;

import com.infybuzz.feignclients.AddressFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.infybuzz.entity.Student;
import com.infybuzz.repository.StudentRepository;
import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.AddressResponse;
import com.infybuzz.response.StudentResponse;

import reactor.core.publisher.Mono;

@Service
public class StudentService {
	Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;

	/*@Autowired
	AddressFeignClient addressFeignClient;*/
	@Autowired
	CommonService commonService;


	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		logger.error("Inside createStudent = " + createStudentRequest);

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);

		// AddressResponse addressResponse = getAddressById(student.getAddressId());
		// AddressResponse addressResponse = addressFeignClient.getById(student.getAddressId());
		AddressResponse addressResponse = commonService.getAddressById(student.getAddressId());
		studentResponse.setAddressResponse(addressResponse);

		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		logger.info("Inside getById = " + id);

		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);

		// AddressResponse addressResponse = getAddressById(student.getAddressId());
		// AddressResponse addressResponse = addressFeignClient.getById(student.getAddressId());
		AddressResponse addressResponse = commonService.getAddressById(student.getAddressId());
		studentResponse.setAddressResponse(addressResponse);
		
		return studentResponse;
	}

	// Resilient4j internally uses Spring AOP so here if we use it won't work
/*	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
	public AddressResponse getAddressById (long addressId) {

		// REST Call using Web Client
		*//*	Mono<AddressResponse> addressResponse =
				webClient.get().uri("/getById/" + addressId)
						.retrieve()
						.bodyToMono(AddressResponse.class);

		return addressResponse.block();
		*//*
		return addressFeignClient.getById(addressId);
	}*/

	/*	public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {
		logger.error("Error = " + th);
		return new AddressResponse();
	}*/

}
