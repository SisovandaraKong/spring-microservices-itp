package co.istad.dara.pipeline_service.controller;

import co.istad.dara.pipeline_service.client.AccountClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/client/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountClient accountClient;

    @GetMapping("/secured")
    public Map<String, Object> getAllAccounts(){
        return accountClient.getAllAccounts();
    }

}
