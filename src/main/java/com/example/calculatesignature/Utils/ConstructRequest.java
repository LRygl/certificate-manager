package com.example.calculatesignature.Utils;

import com.example.calculatesignature.Model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.UUID;

@Service
public class ConstructRequest {
    public static String constructAppPingDotaz() throws JAXBException {
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
        zprava.setOdeslano(new DatetimeUtils().getCurrentFormatedDate());
        zprava.setSW_Klienta("0123456789AB");

        appPingDotaz.setZprava(zprava);
        envelope.setBody(body);
        body.setAppPingDotaz(appPingDotaz);

        // Now, you can marshal the objects into XML
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(envelope,writer);
        String xml = writer.toString();
        xml = xml.replace("<soapenv:Envelope","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:com=\"http://www.sukl.cz/erp/common\"");
        return xml;
    }
}
