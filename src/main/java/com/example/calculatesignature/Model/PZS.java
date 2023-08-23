package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
//as
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PZS {
    @JsonProperty("Kod")
    private String kod;
    @JsonProperty("Nazev")
    private String nazev;
    @JsonProperty("IC")
    private String ico;
    @JsonProperty("Telefon")
    private String telefon;
    @JsonProperty("Adresa")
    private Adresa adresa;
;



}
