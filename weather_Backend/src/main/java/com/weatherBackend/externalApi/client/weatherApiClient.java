package com.weatherBackend.externalApi.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class weatherApiClient {

    private final RestClient weatherRestClient;



}
