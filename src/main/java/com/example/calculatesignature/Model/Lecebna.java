package com.example.calculatesignature.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Lecebna {
    @JsonProperty("ZPL")
    private ZPL zpl;
    @JsonProperty("StupenInkontinence")
    private String stupenInkontinence;
    @JsonProperty("DocasnaPocetMesicu")
    private String docasnaPocetMesicu;
    @JsonProperty("Repasovana")
    private String repasovana;

}
