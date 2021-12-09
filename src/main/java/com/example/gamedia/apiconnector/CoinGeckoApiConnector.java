package com.example.gamedia.apiconnector;

import com.example.gamedia.utils.CoinGeckoTsvDataMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;

@Repository
public class CoinGeckoApiConnector {

    public static BigDecimal getCurrencyRate(String baseC, String supC) {

        baseC = CoinGeckoTsvDataMapper.Data.get(baseC.toLowerCase());

        supC = supC.toLowerCase();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.coingecko.com/api/v3/simple/price?ids="+baseC+"&vs_currencies="+supC)
                .get()
                .addHeader("accept", "application/json")
                .build();

        BigDecimal retVal = new BigDecimal("0.0");

        try {
            JSONObject json = new JSONObject(client.newCall(request).execute().body().string());
            retVal = json.getJSONObject(baseC).getBigDecimal(supC);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
