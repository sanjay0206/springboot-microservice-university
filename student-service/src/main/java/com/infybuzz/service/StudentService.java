package com.infybuzz.service;

import com.infybuzz.entity.Student;
import com.infybuzz.external.client.AddressFeignClient;
import com.infybuzz.repository.StudentRepository;
import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.AddressResponse;
import com.infybuzz.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final WebClient webClient;
    private final CommonService commonService;
    private final AddressFeignClient addressFeignClient;

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          WebClient webClient,
                          CommonService commonService,
                          AddressFeignClient addressFeignClient) {
        this.studentRepository = studentRepository;
        this.webClient = webClient;
        this.commonService = commonService;
        this.addressFeignClient = addressFeignClient;
    }

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        log.error("Inside createStudent = {}", createStudentRequest);

        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());

        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);

//         AddressResponse addressResponse = getAddressById(student.getAddressId());
//         AddressResponse addressResponse = commonService.getById(student.getAddressId());
        AddressResponse addressResponse = addressFeignClient.getById(student.getAddressId());
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }

    public StudentResponse getById(long id) {
        log.info("Inside getById = {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id: " + id + " not found."));
        StudentResponse studentResponse = new StudentResponse(student);

//        AddressResponse addressResponse = getAddressById(student.getAddressId());
//         AddressResponse addressResponse = commonService.getAddressById(student.getAddressId());
        AddressResponse addressResponse = addressFeignClient.getById(student.getAddressId());
        studentResponse.setAddressResponse(addressResponse);

        return studentResponse;
    }

    // Resilient4j internally uses Spring AOP so here if we use it won't work
  /*  @CircuitBreaker(name = "external", fallbackMethod = "getAddressByIdFallback")
    public AddressResponse getAddressById(long addressId) {

        // REST Call using Web Client
        Mono<AddressResponse> addressResponse =
                webClient.get()
                        .uri("/getById/" + addressId)
                        .retrieve()
                        .bodyToMono(AddressResponse.class);

        return addressResponse.block();
    }

    public AddressResponse getAddressByIdFallback(long addressId, Throwable th) {
        log.error("Error = {}", String.valueOf(th));
        return new AddressResponse();
    }*/
}
