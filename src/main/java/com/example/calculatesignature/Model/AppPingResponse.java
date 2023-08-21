package com.example.calculatesignature.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "AppPingOdpoved", namespace = "http://www.sukl.cz/erp/common")
@Data
public class AppPingResponse {
    @XmlElement(name = "ZpravaOdpoved")
    private ZpravaOdpoved zpravaOdpoved;

}
