package com.example.calculatesignature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.x500.X500Principal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class CalculateSignatureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculateSignatureApplication.class, args);

        String certificateFilePath = "/test3.cer";

        try (FileInputStream fis = new FileInputStream(certificateFilePath)) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(fis);
            X500Principal subjectPrincipal = certificate.getSubjectX500Principal();

        // Print certificate information
            System.out.println("Subject: " + certificate.getSubjectX500Principal().getName());

        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }



    }
}
