package com.example.calculatesignature;

import com.example.calculatesignature.Model.*;
import jakarta.xml.bind.*;
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

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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

//https://oidref.com/2.5.4.5

@SpringBootApplication
public class CalculateSignatureApplication {

    public static void main(String[] args) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, JAXBException, UnrecoverableKeyException, KeyManagementException {
        SpringApplication.run(CalculateSignatureApplication.class, args);

        String certificateFilePath = "/test3.cer";

        getX509CertificateValue(certificateFilePath);
        getX509CertificateDetails(certificateFilePath);

        constructAppPingDotaz();
        generateBasicAuthentication();
        sendRequestToHost();
        parseXmlResponseToObject();
    }


    private static void sendRequestToHost() throws IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, CertificateException {
        String url = "https://cuep-soap.test-erecept.sukl.cz";

        String pfxFilePath = "AMBSUKL150389781G.pfx"; // Update this with the actual path
        String pfxPassword = "Mentors2023";

        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // Load the PFX certificate from the resources folder
        ClassLoader classLoader = CalculateSignatureApplication.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(pfxFilePath);
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(inputStream, pfxPassword.toCharArray());


        SSLContextBuilder sslContextBuilder = SSLContextBuilder.create()
                .loadKeyMaterial(keyStore, pfxPassword.toCharArray());

        // Set up a custom SSL connection socket factory
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                sslContextBuilder.build(),
                NoopHostnameVerifier.INSTANCE);

        // Create HttpClient with custom SSL socket factory
        HttpPost httpPost;
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build()){

            httpPost = new HttpPost(url);
            httpPost.setHeader("Authorization", generateBasicAuthentication());
            httpPost.setHeader("Content-Type", "text/xml");

            HttpEntity requestEntity = new InputStreamEntity(new ByteArrayInputStream(constructAppPingDotaz().getBytes()),
                    ContentType.APPLICATION_XML);
            httpPost.setEntity(requestEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseXmlResponseToObject(){

        String soapResponseXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><AppPingOdpoved xmlns=\"http://www.sukl.cz/erp/common\"><ZpravaOdpoved><ID_Zpravy>90F6FC79-AFEB-4297-B035-BD033D8C80E5</ID_Zpravy><Verze>202307A</Verze><Odeslano>2023-08-21T22:45:32.9565232+02:00</Odeslano><Aplikace>Informační systém eRecept, v. 1.51.0.29949</Aplikace><ID_Podani>AA05947C-5C7F-42AC-BC4F-F45CBC5ED78A</ID_Podani><Prijato>2023-08-21T22:45:32.8939506+02:00</Prijato></ZpravaOdpoved></AppPingOdpoved></soap:Body></soap:Envelope>";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AppPingResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(soapResponseXml);

            AppPingResponse appPingResponse = (AppPingResponse) unmarshaller.unmarshal(reader);

            ZpravaOdpoved zpravaOdpoved = appPingResponse.getZpravaOdpoved();

            // Access fields of ZpravaOdpoved as needed
            System.out.println("ID_Zpravy: " +  zpravaOdpoved.getIdZpravyResponse());
            System.out.println("Verze: " + zpravaOdpoved.getVerzeResponse());
            // ... and so on

        } catch (JAXBException e) {
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
    private static String generateBasicAuthentication() {
        String login = "00150390017";
        String password = "+Sphingalama33";
        String delimiter = ":";

        String loginString = login+delimiter+password;
        System.out.println(loginString);

        // Encode the login string using Base64
        String base64Credentials = Base64.getEncoder().encodeToString(loginString.getBytes());
        System.out.println("Base64 Encoded Credentials: " + base64Credentials);

        // Set the Authorization header in BASIC format
        String basicAuthHeader = "BASIC " + base64Credentials;
        System.out.println("Authorization Header: " + basicAuthHeader);

        return basicAuthHeader;

    }
    private static String constructAppPingDotaz() throws JAXBException {
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
        //marshaller.marshal(envelope, System.out);
        StringWriter writer = new StringWriter();
        marshaller.marshal(envelope,writer);
        String xml = writer.toString();
        xml = xml.replace("<soapenv:Envelope","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"http://www.sukl.cz/erp/common\"");
        System.out.println(xml);
        return xml;
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
