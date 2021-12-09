package com.example.gamedia.getcurrenciesrate;

import com.example.gamedia.apiconnector.CoinGeckoApiConnector;
import com.example.gamedia.utils.CoinGeckoTsvDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GetCurrenciesRateService {

    public GetCurrenciesRateResponseModel getResponse(String source, String[] filters) {
        GetCurrenciesRateResponseModel responseModel = new GetCurrenciesRateResponseModel();

        responseModel.setSource(source);

        Map<String, BigDecimal> ratesValue = new LinkedHashMap<>();

        for(String filterKey : filters){
            BigDecimal rate = CoinGeckoApiConnector.getCurrencyRate(source, filterKey);

            if(!rate.equals(new BigDecimal("0.0")))
                ratesValue.put(filterKey, rate);
        }

        responseModel.setRates(ratesValue);

        return responseModel;
    }
}
