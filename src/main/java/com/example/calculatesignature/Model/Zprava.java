package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zprava")
@Data
public class Zprava {
    @XmlElement(name = "com:ID_Zpravy")
    private String ID_Zpravy;
    @XmlElement(name = "com:Verze")
    private String Verze;
    @XmlElement(name = "com:Odeslano")
    private String Odeslano;
    @XmlElement(name = "com:SW_Klienta")
    private String SW_Klienta;
}