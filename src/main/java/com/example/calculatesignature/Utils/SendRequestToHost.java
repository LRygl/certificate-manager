package com.example.calculatesignature.Utils;

import com.example.calculatesignature.CalculateSignatureApplication;
import jakarta.xml.bind.JAXBException;
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
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;

import static com.example.calculatesignature.Utils.ConstructRequest.constructAppPingDotaz;

@Service
@RequiredArgsConstructor
public class SendRequestToHost {

    private final ConstructRequest constructRequest;

    public static String sendRequest() throws IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException, CertificateException {
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
            String header = new CertificateAndAuthUtils().getBasicAuthHeader();
            httpPost.setHeader("Authorization",  header);
            httpPost.setHeader("Content-Type", "text/xml");

            HttpEntity requestEntity = new InputStreamEntity(new ByteArrayInputStream(constructAppPingDotaz().getBytes()),
                    ContentType.APPLICATION_XML);
            httpPost.setEntity(requestEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
                return EntityUtils.toString(response.getEntity());
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
