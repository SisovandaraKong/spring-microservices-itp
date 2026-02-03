package co.istad.dara.pipeline_service.service;

import co.istad.dara.pipeline_service.client.AccountClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountClient accountClient;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public Map<String, Object> getAllAccounts() {
        // Get the circuit breaker instance
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("accountService");

        // Decorate the supplier with circuit breaker
        Supplier<Map<String, Object>> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, () -> {
                    log.info("Calling account service...");
                    return accountClient.getAllAccounts();
                });

        try {
            // Execute the call
            Map<String, Object> result = decoratedSupplier.get();
            log.info("Account service call succeeded");
            return result;
        } catch (Exception e) {
            log.error("Circuit breaker caught exception: {}", e.getMessage());
            // Return fallback response
            return getFallbackResponse(e);
        }
    }

    private Map<String, Object> getFallbackResponse(Exception e) {
        log.warn("Returning fallback response for account service");
        return Map.of(
                "message", "Account service is currently unavailable. Please try again later.",
                "status", "FALLBACK",
                "error", e.getClass().getSimpleName(),
                "timestamp", System.currentTimeMillis()
        );
    }
}