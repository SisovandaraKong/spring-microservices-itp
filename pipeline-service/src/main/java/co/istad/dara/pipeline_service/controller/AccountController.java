//package co.istad.dara.pipeline_service.controller;
//
//import co.istad.dara.pipeline_service.service.AccountService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequestMapping("/client/accounts")
//@RequiredArgsConstructor
//public class AccountController {
//    private final AccountService accountService;
//
//    @GetMapping("/secured")
//    public Map<String, Object> getAllAccounts(){
//        log.info("Received request to get all accounts");
//        return accountService.getAllAccounts();
//    }
//}

package co.istad.dara.pipeline_service.controller;

import co.istad.dara.pipeline_service.client.AccountClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/client/accounts")
//@RequiredArgsConstructor
public class AccountController {
    private final AccountClient accountClient;
    private final io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker;

    public AccountController (AccountClient accountClient, CircuitBreakerRegistry circuitBreakerRegistry){
            this.accountClient = accountClient;
            circuitBreaker = circuitBreakerRegistry.circuitBreaker("accountService");
    }


    @GetMapping("/secured")
    public Map<String, Object> getSecuredData() {
        log.debug("getSecuredData");
        //return accountClient.getSecuredData();
        try {
            return circuitBreaker.executeSupplier(accountClient::getAllAccounts);
        } catch (CallNotPermittedException e) {
            return Map.of("data", e.getMessage());
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return Map.of("data", e.getMessage());
        }
    }

//    @GetMapping("/secured")
////    @CircuitBreaker(name = "accountService", fallbackMethod = "accountFallback")
//    public Map<String, Object> getAllAccounts(){
//        log.info("Calling account service...");
//        return accountClient.getAllAccounts();
//    }

//    public Map<String, Object> accountFallback(Exception e) {
//        log.error("Circuit breaker activated! Error: {}", e.getMessage());
//        return Map.of(
//                "message", "Account service is currently unavailable. Please try again later.",
//                "status", "FALLBACK",
//                "error", e.getClass().getSimpleName(),
//                "timestamp", System.currentTimeMillis()
//        );
//    }
}