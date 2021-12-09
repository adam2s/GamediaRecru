package com.example.gamedia.postcurrenciesexchange;

import com.example.gamedia.apiconnector.CoinGeckoApiConnector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PostCurrenciesExchangeService {

    private final Double FEE = 0.01d;

    public PostCurrenciesExchangeResponseModel getResponse(PostCurrenciesExchangeRequestModel request) {
        PostCurrenciesExchangeResponseModel response = new PostCurrenciesExchangeResponseModel();

        Map<String, CurrencyDetailsModel> currenciesExchangeInfos = new LinkedHashMap<>();

        Thread[] threads = new Thread[request.getTo().size()];

        int threadIndex = 0;

        for(String curr: request.getTo()){
//            BigDecimal rate = CoinGeckoApiConnector.getCurrencyRate(request.getFrom(), curr);
//
//            if(!rate.equals(new BigDecimal("0.0"))) {
//                CurrencyDetailsModel CDModel = new CurrencyDetailsModel();
//
//                CDModel.setAmount(request.getAmount());
//                CDModel.setFee(new BigDecimal(FEE.toString()));
//                CDModel.setRate(rate);
//                CDModel.setResult(BigDecimal.valueOf(CDModel.getAmount()).multiply(rate).multiply(BigDecimal.valueOf(1-FEE)));
//
//                currenciesExchangeInfos.put(curr, CDModel);
//            }

            MultithreadedExchange MExchange = new MultithreadedExchange(FEE,request,curr);

            threads[threadIndex] = new Thread(MExchange);
            threads[threadIndex].start();

            try {
                threads[threadIndex].join();
            } catch(Exception e){
                e.printStackTrace();
            }

            if(MExchange.getCDModel() != null)
                currenciesExchangeInfos.put(curr,MExchange.getCDModel());

            threadIndex++;
        }

        response.put(request.getFrom(), currenciesExchangeInfos);

        return response;
    }
}

@Getter
@RequiredArgsConstructor
class MultithreadedExchange implements Runnable{

    private final Double FEE;
    private CurrencyDetailsModel CDModel = null;
    private final PostCurrenciesExchangeRequestModel request;
    private final String curr;

    @Override
    public void run() {

        BigDecimal rate = CoinGeckoApiConnector.getCurrencyRate(request.getFrom(), curr);

        System.out.println(curr + " : " + rate);

        if(!rate.equals(new BigDecimal("0.0"))) {
            CDModel = new CurrencyDetailsModel();

            CDModel.setAmount(request.getAmount());
            CDModel.setFee(new BigDecimal(FEE.toString()));
            CDModel.setRate(rate);
            CDModel.setResult(BigDecimal.valueOf(CDModel.getAmount()).multiply(rate).multiply(BigDecimal.valueOf(1-FEE)));
        }
    }
}
