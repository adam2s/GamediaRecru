package com.example.gamedia.getcurrenciesrate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("currencies")
@RequiredArgsConstructor
public class GetCurrenciesRateController {

    private final GetCurrenciesRateService service;

    @GetMapping("{source}")
    public ResponseEntity<GetCurrenciesRateResponseModel> getCurrenciesRates(
            @PathVariable String source,
            @RequestParam(value = "filter[]") String[] filter){

        GetCurrenciesRateResponseModel response = service.getResponse(source,filter);

        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
