package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppPingOdpoved {
    @JsonProperty("ZpravaOdpoved")
    private ZpravaOdpoved zpravaOdpoved;
}
