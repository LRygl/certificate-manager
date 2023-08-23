package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Uzivatel {

    @JsonProperty("Kod")
    private String kod;

    @JsonProperty("Jmeno")
    private Jmeno jmeno;


}
