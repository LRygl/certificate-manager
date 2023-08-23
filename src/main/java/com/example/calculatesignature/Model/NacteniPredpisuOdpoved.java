package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class NacteniPredpisuOdpoved {

    @JsonProperty("Doklad")
    private Doklad doklad;

    @JsonProperty("ZpravaOdpoved")
    private ZpravaOdpoved zpravaOdpoved;
}
