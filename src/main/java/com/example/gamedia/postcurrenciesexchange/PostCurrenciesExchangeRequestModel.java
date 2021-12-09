package com.example.gamedia.postcurrenciesexchange;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostCurrenciesExchangeRequestModel {
    private String from;
    private List<String> to;
    private Double amount;
}
