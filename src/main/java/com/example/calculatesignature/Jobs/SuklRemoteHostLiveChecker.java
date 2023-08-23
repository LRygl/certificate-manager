package com.example.calculatesignature.Jobs;

import com.example.calculatesignature.Model.AppPingOdpoved;
import com.example.calculatesignature.Utils.ParseXmlResponse;
import com.example.calculatesignature.Utils.SendRequestToHost;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Component
@RequiredArgsConstructor
public class SuklRemoteHostLiveChecker {

    private final SendRequestToHost sendRequestToHost;

    //@Scheduled(fixedDelay = 10000) // time in ms = 10 seconds
    public void checkRemoteHostStatus() throws UnrecoverableKeyException, CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, JAXBException {

        String result = SendRequestToHost.sendRequest();
        AppPingOdpoved appPingOdpoved = ParseXmlResponse.parseResponse(result);
        System.out.println("Verze: " + appPingOdpoved.getZpravaOdpoved().getVerze());
    }
}
