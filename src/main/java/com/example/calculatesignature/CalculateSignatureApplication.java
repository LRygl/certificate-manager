package com.example.calculatesignature;

import jakarta.xml.bind.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//https://oidref.com/2.5.4.5

@SpringBootApplication
@RequiredArgsConstructor
public class CalculateSignatureApplication {

    public static void main(String[] args) throws JAXBException {
        SpringApplication.run(CalculateSignatureApplication.class, args);

    }

}
