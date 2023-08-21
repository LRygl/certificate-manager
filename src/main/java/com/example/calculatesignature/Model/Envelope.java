package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "soapenv:Envelope")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Envelope {
    @XmlElement(name = "soapenv:Header")
    private String header;

    @XmlElement(name = "soapenv:Body")
    private Body body;
}

