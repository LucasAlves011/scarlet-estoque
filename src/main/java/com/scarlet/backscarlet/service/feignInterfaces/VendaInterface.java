package com.scarlet.backscarlet.service.feignInterfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "venda")
public interface VendaInterface {

    @GetMapping("/venda/feign")
    String teste();
}
