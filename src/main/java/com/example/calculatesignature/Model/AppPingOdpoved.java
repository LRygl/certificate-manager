package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AppPingOdpoved {
    @JsonProperty("ZpravaOdpoved")
    private ZpravaOdpoved zpravaOdpoved;
}
