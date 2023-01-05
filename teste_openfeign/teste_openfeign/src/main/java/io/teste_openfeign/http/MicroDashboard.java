package io.teste_openfeign.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("micro-dashboard")
public interface MicroDashboard {

    @RequestMapping(method = RequestMethod.PUT, value = "/dashboard")
    List<String> atualizaLista(@RequestBody String valor);
}
