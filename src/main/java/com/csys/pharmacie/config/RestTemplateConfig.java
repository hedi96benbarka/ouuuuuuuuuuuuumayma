/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.pharmacie.config;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Farouk
 */
@Configuration
@Profile({"dev","test"})
public class RestTemplateConfig {



   @Bean
    public RestTemplate newInterceptingTemplate(ClientHttpRequestInterceptor... additionalInterceptors) {

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restTemplate.setInterceptors(setInterceptors(additionalInterceptors));
        return restTemplate;

    }

    private static List<ClientHttpRequestInterceptor> setInterceptors(ClientHttpRequestInterceptor... additionalInterceptors) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        if (additionalInterceptors.length > 0) {
            interceptors.addAll(Arrays.asList(additionalInterceptors));
        }

        return interceptors;
    }
}
