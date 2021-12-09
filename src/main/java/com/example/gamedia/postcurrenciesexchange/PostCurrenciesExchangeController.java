package com.example.gamedia.postcurrenciesexchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("currencies")
@RequiredArgsConstructor
public class PostCurrenciesExchangeController {

    private final PostCurrenciesExchangeService service;

    @PostMapping("exchange")
    public ResponseEntity<?> getCurrenciesExchange(
            @RequestBody PostCurrenciesExchangeRequestModel request) throws JsonProcessingException {

        PostCurrenciesExchangeResponseModel response = service.getResponse(request);

        ObjectMapper mapper = new ObjectMapper();

        return ResponseEntity
                .status(201)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(response.getInfo()));
    }
}
