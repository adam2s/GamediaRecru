package com.example.gamedia.getcurrenciesrate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class GetCurrenciesRateResponseModel {
    private String source;
    private Map<String, BigDecimal> rates;
}
