package com.infybuzz.response;

import com.infybuzz.entity.Student;
import lombok.Data;

@Data
public class StudentResponse {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressResponse addressResponse;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
    }
}
