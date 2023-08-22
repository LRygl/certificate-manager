package com.example.calculatesignature;

import com.example.calculatesignature.Model.*;
import com.example.calculatesignature.Utils.CertificateAndAuthUtils;
import com.example.calculatesignature.Utils.DatetimeUtils;
import jakarta.xml.bind.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import static com.example.calculatesignature.Utils.ParseXmlResponse.parseResponse;

//https://oidref.com/2.5.4.5

@SpringBootApplication
@RequiredArgsConstructor
public class CalculateSignatureApplication {

    public static void main(String[] args) throws JAXBException {
        SpringApplication.run(CalculateSignatureApplication.class, args);
    }

}
