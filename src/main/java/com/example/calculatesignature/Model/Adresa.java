package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Adresa {
    @JsonProperty("NazevUlice")
    private String nazevUlice;
    @JsonProperty("CisloPopisne")
    private String cisloPopisne;
    @JsonProperty("NazevObce")
    private String nazevObce;
    @JsonProperty("NazevCastiObce")
    private String nazevCastiObce;
    @JsonProperty("NazevOkresu")
    private String nazevOkresu;
    @JsonProperty("PSC")
    private String psc;
}
