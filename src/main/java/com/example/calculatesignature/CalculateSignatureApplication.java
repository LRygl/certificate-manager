package com.example.calculatesignature;

import com.example.calculatesignature.Model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

//https://oidref.com/2.5.4.5

@SpringBootApplication
public class CalculateSignatureApplication {

    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, JAXBException {
        SpringApplication.run(CalculateSignatureApplication.class, args);

        String certificateFilePath = "/test3.cer";

        getX509CertificateValue(certificateFilePath);
        getX509CertificateDetails(certificateFilePath);

        constructAppPingDotaz();
    }

    private static void constructAppPingDotaz() throws JAXBException {
        Envelope envelope = new Envelope();
        envelope.setHeader("");

        Body body = new Body();
        AppPingDotaz appPingDotaz = new AppPingDotaz();
        Doklad doklad = new Doklad();
        Pristupujici pristupujici = new Pristupujici();

        pristupujici.setUzivatel("00150390017");
        pristupujici.setPracoviste("00150389779");

        doklad.setPristupujici(pristupujici);
        appPingDotaz.setDoklad(doklad);

        Zprava zprava = new Zprava();
        zprava.setID_Zpravy(UUID.randomUUID().toString());
        zprava.setVerze("202307A");
        zprava.setOdeslano(getCurrentFormatedDate());
        zprava.setSW_Klienta("0123456789AB");

        appPingDotaz.setZprava(zprava);
        envelope.setBody(body);
        body.setAppPingDotaz(appPingDotaz);

        // Now, you can marshal the objects into XML
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(envelope, System.out);

    }

    private static String getCurrentFormatedDate(){
        ZonedDateTime dateTime = ZonedDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
        return dateTime.format(formatter);
    }

    private static void getX509CertificateDetails(String certificateFilePath){
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

    private static void getX509CertificateValue(String certificateFilePath){
        try {
            byte[] certBytes = Files.readAllBytes(Paths.get(certificateFilePath));

            // Create a CertificateFactory and parse the certificate
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(certBytes);
            Certificate certificate = certificateFactory.generateCertificate(inputStream);

            // Convert the certificate bytes to Base64-encoded string with proper formatting
            String base64Cert = Base64.getMimeEncoder(64, System.lineSeparator().getBytes()).encodeToString(certificate.getEncoded());

            // Print the formatted output
            System.out.println("<ds:X509Certificate>" + base64Cert + "</ds:X509Certificate>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
