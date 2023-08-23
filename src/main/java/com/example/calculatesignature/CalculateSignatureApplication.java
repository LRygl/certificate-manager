package com.example.calculatesignature;

import com.example.calculatesignature.Model.AppPingOdpoved;
import com.example.calculatesignature.Model.NacteniPredpisuOdpoved;
import com.example.calculatesignature.Utils.ParseXmlResponse;
import com.example.calculatesignature.Utils.SendRequestToHost;
import jakarta.xml.bind.*;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import static com.example.calculatesignature.Utils.ConstructRequest.constructCuepLoadEVoucherData;

//https://oidref.com/2.5.4.5

@SpringBootApplication
@RequiredArgsConstructor
public class CalculateSignatureApplication {
    private final SendRequestToHost sendRequestToHost;
    public static void main(String[] args) throws JAXBException, UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SpringApplication.run(CalculateSignatureApplication.class, args);

        String result = SendRequestToHost.sendRequest();
        System.out.println("Response from Host: "+result);
        NacteniPredpisuOdpoved nacteniPredpisuOdpoved = ParseXmlResponse.parseNacteniPredpisuOdpoved(result);

        System.out.println(nacteniPredpisuOdpoved.getDoklad().getId_Dokladu());

    }

}
