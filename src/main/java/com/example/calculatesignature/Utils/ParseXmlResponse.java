package com.example.calculatesignature.Utils;

import com.example.calculatesignature.Model.AppPingOdpoved;
import com.example.calculatesignature.Model.NacteniPredpisuOdpoved;
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

    public static NacteniPredpisuOdpoved parseNacteniPredpisuOdpoved(String xmlResponse){
        // Unmarshal inner content
        int startIndex = xmlResponse.indexOf("<NacteniPredpisuOdpoved xmlns=\"http://www.sukl.cz/erp/cuep\">");
        int endIndex = xmlResponse.lastIndexOf("</NacteniPredpisuOdpoved>") + "</NacteniPredpisuOdpoved>".length();
        String innerContent = xmlResponse.substring(startIndex, endIndex);
System.out.println("Inner XML content" + innerContent);
        try {
            XmlMapper xmlMapper = new XmlMapper();
            NacteniPredpisuOdpoved nacteniPredpisuOdpoved = xmlMapper.readValue(innerContent, NacteniPredpisuOdpoved.class);
            return nacteniPredpisuOdpoved;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
