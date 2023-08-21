package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
public class ZpravaOdpoved {
    @XmlElement(name = "ID_Zpravy")
    private String idZpravyResponse;

    @XmlElement(name = "Verze")
    private String verzeResponse;

    @XmlElement(name = "Odeslano")
    private String odeslanoResponse;

    @XmlElement(name = "Aplikace")
    private String aplikaceResponse;

    @XmlElement(name = "ID_Podani")
    private String idPodaniResponse;

    @XmlElement(name = "Prijato")
    private String prijatoResponse;
}
