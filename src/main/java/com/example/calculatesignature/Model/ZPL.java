package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ZPL {

    @JsonProperty("Kod")
    private String kod;
    @JsonProperty("Nazev")
    private String nazev;
    @JsonProperty("Skupina")
    private String skupina;
    @JsonProperty("Mnozstvi")
    private String mnozstvi;
    @JsonProperty("Uhrada")
    private String uhrada;
    @JsonProperty("Nezamenovat")
    private String nezamenovat;
    @JsonProperty("ID_ZP_Zdroj")
    private String id_zp_zdroj;
    @JsonProperty("ID_ZP")
    private String id_zp;

}
