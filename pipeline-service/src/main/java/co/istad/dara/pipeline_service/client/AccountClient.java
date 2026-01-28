package co.istad.dara.pipeline_service.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/api/v1/accounts")
public interface AccountClient {

    @GetExchange
    Map<String,Object> getAllAccounts ();
}
