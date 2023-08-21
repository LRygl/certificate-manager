package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Pristupujici {
    @XmlElement(name = "com:Uzivatel")
    private String Uzivatel;
    @XmlElement(name = "com:Pracoviste")
    private String Pracoviste;
}