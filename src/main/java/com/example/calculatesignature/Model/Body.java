package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Body {
    @XmlElement(name = "com:AppPingDotaz")
    AppPingDotaz appPingDotaz;

}
