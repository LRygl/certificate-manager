package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Signature {

    private SignedInfo signedInfo;
    private String signatureValue;
    private KeyInfo keyInfo;
}
