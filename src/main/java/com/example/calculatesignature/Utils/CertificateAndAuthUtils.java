package com.example.calculatesignature.Utils;

import org.springframework.stereotype.Service;

import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

@Service
public class CertificateAndAuthUtils {

    String certificateFilePath = "/test3.cer";

    public String getBasicAuthHeader(){
        String login = "00150390017";
        String password = "+Sphingalama33";
        String delimiter = ":";

        String loginString = login+delimiter+password;

        // Encode the login string using Base64
        String base64Credentials = Base64.getEncoder().encodeToString(loginString.getBytes());

        // Set the Authorization header in BASIC format
        String basicAuthHeader = "BASIC " + base64Credentials;

        return basicAuthHeader;
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

    private static class TrustAllCertificates implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        }
    }


}
