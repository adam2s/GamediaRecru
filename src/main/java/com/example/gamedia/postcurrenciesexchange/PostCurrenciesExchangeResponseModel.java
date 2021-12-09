package com.example.gamedia.postcurrenciesexchange;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;


public class PostCurrenciesExchangeResponseModel {
    private CryptoExchangeInfos info = new CryptoExchangeInfos();

    public CryptoExchangeInfos getInfo(){
        return this.info;
    }

    public void put(String from,Map<String,CurrencyDetailsModel> model){
        info.put(model);
        info.setFrom(from);
    }
}

@Getter
@Setter
@NoArgsConstructor
class CurrencyDetailsModel {
    private BigDecimal rate;
    private Double amount;
    private BigDecimal result;
    private BigDecimal fee;
}

class CryptoExchangeInfos{
    private String from;
    private Map<String, CurrencyDetailsModel> cValues;

    public void setFrom(String from){
        this.from = from;
    }

    public String getFrom(){
        return this.from;
    }

    public void put(Map<String,CurrencyDetailsModel> model){
        cValues = model;
    }

    @JsonAnyGetter
    public Map<String, CurrencyDetailsModel> getCValues(){
        return cValues;
    }

    @Override
    public String toString() {
        return from + cValues.toString();
    }
}

