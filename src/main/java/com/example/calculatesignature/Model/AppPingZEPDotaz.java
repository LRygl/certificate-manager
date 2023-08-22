package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AppPingZEPDotaz {
    @XmlElement(name = "com:Doklad")
    private Doklad Doklad;
    @XmlElement(name = "com:Zprava")
    private Zprava Zprava;
    @XmlElement(name = "ds:Signature")
    private Signature signature;
}