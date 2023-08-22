package com.example.calculatesignature.Utils;

import com.example.calculatesignature.Model.AppPingOdpoved;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParseXmlResponse {

    public static AppPingOdpoved parseResponse(String xmlResponse) throws JAXBException {

        // Unmarshal inner content
        int startIndex = xmlResponse.indexOf("<AppPingOdpoved xmlns=\"http://www.sukl.cz/erp/common\">");
        int endIndex = xmlResponse.lastIndexOf("</AppPingOdpoved>") + "</AppPingOdpoved>".length();
        String innerContent = xmlResponse.substring(startIndex, endIndex);

        try {
            XmlMapper xmlMapper = new XmlMapper();
            AppPingOdpoved appPingOdpoved = xmlMapper.readValue(innerContent, AppPingOdpoved.class);
            return appPingOdpoved;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
