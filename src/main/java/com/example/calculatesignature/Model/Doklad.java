package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Doklad {
    @XmlElement(name = "com:Pristupujici")
    private Pristupujici Pristupujici;

    @XmlElement(name = "cuep:Identifikator")
    private Identifikator identifikator;

    @JsonProperty("ID_Dokladu")
    private String Id_Dokladu;

    @JsonProperty("DatumVystaveni")
    private String DatumVystaveni;

    @JsonProperty("PlatnostDo")
    private String platnostDo;

    @JsonProperty("Rodina")
    private String rodina;

    @JsonProperty("Pacient")
    private Pacient pacient;

    @JsonProperty("Predepisujici")
    private Predepisujici predepisujici;

    @JsonProperty("Lecebna")
    private Lecebna lecebna;

    @JsonProperty("Diagnoza")
    private String diagnoza;

    @JsonProperty("DruhPojisteni")
    private String druhPojisteni;

    @JsonProperty("StavPoukazu")
    private String stavPoukazu;

    @JsonProperty("StavSchvalovani")
    private String stavSchvalovani;
    @JsonProperty("PocetPriloh")
    private String pocetPriloh;
    @JsonProperty("Zalozeni")
    private String zalozeni;
    @JsonProperty("Zmena")
    private String zmena;

}