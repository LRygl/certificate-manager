package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Pacient {
    @JsonProperty("Totoznost")
    private Totoznost totoznost;

    @JsonProperty("CP")
    private String CP;

    @JsonProperty("ZP")
    private String ZP;

    @JsonProperty("Telefon")
    private String telefon;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Notifikace")
    private String notifikace;

    @JsonProperty("Hmotnost")
    private String hmotnost;

    @JsonProperty("Pohlavi")
    private String pohlavi;
}
