package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Totoznost {

    @JsonProperty("Jmeno")
    private Jmeno jmeno;

    @JsonProperty("DatumNarozeni")
    private String datumNarozeni;

    @JsonProperty("Adresa")
    private Adresa adresa;
}
