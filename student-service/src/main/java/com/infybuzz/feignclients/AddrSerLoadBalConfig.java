//
//package com.infybuzz.feignclients;
//
//import feign.Feign;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@LoadBalancerClient(value = "address-service")
//public class AddrSerLoadBalConfig {
//
//    @LoadBalanced
//    @Bean
//    public Feign.Builder feignBuilder () {
//        return Feign.builder();
//    }
//
//    @LoadBalanced
//    @Bean
//    public WebClient.Builder webClientBuilder() {
//        return WebClient.builder();
//    }
//
//    @LoadBalanced
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//}
//
