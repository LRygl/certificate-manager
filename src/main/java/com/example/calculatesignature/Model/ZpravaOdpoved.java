package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZpravaOdpoved {
    @JsonProperty("ID_Zpravy")
    private String idZpravy;

    @JsonProperty("Verze")
    private String verze;

    @JsonProperty("Odeslano")
    private String odeslano;

    @JsonProperty("Aplikace")
    private String aplikace;

    @JsonProperty("ID_Podani")
    private String idPodani;

    @JsonProperty("Prijato")
    private String prijato;
}
