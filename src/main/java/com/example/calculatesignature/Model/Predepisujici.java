package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Predepisujici {

    @JsonProperty("Uzivatel")
    private Uzivatel uzivatel;

    @JsonProperty("ICP")
    private String icp;

    @JsonProperty("PZS")
    private PZS pzs;

    @JsonProperty("Telefon")
    private String telefon;
}
